package meridian.com.etsdcapp.model;

/**
 * Created by user 1 on 30-04-2016.
 */
public class ScheduleModel {
    private String schid;
    private  String day;
    private  String schselday;

    public String getSchselday() {
        return schselday;
    }

    public void setSchselday(String schselday) {
        this.schselday = schselday;
    }

    public String getSchid() {
        return schid;
    }

    public void setSchid(String schid) {
        this.schid = schid;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    private String crcnam;
    private  String tim;

    public String getCrcnam() {
        return crcnam;
    }

    public void setCrcnam(String crcnam) {
        this.crcnam = crcnam;
    }

    public String getTim() {
        return tim;
    }

    public void setTim(String tim) {
        this.tim = tim;
    }
}
