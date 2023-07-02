package ru.stqa.java_learn.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String lastname;
    private final String address;
    private final String home;
    private final String mobile;
    private final String email;

    public ContactData(String firstName, String lastname, String address, String home, String mobile, String email) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.address = address;
        this.home = home;
        this.mobile = mobile;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getHome() {
        return home;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }
}
