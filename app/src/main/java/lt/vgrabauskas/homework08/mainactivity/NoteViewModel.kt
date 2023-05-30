package lt.vgrabauskas.homework08.mainactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lt.vgrabauskas.homework08.repository.Note
import lt.vgrabauskas.homework08.repository.NoteRepository

class NoteViewModel : ViewModel() {

    private val _itemsLiveData = MutableLiveData<List<Note>>(mutableListOf())
    val itemsLiveData: LiveData<List<Note>>
        get() = _itemsLiveData

    fun fetchNotes() {
        if (itemsLiveData.value == null || _itemsLiveData.value?.isEmpty() == true) {
            NoteRepository.instance.addDummyListOfItems()
        }
        _itemsLiveData.value = NoteRepository.instance.notes
    }

    fun searchNotes(query: String) {
        _itemsLiveData.value = NoteRepository.instance.getSearchResults(query)
    }
}