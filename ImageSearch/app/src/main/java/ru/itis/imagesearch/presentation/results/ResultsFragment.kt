package ru.itis.imagesearch.presentation.results

import android.content.ContentValues
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_results.*
import kotlinx.coroutines.Dispatchers
import ru.itis.imagesearch.R
import ru.itis.imagesearch.data.api.ApiFactory
import ru.itis.imagesearch.data.api.ImagesRepositoryImpl
import ru.itis.imagesearch.data.api.response.Hit
import ru.itis.imagesearch.domain.FindImagesUseCase
import ru.itis.imagesearch.presentation.ViewModelFactory
import ru.itis.imagesearch.presentation.details.DetailsFragment
import ru.itis.imagesearch.presentation.main.verticalList.ImagesAdapter

class ResultsFragment : Fragment() {

    lateinit var viewModel: ResultsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, initFactory()).get(ResultsViewModel::class.java)
        initSubscribes()
        initListeners()
    }

    private fun initListeners() {
        viewModel.getResults(arguments?.getString("REQ")!!)
    }

    private fun initSubscribes() {
        with(viewModel) {
            progress().observe(viewLifecycleOwner, {
                pb_res.isVisible = it
            })
            mainNetworkState().observe(viewLifecycleOwner, {
                if (it) {
                    mainResults().observe(viewLifecycleOwner, {
                        showResults(it)
                    })
                } else Toast.makeText(requireContext(), "Нет подключения к сети", Toast.LENGTH_LONG).show()
            })
        }
    }

    fun showResults(list: List<Hit>) {
        if (list.isEmpty()) Toast.makeText(requireContext(), "Ничего не найдено", Toast.LENGTH_LONG).show()
        else {
            rv_results.adapter = ImagesAdapter(list, Glide.with(this)) { id ->
                val bundle = Bundle()
                bundle.putInt("ID", id)
                val df = DetailsFragment()
                df.arguments = bundle
                fragmentManager?.beginTransaction()?.addToBackStack(ContentValues.TAG)?.replace(R.id.fragment, df)?.commit()
            }
        }
    }

    private fun initFactory() = ViewModelFactory(
            findCityUseCase = ApiFactory.imageApi.let {
                ImagesRepositoryImpl(it, requireContext()).let {
                    FindImagesUseCase(it, Dispatchers.IO)
                }
            }
    )

    companion object {
        fun getNewInstance(args: Bundle?): ResultsFragment {
            val resFragment = ResultsFragment()
            resFragment.arguments = args
            return resFragment
        }
    }
}