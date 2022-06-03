# TikTokMap

TikTokMap is a spigot plugin that utilizes a TikTok livechat
reader to extract data from a livestream and insert it on 
a Minecraft map item.

***

## Getting Started

The first step is to configure the plugins config.yml and make sure the
url and username are both valid. The livestream user must be live before
you connect with the command. You may also host the chat reader yourself 
or use the demo version [HERE](https://tiktok-chat-reader.zerody.one/).

***

### Dependencies

TikTokMap is built on the following libraries:

* [Spigot-API](https://www.spigotmc.org/wiki/spigot-maven/)
* [Socket.IO](https://github.com/socketio/socket.io-client-java)
* [TikTok-Chat-Reader](https://github.com/zerodytrash/TikTok-Chat-Reader)
  * (Optional: Compile yourself for your own chat reader client)