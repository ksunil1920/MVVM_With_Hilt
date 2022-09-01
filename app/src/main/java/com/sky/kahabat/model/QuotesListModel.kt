package com.sky.kahabat.model

import com.sky.kahabat.model.Result

data class QuotesListModel(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<Result>,
    val totalCount: Int,
    val totalPages: Int
)