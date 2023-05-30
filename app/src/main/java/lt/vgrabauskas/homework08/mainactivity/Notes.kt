package lt.vgrabauskas.homework08.mainactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import lt.vgrabauskas.homework08.ActivityLifecycles
import lt.vgrabauskas.homework08.R
import lt.vgrabauskas.homework08.databinding.ActivityMainBinding
import lt.vgrabauskas.homework08.repository.Note
import lt.vgrabauskas.homework08.secondactivity.NoteDetails

class Notes : ActivityLifecycles() {

    private lateinit var adapter: CustomAdapter
    private lateinit var binding: ActivityMainBinding
    private val viewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivity = this

        setUpNoteListView()

        viewModel.itemsLiveData.observe(this,
            Observer { lisOfNotes ->
                adapter.add(lisOfNotes)
            }
        )
        setClickOpenNoteDetails()

        binding.searchButton.setOnClickListener {
            val query = binding.searchEditText.text.toString()
            viewModel.searchNotes(query)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchNotes()
    }

    fun onClickButtonOpenSecondActivity(view: View) {
        startActivity(Intent(this, NoteDetails::class.java))
    }

    private fun setUpNoteListView() {
        adapter = CustomAdapter(this)
        binding.noteListView.adapter = adapter
    }

    private fun setClickOpenNoteDetails() {
        binding.noteListView.setOnItemClickListener { adapterView, view, position, l ->
            val note: Note = adapterView.getItemAtPosition(position) as Note
            val intent = Intent(this, NoteDetails::class.java)
            intent.putExtra(MAIN_ACTIVITY_NOTE_INTENT_ID, note.id)
            startActivity(intent)
        }
    }

    companion object {
        const val MAIN_ACTIVITY_NOTE_INTENT_ID = "package lt.vgrabauskas.homework08_note_intent_id"
    }
}