package ru.itis.imagesearch.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.itis.imagesearch.data.api.response.Hit
import ru.itis.imagesearch.domain.FindImagesUseCase
import java.net.UnknownHostException

class MainListViewModel(
    private val findImagesUseCase: FindImagesUseCase
) : ViewModel(){

    private val mProgress: MutableLiveData<Boolean> = MutableLiveData()
    private val mMain: MutableLiveData<List<Hit>> = MutableLiveData()
    private val mHistory: MutableLiveData<List<String>> = MutableLiveData()
    private val mNetworkState: MutableLiveData<Boolean> = MutableLiveData()

    fun progress(): LiveData<Boolean> = mProgress
    fun mainList(): LiveData<List<Hit>> = mMain
    fun mainHistory(): LiveData<List<String>> = mHistory
    fun mainNetworkState() : LiveData<Boolean> = mNetworkState


    fun getMainList(param: String?) {
        viewModelScope.launch {
            try {
                mProgress.value = true
                findImagesUseCase.findMainListImages(param).run {
                    mMain.value = hits
                    mNetworkState.value = true
                }
            } catch (throwable: UnknownHostException) {
                mNetworkState.value = false
            } finally {
                mProgress.value = false
            }
        }
    }

    fun getHistory() {
        viewModelScope.launch {
            try {
                mProgress.value = true
                findImagesUseCase.gitHistory().run {
                    mHistory.value = this
                }
            } catch (throwable: Throwable) {
                mHistory.value = null
            } finally {
                mProgress.value = false
            }
        }
    }

}