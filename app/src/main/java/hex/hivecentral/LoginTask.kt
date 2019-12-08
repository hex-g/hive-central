package hex.hivecentral

import android.os.AsyncTask
import feign.Feign
import feign.gson.GsonDecoder
import java.lang.Exception

class LoginTask : AsyncTask<String, Void, String>() {
    override fun doInBackground(vararg params: String?): String? {
        val request = Feign.builder()
            .decoder(GsonDecoder())
            .target(
                RequestKirby::class.java,
                "http://35.153.196.161:8762/caronte"
            )

        return try {
            System.err.println(params[0]!!)
            System.err.println(params[1]!!)
            request.auth(params[0]!!, params[1]!!).headers()["Authorization"]!!.take(1)[0]
        } catch (e: Exception) {
            println(e.message)
            null
        }
    }
}
