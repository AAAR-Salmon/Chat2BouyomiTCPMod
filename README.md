# Chat2BouyomiTCPMod

TCP/IP ソケット通信を利用してチャットの内容を棒読みちゃんに横流しする Mod です。

# 対応バージョン

Minecraft 1.16.4~ かつ Minecraft Forge 35~

# 使い方

1. [Releases](https://github.com/AAAR-Salmon/Chat2BouyomiTCPMod/releases) から最新版の jar ファイルをダウンロードする。
2. Minecraft Forge 35 以降を導入した Minecraft 1.16 を起動し、`mods` フォルダにダウンロードしたファイルを入れる。
3. [棒読みちゃん](https://chi.usamimi.info/Program/Application/BouyomiChan/)を起動する。
4. 棒読みちゃんの基本設定から、システム>アプリケーション連携の Socket 連携を有効化し、ポート番号を 50001 に設定する。
    - その他のポート番号へは今後対応する予定です。

この状態で普通にプレイすれば棒読みちゃんでチャットを読み上げられるはずです。
マルチプレイに関しては未確認。

# ライセンス

この Mod のソースコードおよびビルドは MIT License にて配布します。
