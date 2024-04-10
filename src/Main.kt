import java.util.*

data class Note(val id: String = UUID.randomUUID().toString(), var title: String, var content: String)

class NoteManager {
    private val notes = mutableListOf<Note>()

    fun addNote(title: String, content: String, id: String? = null) {
        val note = Note(id ?: UUID.randomUUID().toString(), title, content)
        notes.add(note)
    }

    fun getNoteById(id: String): Note? {
        return notes.find { it.id == id }
    }

    fun editNoteTitle(id: String, newTitle: String) {
        getNoteById(id)?.title = newTitle
    }

    fun editNoteContent(id: String, newContent: String) {
        getNoteById(id)?.content = newContent
    }

    fun deleteNoteById(id: String) {
        notes.removeIf { it.id == id }
    }

    fun getAllNotes(): List<Note> {
        return notes.toList()
    }
}

fun main() {
    val noteManager = NoteManager()

    println("Welcome to Simple Note-taking App!")

    while (true) {
        println("\n1. View all notes")
        println("2. Add a new note")
        println("3. Edit a note")
        println("4. Delete a note")
        println("5. Exit")
        print("Choose an option: ")

        when (readlnOrNull()?.toIntOrNull()) {
            1 -> {
                val allNotes = noteManager.getAllNotes()
                if (allNotes.isNotEmpty()) {
                    println("\nAll Notes:")
                    allNotes.forEach { println("${it.title}: ${it.content}") }
                } else {
                    println("\nNo notes found.")
                }
            }
            2 -> {
                print("Enter note title: ")
                val title = readlnOrNull() ?: ""
                print("Enter note content: ")
                val content = readlnOrNull() ?: ""
                print("Enter note ID (optional, leave blank to auto-generate): ")
                val id = readlnOrNull()
                noteManager.addNote(title, content, id)
                println("Note added successfully!")
            }
            3 -> {
                print("Enter note ID to edit: ")
                val id = readlnOrNull() ?: ""
                val note = noteManager.getNoteById(id)
                if (note != null) {
                    print("Enter new title: ")
                    val newTitle = readlnOrNull() ?: note.title
                    print("Enter new content: ")
                    val newContent = readlnOrNull() ?: note.content
                    noteManager.editNoteTitle(id, newTitle)
                    noteManager.editNoteContent(id, newContent)
                    println("Note edited successfully!")
                } else {
                    println("Note not found.")
                }
            }
            4 -> {
                print("Enter note ID to delete: ")
                val id = readlnOrNull() ?: ""
                val note = noteManager.getNoteById(id)
                if (note != null) {
                    noteManager.deleteNoteById(id)
                    println("Note deleted successfully!")
                } else {
                    println("Note not found.")
                }
            }
            5 -> {
                println("Exiting...")
                return
            }
            else -> println("Invalid option. Please choose again.")
        }
    }
}