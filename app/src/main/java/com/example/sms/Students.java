package com.example.sms;

public class Students {
    String fullname;
    String email;
    String code;
    String phone;
    String rfidno;
    String standard;
    String division;
    String rollno;
    String password;

    public Students(String fullname, String email,String code, String phone, String rfidno, String standard, String division,String rollno, String password) {
        this.fullname = fullname;
        this.email = email;
        this.code = code;
        this.phone = "+" + this.code + phone;
        this.rfidno = rfidno;
        this.standard = standard;
        this.division = division;
        this.rollno = rollno;
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() { return phone; }

    public String getRfidno() {
        return rfidno;
    }

    public String getStandard() {
        return standard;
    }

    public String getDivision() { return division; }

    public String getRollno() {
        return rollno;
    }

    public String getPassword() {
        return password;
    }
}
