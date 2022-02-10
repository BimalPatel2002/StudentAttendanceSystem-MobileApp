package com.example.sms;

public class AddAttendance {

    String fullname, standard , division, rollno , rfidno;

    public AddAttendance(String fullname, String standard, String division ,String rollno, String rfidno) {
        this.fullname = fullname;
        this.standard = standard;
        this.division = division;
        this.rollno = rollno;
        this.rfidno = rfidno;
    }

    public String getFullname() { return fullname; }

    public String getStandard() { return standard; }

    public String getDivision() { return division; }

    public String getRollno() { return rollno; }

    public String getRfidno() { return rfidno; }
}