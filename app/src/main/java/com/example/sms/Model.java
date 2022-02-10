package com.example.sms;

public class Model {
    String division,fullname,rollno,standard,rfidno,date,time;

    public Model() {
    }

    public Model(String division, String fullname, String rollno, String standard, String rfidno, String date, String time) {
        this.division = division;
        this.fullname = fullname;
        this.rollno = rollno;
        this.standard = standard;
        this.rfidno = rfidno;
        this.date = date;
        this.time = time;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getRfidno() { return rfidno; }

    public void setRfidno(String rfidno) { this.rfidno = rfidno; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }
}
