package ru.itis.imagesearch.presentation.main

import android.content.ContentValues.TAG
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_main_list.*
import kotlinx.coroutines.Dispatchers
import ru.itis.imagesearch.R
import ru.itis.imagesearch.data.api.ApiFactory
import ru.itis.imagesearch.data.api.ImagesRepositoryImpl
import ru.itis.imagesearch.data.api.response.Hit
import ru.itis.imagesearch.domain.FindImagesUseCase
import ru.itis.imagesearch.presentation.ViewModelFactory
import ru.itis.imagesearch.presentation.details.DetailsFragment
import ru.itis.imagesearch.presentation.main.horizontalList.SearchAdapter
import ru.itis.imagesearch.presentation.main.verticalList.ImagesAdapter
import ru.itis.imagesearch.presentation.results.ResultsFragment

class MainListFragment : Fragment() {

    lateinit var viewModel: MainListViewModel
    var adapter: SearchAdapter? = null

    private var adapterImage: ImagesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, initFactory()).get(MainListViewModel::class.java)

        initSubscribes()
        initListeners()
        initAdapter()

    }

    private fun initAdapter() {
        adapterImage = ImagesAdapter(arrayListOf(), Glide.with(this)) { id ->
            val bundle = Bundle()
            bundle.putInt("ID", id)
            val df = DetailsFragment()
            df.arguments = bundle
            fragmentManager?.beginTransaction()?.addToBackStack(TAG)?.replace(R.id.fragment, df)?.commit()
        }
        rv_main.adapter = adapterImage
    }

    private fun initListeners() {
        viewModel.getMainList(null)
        viewModel.getHistory()

        popular_show1.setOnClickListener { viewModel.getMainList("popular") }
        new_show1.setOnClickListener { viewModel.getMainList("latest") }

    }

    private fun initSubscribes() {
        with(viewModel) {
            progress().observe(viewLifecycleOwner, {
                pb_main.isVisible = it
            })
            mainNetworkState().observe(viewLifecycleOwner, {
                if (it) {
                    mainHistory().observe(viewLifecycleOwner, {
                        showHistory(it)
                    })
                    mainList().observe(viewLifecycleOwner, {
                        showMainList(it)
                    })
                } else Toast.makeText(requireContext(), "Нет подключения к сети", Toast.LENGTH_LONG).show()
            })
        }
    }

    fun showMainList(list: List<Hit>) {
        adapterImage?.test(list)
    }

    fun showHistory(list: List<String>) {
        adapter = SearchAdapter(list.asReversed()) { s ->
            val bundle = Bundle()
            bundle.putString("REQ", s)
            val rf = ResultsFragment()
            rf.arguments = bundle
            fragmentManager?.beginTransaction()?.replace(R.id.fragment, rf)?.commit()
        }
        search_recycler.adapter = adapter
    }

    private fun initFactory() = ViewModelFactory(
        findCityUseCase = ApiFactory.imageApi.let {
            ImagesRepositoryImpl(it, requireContext()).let {
                FindImagesUseCase(it, Dispatchers.IO)
            }
        }
    )
}