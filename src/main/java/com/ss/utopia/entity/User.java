package com.ss.utopia.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonBackReference
    private UserRole userRole;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<BookingUser> bookingUsers = new ArrayList<>();

    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    private String givenName;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    private String familyName;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 45)
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    private String password;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 45)
    private String phone;

    public User(){

    }

    public User(String givenName, String familyName, String username, String email, String password, String phone) {
        this.givenName = givenName;
        this.familyName = familyName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public User(Integer agentId) {
        this.id = agentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<BookingUser> getBookingUsers() {
        return bookingUsers;
    }

    public void setBookingUsers(List<BookingUser> bookingUsers) {
        this.bookingUsers = bookingUsers;
    }
}
