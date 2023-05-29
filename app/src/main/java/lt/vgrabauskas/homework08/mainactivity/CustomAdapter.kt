package lt.vgrabauskas.homework08.mainactivity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import lt.vgrabauskas.homework08.databinding.NoteBinding
import lt.vgrabauskas.homework08.repository.Note

class CustomAdapter(context: Context) : BaseAdapter(){
    private val inflater = LayoutInflater.from(context)
    private val list = mutableListOf<Note>()

    fun getMaxId() = if (list.isEmpty()){
        -1
    } else {
        list.maxBy { item -> item.id }.id
    }

    fun add(vararg note: Note) {
        list.addAll(note)
        notifyDataSetChanged()
    }

    fun add(notes: List<Note>) {
        list.addAll(notes)
        notifyDataSetChanged()
    }

    fun update(note: Note?) {
        if (note != null) {
            val index = list.indexOfFirst { it.id == note.id }
            if (index >=0) {
                list[index] = note
                notifyDataSetChanged()
            }
        }
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val binding: NoteBinding
        if (view == null) {
            binding = NoteBinding.inflate(inflater, parent, false)
            view = binding.root
            view.tag = binding
        } else {
            binding = view.tag as NoteBinding
        }

        //something
        return view
    }

    override fun getCount(): Int =list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int) = position.toLong()
}