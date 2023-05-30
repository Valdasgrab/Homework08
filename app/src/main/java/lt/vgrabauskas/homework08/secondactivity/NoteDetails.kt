package lt.vgrabauskas.homework08.secondactivity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import lt.vgrabauskas.homework08.ActivityLifecycles
import lt.vgrabauskas.homework08.R
import lt.vgrabauskas.homework08.databinding.ActivityNoteBinding
import lt.vgrabauskas.homework08.mainactivity.Notes
import lt.vgrabauskas.homework08.repository.Note

class NoteDetails : ActivityLifecycles() {

    private lateinit var binding: ActivityNoteBinding
    private val noteActivityViewModel: NoteDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_note)
        binding.notedetails = this
        noteActivityViewModel.noteLiveData.observe(
            this,
            Observer { note ->
                binding.note = note
            }
        )
        noteActivityViewModel.fetchNote(getIntentExtra())
    }

    private fun getIntentExtra() =
        intent.getIntExtra(Notes.MAIN_ACTIVITY_NOTE_INTENT_ID, -1)

    fun onClickSaveButton() {
        noteActivityViewModel.saveNote(binding.note as Note)
        finish()
    }

    fun onClickCloseButton(view: View) {
        finish()
    }
}
