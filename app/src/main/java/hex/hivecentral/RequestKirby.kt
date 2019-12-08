package hex.hivecentral

import feign.*

interface RequestKirby {

    @RequestLine("GET /note?path={path}")
    @Headers("Authorization: {token}")
    fun getNote(@Param("token") token: String, @Param("path") path: String): KirbyNote?

    @RequestLine("GET /tree")
    @Headers("Authorization: {token}")
    fun getTree(@Param("token") token: String): NoteNode?

    @RequestLine("POST /auth")
    @Headers("Content-Type: application/json")
    @Body("%7B\"username\": \"{username}\", \"password\": \"{password}\"%7D")
    fun auth(@Param("username") username: String, @Param("password") password: String): Response
}