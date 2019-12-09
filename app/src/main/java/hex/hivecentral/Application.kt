package hex.hivecentral

import android.app.Application

class Application : Application() {
    var currentUser: String? = null
    var token: String? = null
}