import SwiftUI
import Shared

struct ArticleRow: View {
    var article: Article

    var body: some View {
        
        VStack(spacing: 8.0) {
            Text(article.title)
                .font(.title2)
            
            if !article.user.name.isEmpty {
                HStack(alignment: .center, spacing: 8.0) {
                    if let profileImageUrl = URL(string: article.user.profileImageUrl) {
                        AsyncImage(url: profileImageUrl)
                            .scaledToFit()
                            .frame(width: 40, height: 40)
                            .clipShape(Circle())
                        
                        Text(article.user.name)
                    }
                    Spacer()
                }
            }
        }
    }
}
