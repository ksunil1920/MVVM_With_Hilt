package com.sky.kahabat.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sky.kahabat.model.QuotesListModel
import com.sky.kahabat.model.repository.QuotesRepository
import com.sky.kahabat.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: QuotesRepository):ViewModel() {
    val page:Int= Random().nextInt()
    init {
         viewModelScope.launch {
            repository.getListOfQuotes(page)
        }
    }
    val quotesViewModelLiveData: LiveData<NetworkResult<QuotesListModel>>
        get() = repository.quotes


}