package meridian.com.etsdcapp.model;

/**
 * Created by user 1 on 01-06-2016.
 */
public class JsonModel {
    private String course;
    private String subcourseid;

    public String getSubcourseid() {
        return subcourseid;
    }

    public void setSubcourseid(String subcourseid) {
        this.subcourseid = subcourseid;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSubcourse() {
        return subcourse;
    }

    public void setSubcourse(String subcourse) {
        this.subcourse = subcourse;
    }

    private String subcourse;

}
