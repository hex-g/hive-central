package hex.hivecentral

import android.os.AsyncTask
import feign.Feign
import feign.gson.GsonDecoder
import java.lang.Exception

class GetTreeTask : AsyncTask<String, Void, NoteNode>() {
    override fun doInBackground(vararg params: String?): NoteNode? {
        val request = Feign.builder()
            .decoder(GsonDecoder())
            .target(
                RequestKirby::class.java,
                "http://35.153.196.161:8762/kirby"
            )

        return try {
            request.getTree(params[0]!!)
        } catch (e: Exception) {
            println(e.message)
            null
        }
    }
}
