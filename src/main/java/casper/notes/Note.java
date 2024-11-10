package casper.notes;

public class Note {
	public enum Status {
        IN_PROGRESS,
        COMPLETED
    }
    private int id;
    private String title;
    private String content;
    private Status status;

    // Constructor to create a new note
    public Note(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = Status.IN_PROGRESS;  // Default status
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    // Setter methods
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    // String representation of the note
    @Override
    public String toString() {
        return "Note ID: " + id + "\nTitle: " + title + "\nContent: " + content + "\nStatus: " + status;
    }
}
