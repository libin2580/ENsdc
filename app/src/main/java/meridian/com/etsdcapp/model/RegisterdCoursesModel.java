package meridian.com.etsdcapp.model;

import java.util.Date;

/**
 * Created by user 1 on 01-06-2016.
 */
public class RegisterdCoursesModel {
    private  String course;
    private  String time;
    private  String subcourse;
    private  String duration;
    private  String colr;

    public String getColr() {
        return colr;
    }

    public void setColr(String colr) {
        this.colr = colr;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubcourse() {
        return subcourse;
    }

    public String getDuration() {
        return duration;
    }

    public String getTime() {
        return time;
    }

    public String getSubcourseduration() {
        return subcourseduration;

    }



    public void setSubcourse(String subcourse) {
        this.subcourse = subcourse;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setSubcourseduration(String subcourseduration) {
        this.subcourseduration = subcourseduration;
    }

    private  String subcourseduration;

    public String getDay() {
        return day;
    }



    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    private String  datesbtw;
    private String  day;
    private String  month;
    private String year;

    public String getDatesbtw() {
        return datesbtw;
    }

    public void setDatesbtw(String datesbtw) {
        this.datesbtw = datesbtw;
    }






    private  String userid;




    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }



    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
