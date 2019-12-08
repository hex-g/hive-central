package hex.hivecentral

import android.os.AsyncTask
import feign.Feign
import feign.gson.GsonDecoder
import java.lang.Exception

class GetTreeTask : AsyncTask<Void, Void, NoteNode>() {
    override fun doInBackground(vararg params: Void?): NoteNode? {
        val request = Feign.builder()
            .decoder(GsonDecoder())
            .target(
                RequestKirby::class.java,
                "http://35.153.196.161:8762/"
            )

        return try {
            request.getTree()
        } catch (e: Exception) {
            null
        }
    }
}
