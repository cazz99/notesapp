package casper.notes;

public class Note {
    private int id;
    private String title;
    private String content;
    private Status status;

    // Enum for note status (In Progress, Completed)
    public enum Status {
        IN_PROGRESS,
        COMPLETED
    }

    // Default constructor
    public Note() {
    }

    // Constructor with parameters
    public Note(int id, String title, String content, Status status) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                '}';
    }
}