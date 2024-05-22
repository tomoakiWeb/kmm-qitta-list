import org.koin.core.component.KoinComponent
import com.qittaList.QittaSDK
import com.qittaList.network.QittaApi
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.dsl.module

class KoinHelper: KoinComponent {
    private val sdk: QittaSDK by inject<QittaSDK>()
    suspend fun getArticle(): List<Article> {
        return sdk.getArticles()
    }
}

fun initKoin() {
    startKoin {
        modules(module {
            single<QittaApi> { QittaApi() }
            single<QittaSDK> {
                QittaSDK(
                   api = get()
                )
            }
        })
    }
}