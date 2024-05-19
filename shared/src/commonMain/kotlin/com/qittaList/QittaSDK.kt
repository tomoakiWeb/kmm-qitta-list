package com.qittaList

import Article
import com.qittaList.network.QittaApi

class QittaSDK(val api: QittaApi) {
    @Throws(Exception::class)
    suspend fun getArticles(): List<Article> {
        return api.getArticles()
    }
}