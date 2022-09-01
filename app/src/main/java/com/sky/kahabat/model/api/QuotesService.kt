package com.sky.kahabat.model.api

import com.sky.kahabat.model.QuotesListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesService {
    @GET("quotes/")
   suspend fun getQuotesList(@Query("page") page: Int):Response<QuotesListModel>
}