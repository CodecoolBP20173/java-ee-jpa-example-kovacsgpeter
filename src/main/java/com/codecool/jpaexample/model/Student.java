package com.codecool.jpaexample.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Transient
    private long age;

    @ManyToOne
    private Klass klass;

    @OneToOne(mappedBy = "student")
    private Address address;

    @ElementCollection
    private List<String> phoneNumbers;

    public Student() {
    }

    public Student(String name, String email, Date dateOfBirth) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.age = (Calendar.getInstance().getTimeInMillis() - dateOfBirth.getTime())
                / (60L * 60L * 1000L * 24L * 365L);
    }

    public Student(String name, String email, Date dateOfBirth, Address address) {
        this(name, email, dateOfBirth);
        this.address = address;
        this.phoneNumbers = phoneNumbers;
    }

    public Student(String name, String email, Date dateOfBirth, Address address, List<String> phoneNumbers, Klass klass) {
        this(name, email, dateOfBirth, address);
        this.phoneNumbers = phoneNumbers;
        this.klass = klass;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getAge() {
        Date now = new Date();
        long timeBetween = now.getTime() - getDateOfBirth().getTime();
        double yearsBetween = timeBetween / 3.15576e+10;
        return (int) Math.floor(yearsBetween);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address id=" + address.getId() +
                '}';
    }

}
