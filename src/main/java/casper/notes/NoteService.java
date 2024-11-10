package casper.notes;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private List<Note> notes = new ArrayList<>();
    private int nextId = 1;

    // Create a new note
    public Note createNote(String title, String content) {
        Note note = new Note(nextId++, title, content, Note.Status.IN_PROGRESS);
        notes.add(note);
        return note;
    }

    // Get all notes
    public List<Note> getAllNotes() {
        return notes;
    }

    // Get a note by ID
    public Optional<Note> getNoteById(int id) {
        return notes.stream().filter(note -> note.getId() == id).findFirst();
    }

    // Update a note
    public boolean updateNote(int id, String title, String content) {
        Optional<Note> note = getNoteById(id);
        if (note.isPresent()) {
            Note updatedNote = note.get();
            updatedNote.setTitle(title);
            updatedNote.setContent(content);
            return true;
        }
        return false;
    }

    // Update the status of a note
    public boolean updateNoteStatus(int id, Note.Status status) {
        Optional<Note> note = getNoteById(id);
        if (note.isPresent()) {
            note.get().setStatus(status);
            return true;
        }
        return false;
    }

    // Delete a note by ID
    public boolean deleteNoteById(int id) {
        Optional<Note> note = getNoteById(id);
        if (note.isPresent()) {
            notes.remove(note.get());
            return true;
        }
        return false;
    }
}
