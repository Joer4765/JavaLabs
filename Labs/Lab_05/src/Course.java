import java.io.Serializable;

public class Course implements Serializable {
    private final int lectureHours;
    private final int practicalHours;
    private final int labHours;

    public Course(int lectureHours, int practicalHours, int labHours) {
        this.lectureHours = lectureHours;
        this.practicalHours = practicalHours;
        this.labHours = labHours;
    }

    public int getLectureHours() {
        return this.lectureHours;
    }

    public int getPracticalHours() {
        return practicalHours;
    }

    public int getLabHours() {
        return labHours;
    }
}
