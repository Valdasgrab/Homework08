package lt.vgrabauskas.homework08.secondactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import lt.vgrabauskas.homework08.ActivityLifecycles
import lt.vgrabauskas.homework08.R
import lt.vgrabauskas.homework08.databinding.ActivityNoteBinding
import lt.vgrabauskas.homework08.mainactivity.MainActivity
import lt.vgrabauskas.homework08.repository.Note

class NoteDetails : ActivityLifecycles() {

    private lateinit var binding: ActivityNoteBinding
    private val noteActivityViewModel: SecondActivityViewModel by viewModels()

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
        intent.getIntExtra(MainActivity.MAIN_ACTIVITY_NOTE_INTENT_ID, -1)

    fun onClickSaveButton() {
        noteActivityViewModel.saveNote(binding.note as Note)
        finish()
    }
    fun onClickCloseButton(view: View) {
        finish()
    }

    companion object {

        const val SECOND_ACTIVITY_NOTE_INTENT_RETURN_OBJECT = "lt.vgrabauskas.homework08.secondactivity_item_intent_return_object"
        const val SECOND_ACTIVITY_NOTE_SAVE_INSTANCE_STATE = "lt.vgrabauskas.homework08.secondactivity_item_save_instance_state"
        const val SECOND_ACTIVITY_NOTE_FINISH_INTENT_STATUS = "lt.vgrabauskas.homework08.secondactivity_finish_intent_status"
        const val SECOND_ACTIVITY_NOTE_INTENT_RETURN_UPDATE = 102
    }
}