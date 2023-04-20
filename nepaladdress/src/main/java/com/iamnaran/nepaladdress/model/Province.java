package com.iamnaran.nepaladdress.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Province {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("area_sq_km")
    @Expose
    private String areaSqKm;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("headquarter")
    @Expose
    private String headquarter;


    private List<District> allDistrictList;

    public List<District> getAllDistrictList() {
        return allDistrictList;
    }

    public void setAllDistrictList(List<District> allDistrictList) {
        this.allDistrictList = allDistrictList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getHeadquarter() {
        return headquarter;
    }

    public void setHeadquarter(String headquarter) {
        this.headquarter = headquarter;
    }
}
