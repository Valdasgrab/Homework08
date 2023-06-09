package lt.vgrabauskas.homework08.repository


class NoteRepository {

    val notes = mutableListOf<Note>()

    fun getNote(id: Int) = notes.find { it.id == id }

    fun addNote(note: Note) {
        var newId = 1

        if (!notes.isEmpty()) {
            newId = notes.maxBy { it.id }.id.inc()
        }

        val newNote = Note(_id = newId, note.name, note.details)
        notes.add(newNote)
    }

    fun updateNote(note: Note?) {
        if (note != null) {
            val index = notes.indexOfFirst { it.id == note.id }
            if (index >= 0) {
                notes[index] = note
            }
        }
    }

    fun addDummyListOfItems() {
        notes.addAll(generateListOfItems())
    }

    private fun generateListOfItems(): List<Note> {
        val list = mutableListOf<Note>()

        for (number in 1..9) {
            val item = Note(
                number,
                "dummy text01: $number",
                "dummy text02: ${(1..100).random()}"
            )
            list.add(item)
        }

        return list
    }

    companion object {
        val instance: NoteRepository = NoteRepository()
    }
}
