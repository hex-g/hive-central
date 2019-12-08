package hex.hivecentral

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        val root = GetTreeTask().execute().get()

        //apagar essa linha
        var mockedNote = NotesAdapter(this, notes).Note("Não há anotações", "Parece que nenhuma anotação foi feita até o momento, acesse a Hive-Nest e seja o primeiro a criar uma!!!")


        for(id in 0..0){
            val task = ReadNoteTask()
            val note: NotesAdapter.Note? = task.execute().get()
            if (note != null) {
                notes.add(note)
            }
            //apagar esse else
            else{
                notes.add(mockedNote)
                notes.add(mockedNote)
                notes.add(mockedNote)
                notes.add(mockedNote)
                notes.add(mockedNote)
                notes.add(mockedNote)
                notes.add(mockedNote)
            }
        }

        adapter = NotesAdapter(this, notes)
        home_recyclerView_notes.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(this)
        home_recyclerView_notes.layoutManager = linearLayoutManager

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
    fun openArticle(view: View){
        startActivity(Intent(this, OpenArticle::class.java))
    }
}
