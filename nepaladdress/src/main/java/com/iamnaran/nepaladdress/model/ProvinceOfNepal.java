package com.iamnaran.nepaladdress.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProvinceOfNepal implements Serializable {

    @SerializedName("allDistrictList")
    @Expose
    private List<District> districtList;
    @SerializedName("area_sq_km")
    @Expose
    private String areaSqKm;
    @SerializedName("headquarter")
    @Expose
    private String headquarter;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("website")
    @Expose
    private String website;

    public List<District> getAllDistrictList() {
        return districtList;
    }

    public void setAllDistrictList(List<District> districtList) {
        this.districtList = districtList;
    }

    public String getAreaSqKm() {
        return areaSqKm;
    }

    public void setAreaSqKm(String areaSqKm) {
        this.areaSqKm = areaSqKm;
    }

    public String getHeadquarter() {
        return headquarter;
    }

    public void setHeadquarter(String headquarter) {
        this.headquarter = headquarter;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public static class District {

        @SerializedName("area_sq_km")
        @Expose
        private String areaSqKm;
        @SerializedName("headquarter")
        @Expose
        private String headquarter;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("municipalityList")
        @Expose
        private List<Municipality> municipalityList;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("province_id")
        @Expose
        private Integer provinceId;
        @SerializedName("website")
        @Expose
        private String website;

        public String getAreaSqKm() {
            return areaSqKm;
        }

        public void setAreaSqKm(String areaSqKm) {
            this.areaSqKm = areaSqKm;
        }

        public String getHeadquarter() {
            return headquarter;
        }

        public void setHeadquarter(String headquarter) {
            this.headquarter = headquarter;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public List<Municipality> getMunicipalityList() {
            return municipalityList;
        }

        public void setMunicipalityList(List<Municipality> municipalityList) {
            this.municipalityList = municipalityList;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(Integer provinceId) {
            this.provinceId = provinceId;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }


        public static class Municipality {

            @SerializedName("area_sq_km")
            @Expose
            private String areaSqKm;
            @SerializedName("category_id")
            @Expose
            private Integer categoryId;
            @SerializedName("district_id")
            @Expose
            private Integer districtId;
            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("wards")
            @Expose
            private String wards;
            @SerializedName("website")
            @Expose
            private String website;

            public String getAreaSqKm() {
                return areaSqKm;
            }

            public void setAreaSqKm(String areaSqKm) {
                this.areaSqKm = areaSqKm;
            }

            public Integer getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(Integer categoryId) {
                this.categoryId = categoryId;
            }

            public Integer getDistrictId() {
                return districtId;
            }

            public void setDistrictId(Integer districtId) {
                this.districtId = districtId;
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

            public String getWards() {
                return wards;
            }

            public void setWards(String wards) {
                this.wards = wards;
            }

            public String getWebsite() {
                return website;
            }

            public void setWebsite(String website) {
                this.website = website;
            }

        }

    }
}
