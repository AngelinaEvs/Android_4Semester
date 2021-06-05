package ru.itis.imagesearch.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.itis.imagesearch.data.api.response.Hit
import ru.itis.imagesearch.domain.FindImagesUseCase
import java.net.UnknownHostException

class DetailsViewModel(
        private val findImagesUseCase: FindImagesUseCase
) : ViewModel(){

    private val mProgress: MutableLiveData<Boolean> = MutableLiveData()
    private val mMain: MutableLiveData<List<Hit>> = MutableLiveData()
    private val mSimilar: MutableLiveData<List<Hit>> = MutableLiveData()
    private val mUrl: MutableLiveData<String> = MutableLiveData()
    private val mNetworkState: MutableLiveData<Boolean> = MutableLiveData()

    fun progress(): LiveData<Boolean> = mProgress

    fun mainData(): LiveData<List<Hit>> = mMain

    fun mainSimilar(): LiveData<List<Hit>> = mSimilar

    fun mainUrl(): LiveData<String> = mUrl

    fun mainNetworkState() : LiveData<Boolean> = mNetworkState

    fun getData(id: Int) {
        viewModelScope.launch {
            try {
                mProgress.value = true
                findImagesUseCase.findImagesById(id).run {
                    mMain.value = hits
                    mUrl.value = hits[0].webformatURL
                    findImagesUseCase.findResults(mMain.value!![0].tags).run {
                        mSimilar.value = hits
                        mNetworkState.value = true
                    }
                }
            } catch (throwable: UnknownHostException) {
                mNetworkState.value = false
            } finally {
                mProgress.value = false
            }
        }
    }

}