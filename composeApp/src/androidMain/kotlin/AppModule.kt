package com.qittaList

import com.qittaList.QittaSDK
import com.qittaList.network.QittaApi
import org.koin.dsl.module


val appModule = module {
    single<QittaApi> { QittaApi() }
    single<QittaSDK> {
        QittaSDK(
            api = get()
        )
    }
}