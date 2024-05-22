import SwiftUI
import Shared

struct ContentView: View {
  @ObservedObject private(set) var viewModel: ViewModel

    var body: some View {
        NavigationView {
            listView()
            .navigationBarTitle("記事一覧")
        }
    }

    private func listView() -> AnyView {
        switch viewModel.articles {
        case .loading:
            return AnyView(Text("Loading...").multilineTextAlignment(.center))
        case .result(let article):
            return AnyView(List(article) { article in
                ArticleRow(article: article)
            }.refreshable {
                viewModel.loadArticles()
            })
        case .error(let description):
            return AnyView(Text(description).multilineTextAlignment(.center))
        }
    }
}

extension ContentView {

    enum LoadableArticles {
        case loading
        case result([Article])
        case error(String)
    }

    @MainActor
    class ViewModel: ObservableObject {
        let helper: KoinHelper = KoinHelper()
        @Published var articles = LoadableArticles.loading

        init() {
            self.loadArticles()
        }

        func loadArticles() {
            Task {
                do {
                    self.articles = .loading
                    let articles = try await helper.getArticle()
                    self.articles = .result(articles)
                } catch {
                    self.articles = .error(error.localizedDescription)
                }
            }
        }
    }
}

extension Article: Identifiable { }

