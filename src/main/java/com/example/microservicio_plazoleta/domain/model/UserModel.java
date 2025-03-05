package com.example.microservicio_plazoleta.domain.model;

import java.time.LocalDate;

public class UserModel {

    private Long id;
    private String name;
    private String surName;
    private Integer dni;
    private String phone;
    private LocalDate birdDate;
    private String email;
    private String password;
    private Long rolId;

    public UserModel(LocalDate birdDate, Integer dni, String email, Long id, String name, String password, String phone, Long rolId, String surName) {
        this.birdDate = birdDate;
        this.dni = dni;
        this.email = email;
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.rolId = rolId;
        this.surName = surName;
    }

    public UserModel() {

    }

    public LocalDate getBirdDate() {
        return birdDate;
    }

    public void setBirdDate(LocalDate birdDate) {
        this.birdDate = birdDate;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }
}
