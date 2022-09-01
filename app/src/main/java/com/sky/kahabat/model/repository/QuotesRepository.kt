package com.sky.kahabat.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sky.kahabat.model.QuotesListModel
import com.sky.kahabat.model.api.QuotesService
import com.sky.kahabat.util.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class QuotesRepository @Inject constructor(private val quotesService: QuotesService) {
    private val _quotesListLiveData = MutableLiveData<NetworkResult<QuotesListModel>>()
    val quotes: LiveData<NetworkResult<QuotesListModel>>
        get() = _quotesListLiveData

    suspend fun getListOfQuotes(page: Int) {
        _quotesListLiveData.postValue(NetworkResult.Loading())
        val response = quotesService.getQuotesList(page)
        if (response.isSuccessful && response.body() != null) {
            _quotesListLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _quotesListLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _quotesListLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }
}