package com.iamnaran.nepaladdress;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.iamnaran.nepaladdress.helper.AddressPickerListener;
import com.iamnaran.nepaladdress.ui.AddressPickerDialog;

public class NepalAddressPicker {

    public static void openNepalAddressPickerDialog(Context context, AddressPickerListener addressPickerListener){
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        AddressPickerDialog bottomSheetFragment = new AddressPickerDialog(addressPickerListener);
        bottomSheetFragment.show(fragmentManager, bottomSheetFragment.getTag());
    }
}
