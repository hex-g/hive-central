package hex.hivecentral

import android.os.AsyncTask
import feign.Feign
import feign.gson.GsonDecoder
import java.lang.Exception

class GetNoteTask : AsyncTask<String, Void, KirbyNote>() {
    override fun doInBackground(vararg params: String?): KirbyNote? {
        val request = Feign.builder()
            .decoder(GsonDecoder())
            .target(
                RequestKirby::class.java,
                "http://35.153.196.161:8762/kirby"
            )

        return try {
            request.getNote(params[0]!!, params[1]!!)
        } catch (e: Exception) {
            println(e.message)
            null
        }
    }
}
