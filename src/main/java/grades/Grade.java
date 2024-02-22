package grades;

public class Grade {
    private int grade;
    private int stID;
    private int courseID;

    public Grade(int grade, int stID, int courseID) {
        this.grade = grade;
        this.stID = stID;
        this.courseID = courseID;
    }

    public int getGrade() {
        return grade;
    }

    public int getStID() {
        return stID;
    }

    public int getCourseID() {
        return courseID;
    }
}
