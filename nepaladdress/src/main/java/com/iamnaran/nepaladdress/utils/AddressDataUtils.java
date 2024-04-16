package com.iamnaran.nepaladdress.utils;

import android.content.Context;

import com.iamnaran.nepaladdress.model.District;
import com.iamnaran.nepaladdress.model.Municipality;
import com.iamnaran.nepaladdress.model.ProvinceOfNepal;
import com.iamnaran.nepaladdress.model.Province;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddressDataUtils {

    public static List<String> getAllProvinceNames(List<ProvinceOfNepal> provinceOfNepalList) {

        List<String> provinceNameList = new ArrayList<>();
        for (ProvinceOfNepal provinceOfNepal : provinceOfNepalList) {
            provinceNameList.add(provinceOfNepal.getName());
        }
        return provinceNameList;
    }

    public static List<Province> doFilterAllJsonIntoNepalAddress(List<Province> provinceList, List<District> districtList, List<Municipality> municipalitiesList) {

        List<Province> provinces = new ArrayList<>();
        for (Province province : provinceList) {
            List<District> provinceDistrictList = getAllDistrictByProvinceId(province.getId(), districtList, municipalitiesList);
            province.setAllDistrictList(provinceDistrictList);
            provinces.add(province);
        }
        return provinces;

    }

    private static List<District> getAllDistrictByProvinceId(Integer provinceId, List<District> districtList, List<Municipality> municipalitiesList) {

        List<District> provinceDistrictList = new ArrayList<>();
        for (District district : districtList) {
            List<Municipality> municipalityList = getAllMunicipalityListByDistrictId(district.getId(), municipalitiesList);
            district.setMunicipalityList(municipalityList);
            if (provinceId.equals(district.getProvinceId())) {
                provinceDistrictList.add(district);
            }
        }
        return provinceDistrictList;

    }

    private static List<Municipality> getAllMunicipalityListByDistrictId(Integer districtId, List<Municipality> municipalityList) {

        List<Municipality> districtMunicipalityList = new ArrayList<>();
        for (Municipality municipality : municipalityList) {
            if (municipality.getDistrictId() == districtId) {
                districtMunicipalityList.add(municipality);
            }
        }
        return districtMunicipalityList;
    }


    public static void saveJson(Context context, String json) {

        File file = new File(context.getFilesDir(), "myFile.json");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getAllDistrictNames(ProvinceOfNepal provinceOfNepal) {

        List<String> districtNameList = new ArrayList<>();

        for (ProvinceOfNepal.District district : provinceOfNepal.getAllDistrictList()) {
            districtNameList.add(district.getName());
        }
        return districtNameList;
    }

    public static List<String> getAllMunicipalityList(ProvinceOfNepal.District district) {
        List<String> municipalityNameList = new ArrayList<>();

        for (ProvinceOfNepal.District.Municipality municipality : district.getMunicipalityList()) {
            municipalityNameList.add(municipality.getName());
        }
        return municipalityNameList;

    }

    public static ProvinceOfNepal getProvinceById(String selectedProvinceName, List<ProvinceOfNepal> provinceOfNepalAddressesList) {
        ProvinceOfNepal provinceOfNepal = null;
        for (ProvinceOfNepal province : provinceOfNepalAddressesList) {
            if (province.getName().equalsIgnoreCase(selectedProvinceName)) {
                provinceOfNepal = province;
            }
        }
        return provinceOfNepal;
    }

    public static ProvinceOfNepal.District getDistrictById(String selectedDistrictName, ProvinceOfNepal provinceOfNepalAddressesList) {

        ProvinceOfNepal.District provinceOfNepalDistrict = null;
        for (ProvinceOfNepal.District district : provinceOfNepalAddressesList.getAllDistrictList()) {
            if (district.getName().equalsIgnoreCase(selectedDistrictName)) {
                provinceOfNepalDistrict = district;
            }
        }


        return provinceOfNepalDistrict;
    }

    public static ProvinceOfNepal.District.Municipality getMunicipalityById(String selectedMunicipalityName, ProvinceOfNepal.District district) {

        ProvinceOfNepal.District.Municipality provinceOfNepalDistrictMunicipality = null;
        for (ProvinceOfNepal.District.Municipality municipality : district.getMunicipalityList()) {
            if (municipality.getName().equalsIgnoreCase(selectedMunicipalityName)) {
                provinceOfNepalDistrictMunicipality = municipality;
            }
        }

        return provinceOfNepalDistrictMunicipality;
    }
}
