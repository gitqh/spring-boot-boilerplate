## 分包说明

应用层根据不同的实际应用分包
- admin 用于管理员操作
- user 用于用户的个人操作，提供 rest api 和 websocket 
- auth 各个应用都需要 auth 提供的接口，因此单独出来一个应用，类似于 SSO，如果每个 应用需要自己独特的 auth client（例如用户侧可能需要短信登录）就需要各自实现。
