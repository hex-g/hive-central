package hex.hivecentral

import android.os.AsyncTask
import feign.Feign
import feign.gson.GsonDecoder
import java.lang.Exception

class ReadNoteTask : AsyncTask<Int, Void, NotesAdapter.Note>() {
    override fun doInBackground(vararg params: Int?): NotesAdapter.Note? {
        val request = Feign.builder()
            .decoder(GsonDecoder())
            .target(
                RequestKirby::class.java,
                "https://api.myjson.com/bins/"
            )


        return try {
            request.getNote()
        } catch (e: Exception) {
            null
        }
    }
}