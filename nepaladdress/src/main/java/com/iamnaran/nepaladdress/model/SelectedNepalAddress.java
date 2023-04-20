package com.iamnaran.nepaladdress.model;

public class SelectedNepalAddress {

    private ProvinceOfNepal province;

    private ProvinceOfNepal.District district;
    private ProvinceOfNepal.District.Municipality municipality;

    public ProvinceOfNepal getProvince() {
        return province;
    }

    public void setProvince(ProvinceOfNepal province) {
        this.province = province;
    }

    public ProvinceOfNepal.District getDistrict() {
        return district;
    }

    public void setDistrict(ProvinceOfNepal.District district) {
        this.district = district;
    }

    public ProvinceOfNepal.District.Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(ProvinceOfNepal.District.Municipality municipality) {
        this.municipality = municipality;
    }
}
