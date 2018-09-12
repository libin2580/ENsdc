package meridian.com.etsdcapp.model;

import java.util.ArrayList;

/**
 * Created by user 1 on 26-05-2016.
 */
public class CartModel {
public ArrayList<String> arrcrt;
   public int checkcount;

    public int getCheckcount() {
        return checkcount;
    }

    public void setCheckcount(int checkcount) {
        this.checkcount = checkcount;
    }

    public ArrayList<CartModel> getCm() {
        return cm;
    }

    public void setCm(ArrayList<CartModel> cm) {
        this.cm = cm;
    }

    private  ArrayList<CartModel> cm;

    public ArrayList<String> getArrcrt() {
        return arrcrt;
    }

    public void setArrcrt(ArrayList<String> arrcrt) {
        this.arrcrt = arrcrt;
    }

    public int getCrccnt() {
        return crccnt;
    }

    public void setCrccnt(int crccnt) {
        this.crccnt = crccnt;
    }
    public String crcadd;

    public String getCrcadd() {
        return crcadd;
    }




    public void setCrcadd(String crcadd) {
        this.crcadd = crcadd;
    }

    public String crcnam;

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public  String courseid;

    public String getSubcourseid() {
        return subcourseid;
    }

    public void setSubcourseid(String subcourseid) {
        this.subcourseid = subcourseid;
    }

    public  String subcourseid;



    public  int crccnt;

    public String getCrcnam() {
        return crcnam;
    }

    public void setCrcnam(String crcnam) {
        this.crcnam = crcnam;
    }

    public String getSubcrcnam() {
        return subcrcnam;
    }

    public void setSubcrcnam(String subcrcnam) {
        this.subcrcnam = subcrcnam;
    }

    public String subcrcnam;
}
