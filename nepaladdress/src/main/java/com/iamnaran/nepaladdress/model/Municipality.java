package com.iamnaran.nepaladdress.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Municipality {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("district_id")
    @Expose
    private Integer districtId;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("area_sq_km")
    @Expose
    private String areaSqKm;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("wards")
    @Expose
    private String wards;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaSqKm() {
        return areaSqKm;
    }

    public void setAreaSqKm(String areaSqKm) {
        this.areaSqKm = areaSqKm;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWards() {
        return wards;
    }

    public void setWards(String wards) {
        this.wards = wards;
    }
}
