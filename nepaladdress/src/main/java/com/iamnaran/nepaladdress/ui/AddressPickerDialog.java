package com.iamnaran.nepaladdress.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iamnaran.nepaladdress.R;
import com.iamnaran.nepaladdress.helper.AddressPickerListener;
import com.iamnaran.nepaladdress.helper.ShowAlert;
import com.iamnaran.nepaladdress.model.ProvinceOfNepal;
import com.iamnaran.nepaladdress.model.SelectedNepalAddress;
import com.iamnaran.nepaladdress.utils.AddressDataUtils;
import com.iamnaran.nepaladdress.utils.AddressPickerSpinnerAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AddressPickerDialog extends BottomSheetDialogFragment implements AddressPickerListener {


    private final AddressPickerListener addressPickerListener;

    private List<ProvinceOfNepal> provinceOfNepalAddressesList;

    private Spinner provinceSpinner;
    private ArrayAdapter<String> provinceSpinnerAdapter;

    private Spinner districtSpinner;
    private ArrayAdapter<String> districtSpinnerAdapter;

    private Spinner municipalitySpinner;
    private ArrayAdapter<String> municipalitySpinnerAdapter;

    private MaterialCardView btnSave;
    private CoordinatorLayout coordinatorLayout;


    public AddressPickerDialog(AddressPickerListener addressPickerListener) {
        this.addressPickerListener = addressPickerListener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.address_picker_dialog, container, false);
        setUpViews(view);
        parseAddress();
        if (provinceOfNepalAddressesList != null) {
            plotNepalAddress();
        }
        initListeners();

        return view;
    }

    private void initListeners() {
        btnSave.setOnClickListener(view -> validateSpinnerAndSubmitData());
    }

    private void validateSpinnerAndSubmitData() {

        if (provinceSpinner.getSelectedItemPosition() == 0
                || districtSpinner.getSelectedItemPosition() == 0
                || municipalitySpinner.getSelectedItemPosition() == 0) {

            ShowAlert.showSnackBarMessage(coordinatorLayout, "Please select to submit", ShowAlert.AlertType.ERROR);
            return;
        }

        if (addressPickerListener != null) {

            String selectedProvinceName = provinceSpinner.getSelectedItem().toString();
            String selectedDistrictName = districtSpinner.getSelectedItem().toString();
            String selectedMunicipalityName = municipalitySpinner.getSelectedItem().toString();

            ProvinceOfNepal province = AddressDataUtils.getProvinceById(selectedProvinceName, provinceOfNepalAddressesList);
            if (province == null) {
                ShowAlert.showSnackBarMessage(coordinatorLayout, "Province not found", ShowAlert.AlertType.ERROR);

                return;
            }
            ProvinceOfNepal.District district = AddressDataUtils.getDistrictById(selectedDistrictName, province);
            if (district == null) {
                ShowAlert.showSnackBarMessage(coordinatorLayout, "District not found", ShowAlert.AlertType.ERROR);

                return;
            }
            ProvinceOfNepal.District.Municipality municipality = AddressDataUtils.getMunicipalityById(selectedMunicipalityName, district);
            if (municipality == null) {
                ShowAlert.showSnackBarMessage(coordinatorLayout, "Municipality not found", ShowAlert.AlertType.ERROR);

                return;
            }
            SelectedNepalAddress selectedNepalAddress = new SelectedNepalAddress();
            selectedNepalAddress.setProvince(province);
            selectedNepalAddress.setDistrict(district);
            selectedNepalAddress.setMunicipality(municipality);
            addressPickerListener.onAddressSelected(selectedNepalAddress);

            try {
                dismiss();
            }catch (Exception e){
                e.printStackTrace();
            }

        }


    }

    private void parseAddress() {
        provinceOfNepalAddressesList = parseAllNepalAddressMunicipalitiesAddress();
    }


    private void plotNepalAddress() {
        setUpProvinceSpinner(provinceOfNepalAddressesList);


    }

    private void setUpViews(View view) {
        provinceSpinner = view.findViewById(R.id.province_spinner);
        districtSpinner = view.findViewById(R.id.district_spinner);
        municipalitySpinner = view.findViewById(R.id.municipality_spinner);
        btnSave = view.findViewById(R.id.cv_submit);
        coordinatorLayout = view.findViewById(R.id.coordinator_layout);


        // province
        provinceSpinnerAdapter = new AddressPickerSpinnerAdapter(requireActivity()).customProvinceSpinnerAdapter(new ArrayList<>());
        provinceSpinnerAdapter.add("Select Province");
        provinceSpinner.setAdapter(provinceSpinnerAdapter);


        // district
        districtSpinnerAdapter = new AddressPickerSpinnerAdapter(requireActivity()).customDistrictSpinnerAdapter(new ArrayList<>());
        districtSpinnerAdapter.add("Select District");
        districtSpinner.setAdapter(districtSpinnerAdapter);


        // municipality
        municipalitySpinnerAdapter = new AddressPickerSpinnerAdapter(requireActivity()).customMunicipalitySpinnerAdapter(new ArrayList<>());
        municipalitySpinnerAdapter.add("Select Municipality");
        municipalitySpinner.setAdapter(municipalitySpinnerAdapter);

    }

    private void setUpProvinceSpinner(List<ProvinceOfNepal> provinceOfNepals) {

        provinceSpinnerAdapter.clear();
        provinceSpinnerAdapter.add("Select Province");

        List<String> dataList = AddressDataUtils.getAllProvinceNames(provinceOfNepals);
        for (String data : dataList) {
            provinceSpinnerAdapter.add(data);
        }
        provinceSpinnerAdapter.notifyDataSetChanged();

        provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    clearDistrictAdapter(true);
                    clearMunicipalityAdapter(true);
                    doPlotDistrictAndMunicipalityWork(provinceOfNepalAddressesList.get(i - 1));

                } else {
                    clearDistrictAdapter(true);
                    clearMunicipalityAdapter(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void doPlotDistrictAndMunicipalityWork(ProvinceOfNepal provinceOfNepal) {

        clearDistrictAdapter(false);

        List<String> dataList = AddressDataUtils.getAllDistrictNames(provinceOfNepal);
        Collections.sort(dataList);
        for (String data : dataList) {
            districtSpinnerAdapter.add(data);
        }
        districtSpinnerAdapter.notifyDataSetChanged();


        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    clearMunicipalityAdapter(true);
                    doPlotMunicipalityList(AddressDataUtils.getDistrictById(adapterView.getSelectedItem().toString(), provinceOfNepal));
                } else {
                    clearMunicipalityAdapter(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void doPlotMunicipalityList(ProvinceOfNepal.District district) {

        clearMunicipalityAdapter(false);

        List<String> municipalityList = AddressDataUtils.getAllMunicipalityList(district);
        Collections.sort(municipalityList);
        for (String data : municipalityList) {
            municipalitySpinnerAdapter.add(data);
        }
        municipalitySpinnerAdapter.notifyDataSetChanged();

    }


    public void clearMunicipalityAdapter(boolean isZeroPositionSelected) {
        municipalitySpinnerAdapter.clear();
        municipalitySpinnerAdapter.add("Select Municipality");
        if (isZeroPositionSelected) {
            municipalitySpinner.setSelection(0);
        }
    }

    public void clearDistrictAdapter(boolean isZeroPositionSelected) {
        districtSpinnerAdapter.clear();
        districtSpinnerAdapter.add("Select District");
        if (isZeroPositionSelected) {
            districtSpinner.setSelection(0);
        }
    }


    private List<ProvinceOfNepal> parseAllNepalAddressMunicipalitiesAddress() {
        String jsonOfNepalAddress = loadAllNepalAddressFromAsset();
        if (jsonOfNepalAddress != null) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<ProvinceOfNepal>>() {
            }.getType();
            return gson.fromJson(jsonOfNepalAddress, listType);
        }
        return null;

    }


    public String loadAllNepalAddressFromAsset() {
        String json = null;
        try {
            InputStream is = getResources().openRawResource(
                    getResources().getIdentifier("nepal_address",
                            "raw", requireActivity().getPackageName()));
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    @Override
    public void onAddressSelected(SelectedNepalAddress provinceOfNepal) {

    }
}