package casper.notes;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {
    private static final NotesDB notesDB = new NotesDB();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
    	while (true) {
    		//display menu
            System.out.println("\n===== Notes App =====");
            System.out.println("1. Create Note");
            System.out.println("2. View All Notes");
            System.out.println("3. View Note by ID");
            System.out.println("4. Update Note");
            System.out.println("5. Update Note Status");
            System.out.println("6. Delete Note");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // consume newline

            //handle choices
            switch (option) {
                case 1 -> createNote();
                case 2 -> viewAllNotes();
                case 3 -> viewNoteById();
                case 4 -> updateNote();
                case 5 -> updateNoteStatus();
                case 6 -> deleteNote();
                case 7 -> {	//exit app
                    System.out.println("App exited");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void createNote() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();	//scanner scans for input
        System.out.print("Enter content: ");
        String content = scanner.nextLine();

        Note note = notesDB.createNote(title, content);
        System.out.println("Note created: " + note);
    }

    private static void viewAllNotes() {
        List<Note> notes = notesDB.getAllNotes();
        if (notes.isEmpty()) {
            System.out.println("No notes available.");
        } else {
            notes.forEach(System.out::println);
        }
    }

    private static void viewNoteById() {
        System.out.print("Enter note ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        Optional<Note> note = notesDB.getNoteById(id);
        note.ifPresentOrElse(
            System.out::println,
            () -> System.out.println("Note not found.")
        );
    }

    private static void updateNote() {
        System.out.print("Enter note ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();  
        System.out.print("Enter new title: ");
        String title = scanner.nextLine();
        System.out.print("Enter new content: ");
        String content = scanner.nextLine();

        boolean updated = notesDB.updateNote(id, title, content);
        System.out.println(updated ? "Note updated successfully." : "Note not found.");
    }
    
    private static void updateNoteStatus() {
        System.out.print("Enter note ID to update status: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        Note.Status status = selectStatus();

        boolean updated = notesDB.updateNoteStatus(id, status);
        System.out.println(updated ? "Status updated successfully." : "Note not found.");
    }

    private static void deleteNote() {
        System.out.print("Enter note ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        boolean deleted = notesDB.deleteNoteById(id);
        System.out.println(deleted ? "Note deleted successfully." : "Note not found.");
    }
    private static Note.Status selectStatus() {
        System.out.println("Select status:");
        System.out.println("1. In Progress");
        System.out.println("2. Completed");
        System.out.print("Enter choice: ");
        int statusChoice = scanner.nextInt();
        scanner.nextLine();  

        return switch (statusChoice) {
            case 1 -> Note.Status.IN_PROGRESS;
            case 2 -> Note.Status.COMPLETED;
            default -> {
                System.out.println("Invalid choice. Defaulting to 'In Progress'.");
                yield Note.Status.IN_PROGRESS;
            }
        };
    }
}
