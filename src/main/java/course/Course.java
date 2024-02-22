package course;

public class Course {
    private int id;
    private String name;
    private String instructorID;

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setInstructorName(String instructorID) {
        this.instructorID = instructorID;
    }

    public String getInstructorName() {
        return instructorID;
    }
}
