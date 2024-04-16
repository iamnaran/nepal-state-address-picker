package com.iamnaran.nepaladdresspicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.iamnaran.nepaladdress.NepalAddressPicker;
import com.iamnaran.nepaladdress.helper.AddressPickerListener;
import com.iamnaran.nepaladdress.model.SelectedNepalAddress;

public class MainActivity extends AppCompatActivity {


    private AppCompatButton btnPicker;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPicker = findViewById(R.id.appCompatButton);

        textView = findViewById(R.id.title);
        btnPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NepalAddressPicker.openNepalAddressPickerDialog(MainActivity.this, new AddressPickerListener() {
                    @Override
                    public void onAddressSelected(SelectedNepalAddress selectedNepalAddress) {

                        SelectedNepalAddress data = selectedNepalAddress;

                        textView.setText("  " + data.getProvince().getName() + "--" + data.getDistrict().getName() + "--" + data.getMunicipality().getName());

                        Log.e("TAG", "onAddressSelected: " + data.getProvince().getName());
                        Log.e("TAG", "onAddressSelected: " + data.getDistrict().getName());
                        Log.e("TAG", "onAddressSelected: " + data.getMunicipality().getName());

                    }
                });
            }
        });


    }
}