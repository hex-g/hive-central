package hex.hivecentral

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    var lastTime = 0L
    private var adapter: NotesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if ((application as Application).currentUser == null) {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val notes = ArrayList<NotesAdapter.Note>()
        val root = GetTreeTask().execute((application as Application).token).get()

        root!!.children!!.forEach { e -> run { walkTree("", e, notes) } }

        adapter = NotesAdapter(this, notes)
        home_recyclerView_notes.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(this)
        home_recyclerView_notes.layoutManager = linearLayoutManager

    }

    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastTime <= 2000L) {
            logout()
            finishAffinity()
        } else {
            Toast.makeText(this, getString(R.string.home_toast_backpressed), Toast.LENGTH_SHORT)
                .show()
            lastTime = currentTime
        }
    }

    fun logout() {
        (application as Application).currentUser = null
        (application as Application).token = null
        finish()
    }

    fun openArticle(view: View) {
        val note = GetNoteTask().execute(
            (application as Application).token,
            view.findViewById<TextView>(R.id.itemNota_textView_title).text.toString()
        ).get()
        val intent = Intent(this, OpenArticle::class.java)
        intent.putExtra("path", note.path)
        intent.putExtra("content", note.content)
        startActivity(intent)
    }

    fun walkTree(basePath: String, node: NoteNode, notes: ArrayList<NotesAdapter.Note>) {
        if (node.children == null) {
            notes.add(NotesAdapter.Note(basePath + node.name, ""))
        } else {
            node.children.forEach { e -> run { walkTree(basePath + node.name + "/", e, notes) } }
        }
    }
}
