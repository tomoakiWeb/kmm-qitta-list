import SwiftUI
import Shared

struct ArticleRow: View {
    var article: Article

    var body: some View {
        Text(article.title)
    }
}
