package hex.hivecentral

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Home : AppCompatActivity() {
    var lastTime = 0L
    private var adapter: NotesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if ((application as Application).currentUser == null) {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val baseUrl = (application as Application).API_URL
        val urls = ArrayList<String>()

        // build urls
        urls.add("/note")
        urls.add("/note2")
        urls.add("/note3")
        urls.add("/note4")

        val notes = ArrayList<NotesAdapter.Note>()

        val queue = Volley.newRequestQueue(this)
        urls.forEach { path -> {}}

        val stringRequest = StringRequest(baseUrl + "/note", Response.Listener<String> { response -> notes.add(NotesAdapter.Note) })

        adapter = NotesAdapter(this, notes)
        findViewById<RecyclerView>(R.id.home_recyclerView_notes).adapter = adapter
    }

    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastTime <= 2000L) {
            finishAffinity()
        } else {
            Toast.makeText(this, getString(R.string.home_toast_backpressed), Toast.LENGTH_SHORT)
                .show()
            lastTime = currentTime
        }
    }

    fun logout(view: View) {
        (application as Application).currentUser = null
        finish()
    }
}
