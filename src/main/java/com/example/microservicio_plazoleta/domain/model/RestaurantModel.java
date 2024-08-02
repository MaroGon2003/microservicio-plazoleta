package com.example.microservicio_plazoleta.domain.model;

public class RestaurantModel {

    private Long id;
    private String name;
    private String address;
    private Long idOwner;
    private String phone;
    private String logoUrl;
    private String nit;

    public RestaurantModel() {
    }

    public RestaurantModel(String address, Long id, Long idOwner, String logoUrl, String name, String nit, String phone) {
        this.address = address;
        this.id = id;
        this.idOwner = idOwner;
        this.logoUrl = logoUrl;
        this.name = name;
        this.nit = nit;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
