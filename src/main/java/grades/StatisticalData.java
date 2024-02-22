package grades;

public class StatisticalData {
    private String courseName;
    private int max;
    private int min;
    private double avg;

    public StatisticalData(String courseName, int max, int min, double avg) {
        this.courseName = courseName;
        this.max = max;
        this.min = min;
        this.avg = avg;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public double getAvg() {
        return avg;
    }
}
