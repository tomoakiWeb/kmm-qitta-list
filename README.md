## 完成アプリ
KMPを用いてiOS/Androidの両OSでQittaの記事一覧を取得するアプリを作成しました。完成のデモになります。

|iOS|Android|
|:-:|:-:|
|![QittaiOS.gif](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/356150/99cfa36f-da70-dc89-19c9-a3050f6aec84.gif)|![QittaAndroid.gif](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/356150/aa59cfad-94c3-d3a4-0018-3e149dd2dee2.gif)|

## KMPとは

> The Kotlin Multiplatform technology is designed to simplify the development of cross-platform projects. It reduces time spent writing and maintaining the same code for different platforms while retaining the flexibility and benefits of native programming.

公式ドキュメントの説明だと「Kotlin Multiplatformは、クロスプラットフォームプロジェクトの開発を簡素化するために設計されています。この技術は、異なるプラットフォーム向けに同じコードを書く時間とメンテナンスする時間を削減しながら、ネイティブプログラミングの柔軟性と利点を維持します。」と記載されています。

簡潔に言うとKotlinをベースにServerとAndroid,iOS,Desktop,Webでコードを共有して実装できる仕組みになります。これによりコードの再利用ができ開発効率の向上や保守の簡素化というメリットを享受できます。
本記事ではiOSとAndroidアプリのコードを共有化して実装を進めていきます

![スクリーンショット 2024-05-26 0.18.42.png](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/356150/e941fa82-3b92-ddc8-1aaa-0dc21f528daf.png)

## KMPでどこまでコードを共有するのか
KMPではUI、Presentation,BUsiness/Domain, Data/Core全てを共有してiOS/Androidアプリを開発することができます。しかしながらKMPでUIを共有化すると各プラットフォームのネイティブなUIコンポーネントや機能をフルに活用できず、実現したいレウアウトが実装できないと言う問題が発生します。

そのため本記事では[Create a multiplatform app using Ktor and SQLDelightのチュートリアル](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-ktor-sqldelight.html)に習ってUI部分とViewModelに関しては各プラットフォーム(iOS-SwiftUI, Android-Jetpack Compose)で実装しビジネスロジック部分をKMPで共通化して実装していきます。

## 本アプリの構造
図のような構造でアプリを開発しました。
各プラットフォームのViewを作成しそれぞれのViewModelを保持しています。KMPではQittaSDKとQittaAPIを持ちビジネスロジックとデータアクセスレイヤーを共通化しました。

![スクリーンショット 2024-05-26 1.31.45.png](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/356150/df3f1f9f-d683-129d-0236-fe666633f981.png)

## 実装
Githubで公開していますので参考にしてみてください。
[kmm-qitta-list](https://github.com/tomoakiWeb/kmm-qitta-list)

KMPの環境設定に関してはこちらの[公式ドキュメント](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-setup.html#possible-issues-and-solutions)を参考にすることができます。

# Kotlin Multiplatformプロジェクト構成図

```plaintext
Kotlin Multiplatform Project
│
├── /composeApp
│   ├── commonMain
│   │   └── すべてのターゲットに共通するコード
│   ├── androidMain
│       └── Android専用のコード
│
├── /iosApp
│   └── iOSアプリケーションとしてビルドされるXcodeプロジェクト
│
└── /shared
    ├── commonMain
    │   └── すべてのターゲットに共通するコード
    ├── androidMain
    │   └── Android専用のコード
    └── iosMain
        └── iOS専用のコード
```
#### /composeApp
/composeAppフォルダは、Compose Multiplatformアプリケーション全体で共有されるコードを保存するためのフォルダです。主にUIコンポーネントや、Composeに関連するロジックが含まれます。本プロジェクトではAndroidのUIUIやViewModelに関する実装とAndroidManifest.xmlでAndroidのアプリ全体をしています。

#### /iosApp
/iosAppフォルダはiOSアプリケーションとしてビルドされるXcodeプロジェクトです。Xcodeで開きビルドすることができます。
#### /sharedフォルダ
/sharedフォルダは、プロジェクト内のすべてのターゲット間で共有されるコードを保存するためのフォルダです

## 参考文献
[Ktor と SQLDelight を使用してマルチプラットフォーム アプリを作成する](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-ktor-sqldelight.html)
[Kotlin マルチプラットフォーム](https://kotlinlang.org/docs/multiplatform.html)
