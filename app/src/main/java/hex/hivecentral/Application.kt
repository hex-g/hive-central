package hex.hivecentral

import android.app.Application

class Application : Application() {
    val API_URL = "localhost:8080/"
    var currentUser: String? = null
}