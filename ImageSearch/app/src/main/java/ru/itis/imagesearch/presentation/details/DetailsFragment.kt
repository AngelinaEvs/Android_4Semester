package ru.itis.imagesearch.presentation.details

import android.Manifest
import android.app.DownloadManager
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.coroutines.Dispatchers
import ru.itis.imagesearch.App
import ru.itis.imagesearch.R
import ru.itis.imagesearch.data.api.ApiFactory
import ru.itis.imagesearch.data.api.ImagesRepositoryImpl
import ru.itis.imagesearch.data.api.response.Hit
import ru.itis.imagesearch.domain.FindImagesUseCase
import ru.itis.imagesearch.presentation.ViewModelFactory
import javax.inject.Inject

class DetailsFragment : Fragment() {

    @Inject lateinit var viewModel: DetailsViewModel
    private val STORAGE_PERMISSION_CODE: Int = 1000
    private lateinit var urlImage: String
    private var state: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as App).appComponent.detFactory()
            .create(this)
            .inject(this)
        initSubscribes()
        initListeners()
    }

    private fun initListeners() {
        viewModel.getData(requireArguments().getInt("ID"))
        downloadButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED) {
                    requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), STORAGE_PERMISSION_CODE)
                }
                else {
                    startDownloading(urlImage)
                }
            }
            else {
                startDownloading(urlImage)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            STORAGE_PERMISSION_CODE -> {
                if (grantResults.isEmpty() && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    startDownloading(urlImage)
                }
                else {
                    Toast.makeText(requireContext(), "Нет разрешения", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun initSubscribes() {
        with(viewModel) {
            progress().observe(viewLifecycleOwner, {
                pb_det.isVisible = it
            })
            mainNetworkState().observe(viewLifecycleOwner, {
                state = it
                if (it) {
                    mainData().observe(viewLifecycleOwner, {
                        Glide.with(requireContext()).load(it[0].webformatURL).into(image_item)
                        img_info.text = "Теги: " + it[0].tags + ",\nЛайки: " + it[0].likes
                    })
                    mainSimilar().observe(viewLifecycleOwner, {
                        showSimilar(it)
                    })
                    mainUrl().observe(viewLifecycleOwner, {
                        urlImage = it
                    })
                } else Toast.makeText(requireContext(), "Нет подключения к сети", Toast.LENGTH_LONG).show()
            })

        }
    }

    fun showSimilar(list: List<Hit>) {
        similar_recycler.adapter = SimilarAdapter(list, Glide.with(this)) { id ->
            val bundle = Bundle()
            bundle.putInt("ID", id)
            val df = DetailsFragment()
            df.arguments = bundle
            fragmentManager?.beginTransaction()?.addToBackStack(ContentValues.TAG)?.replace(R.id.fragment, df)?.commit()
        }
    }

    private fun startDownloading(url: String) {
        val request = DownloadManager.Request(Uri.parse(url))
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        request.setTitle("Download")
        request.setDescription("The file is downloading...")
        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalFilesDir(requireContext(), Environment.DIRECTORY_DOWNLOADS + "/ImagesSearch", "${System.currentTimeMillis()}")
        val manager = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
        Toast.makeText(requireContext(), "Загрузка началась...", Toast.LENGTH_LONG).show()
    }

    companion object {
        fun getNewInstance(args: Bundle?): DetailsFragment {
            val detailsFragment = DetailsFragment()
            detailsFragment.arguments = args
            return detailsFragment
        }
    }


}