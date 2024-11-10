package casper.notes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    private NoteService noteService;

    // Endpoint to display all notes
    @GetMapping
    public String getAllNotes(Model model) {
        List<Note> notes = noteService.getAllNotes();
        model.addAttribute("notes", notes);  // Add the notes to the model to pass them to the view
        return "index";  
    }

    // Endpoint to display a form to create a new note
    @GetMapping("/create")
    public String createNoteForm(Model model) {
        model.addAttribute("note", new Note());  // Add an empty Note object to the model
        return "createNote";  
    }

    // Endpoint to handle the form submission and create a new note
    @PostMapping("/create")
    public String createNote(@ModelAttribute Note note) {
        noteService.createNote(note.getTitle(), note.getContent());  // Call the service to save the note
        return "redirect:/notes";  // Redirect to the list of notes after creation
    }


    // Endpoint to display a form to update an existing note
    @GetMapping("/update/{id}")
    public String updateNoteForm(@PathVariable int id, Model model) {
        Optional<Note> note = noteService.getNoteById(id);
        model.addAttribute("note", note);  // Add the note to the model for editing
        return "updateNote";  
    }

    // Endpoint to handle the form submission and update an existing note
    @PostMapping("/update/{id}")
    public String updateNote(@PathVariable int id, @ModelAttribute Note note) {
        noteService.updateNote(id, note.getTitle(), note.getContent());  // Call the service to update the note
        return "redirect:/notes";  // Redirect to the list of notes after updating
    }
    @PostMapping("/{id}/status")
    public String updateNoteStatus(@PathVariable int id, @RequestParam("status") Note.Status status) {
        boolean updated = noteService.updateNoteStatus(id, status);
        return updated ? "redirect:/notes" : "error";
    }

    // Endpoint to delete a note by its ID
    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable int id) {
        noteService.deleteNoteById(id);  // Call the service to delete the note
        return "redirect:/notes";  // Redirect to the list of notes after deletion
    }
}
