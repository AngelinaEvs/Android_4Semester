package ru.itis.imagesearch.presentation.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.itis.imagesearch.data.api.response.Hit
import ru.itis.imagesearch.domain.FindImagesUseCase
import java.net.UnknownHostException

class ResultsViewModel(
    private val findImagesUseCase: FindImagesUseCase
) : ViewModel() {

    private val mProgress: MutableLiveData<Boolean> = MutableLiveData()
    private val mMain: MutableLiveData<List<Hit>> = MutableLiveData()
    private val mNetworkState: MutableLiveData<Boolean> = MutableLiveData()

    fun progress(): LiveData<Boolean> = mProgress

    fun mainResults(): LiveData<List<Hit>> = mMain

    fun mainNetworkState() : LiveData<Boolean> = mNetworkState

    fun getResults(req: String) {
        viewModelScope.launch {
            try {
                mProgress.value = true
                findImagesUseCase.findResults(req).run {
                    mMain.value = hits
                    mNetworkState.value = true
                }
                findImagesUseCase.addReqToHistory(req)
            } catch (throwable: UnknownHostException) {
                mNetworkState.value = false
            } finally {
                mProgress.value = false
            }
        }
    }
}