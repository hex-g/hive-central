package hex.hivecentral

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

class NotesAdapter(private val context: Context, private val notes: MutableList<Note>) :
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val createdView = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(createdView)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.fill(note)
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.itemNota_textView_title)
        private val briefing: TextView = itemView.findViewById(R.id.itemNota_textView_briefing)

        fun fill(note: Note) {
            title.text = note.title
            briefing.text = note.briefing
        }
    }

    class Note(val title: String, val briefing: String) : Serializable
}