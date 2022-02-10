package com.example.sms;

public class Teachers {
    String fullname;
    String email;
    String code;
    String phone;
    String password;

    public Teachers(String fullname, String email, String code, String phone, String password) {
        this.fullname = fullname;
        this.email = email;
        this.code = code;
        this.phone = "+" + this.code + phone;
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() { return phone; }

    public String getPassword() {
        return password;
    }
}
