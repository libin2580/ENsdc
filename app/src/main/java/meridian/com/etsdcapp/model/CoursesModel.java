package meridian.com.etsdcapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by user 1 on 07-04-2016.
 */
public class CoursesModel  {
    private  String coursid;
    private  String subcoursid;
    private String coursenam;
    private  String crces;

    public String getSubcoursid() {
        return subcoursid;
    }

    public void setSubcoursid(String subcoursid) {
        this.subcoursid = subcoursid;
    }

    private  String duration;
    private String course_description;
    private String course_number;
   private String target_audience;

    public String getTarget_audience() {
        return target_audience;
    }

    public void setTarget_audience(String target_audience) {
        this.target_audience = target_audience;
    }

    private String fee;

    public String getCourse_description() {
        return course_description;
    }

    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }

    public String getCourse_number() {
        return course_number;
    }

    public void setCourse_number(String course_number) {
        this.course_number = course_number;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getApproval_certification() {
        return approval_certification;
    }

    public void setApproval_certification(String approval_certification) {
        this.approval_certification = approval_certification;
    }

    private String validity;
    private String objectives;
    private String location;
    private String aim;
    private String availability;
    private String approval_certification;
    private String clckd;
    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getClckd() {
        return clckd;
    }

    public void setClckd(String clckd) {
        this.clckd = clckd;
    }

    public String getCrces() {
        return crces;
    }

    public void setCrces(String crces) {
        this.crces = crces;
    }

    public String getCoursid() {
        return coursid;
    }

    public void setCoursid(String coursid) {
        this.coursid = coursid;
    }

    public String getCoursenam() {
        return coursenam;
    }

    public void setCoursenam(String coursenam) {
        this.coursenam = coursenam;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    private String thumbnail;




}
