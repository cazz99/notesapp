package casper.notes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NotesDB {
    private List<Note> notes;	//List for notes
    private int nextId;			//Unique id for note

    //initializes list
    public NotesDB() {
        this.notes = new ArrayList<>();
        this.nextId = 1;
    }

    //create note method
    public Note createNote(String title, String content) {
        Note note = new Note(nextId++, title, content);	//create note with new unique id +1
        notes.add(note);
        return note;
    }

    public List<Note> getAllNotes() {
        return new ArrayList<>(notes);
    }

    public Optional<Note> getNoteById(int id) {
        return notes.stream().filter(note -> note.getId() == id).findFirst();
    }
    
    //Update a note's title, content, and status by ID
    public boolean updateNote(int id, String title, String content) {
        Optional<Note> noteOptional = getNoteById(id);
        if (noteOptional.isPresent()) {
            Note note = noteOptional.get();
            note.setTitle(title);
            note.setContent(content);
            return true;
        }
        return false;	//Note id not found
    }

    public boolean deleteNoteById(int id) {
        return notes.removeIf(note -> note.getId() == id);
    }
    
    //update status of note by id
    public boolean updateNoteStatus(int id, Note.Status status) {
        Optional<Note> noteOptional = getNoteById(id);
        if (noteOptional.isPresent()) {
            noteOptional.get().setStatus(status);
            return true;
        }
        return false; 	//Note id not found
    }
}
