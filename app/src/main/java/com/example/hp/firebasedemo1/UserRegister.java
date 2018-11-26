package com.example.hp.firebasedemo1;

class UserRegister
{
    String name;
    String phone;
    String age;
    String strGender;
    String email;
    String password;
    String cpassword;

    public UserRegister(String name, String phone, String age, String strGender, String email, String password, String cpassword)
    {
        this.name=name;
        this.phone=phone;
        this.age=age;
        this.strGender=strGender;
        this.email=email;
        this.password=password;
        this.cpassword=cpassword;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAge() {
        return age;
    }

    public String getStrGender() {
        return strGender;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCpassword() {
        return cpassword;
    }
}
