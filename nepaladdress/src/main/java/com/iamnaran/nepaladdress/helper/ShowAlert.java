/*
 * Created by NaRan on 8/28/21, 8:29 PM
 * Last modified 8/28/21, 8:29 PM
 * Copyright (c) 2021 .
 * TreeLeaf AI
 * All rights reserved.
 *
 */

package com.iamnaran.nepaladdress.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.androidadvance.topsnackbar.TSnackbar;
import com.iamnaran.nepaladdress.R;



/*
 * Show Alert Message
 * SUCCESS, ERROR & INFORMATION TYPE
 */

public class ShowAlert {

    public enum AlertType {
        SUCCESS,
        ERROR,
        INFORMATION
    }

    public static final int iconSizeInDP = 14;


    @SuppressLint("UseCompatLoadingForDrawables")
    public static void showSnackBarMessage(View rootView, String message, AlertType alertType) {
        if(message == null || TextUtils.isEmpty(message)) return;
        TSnackbar snack = TSnackbar
                .make(rootView, message, TSnackbar.LENGTH_LONG);
        snack.setActionTextColor(Color.WHITE);
        View snackView = snack.getView();

        //alert colors
        int backGroundColor = getBackgroundColorFromType(rootView, alertType);
        int textColor = getTextColorFromType(rootView, alertType);
        switch (alertType) {
            case ERROR:
                snack.setIconLeft(R.drawable.ic_inbox_alert_error_24_dp, iconSizeInDP);
                break;

            case SUCCESS:
                snack.setIconLeft(R.drawable.ic_inbox_alert_success_24_dp, iconSizeInDP);

                break;

            default:
                snack.setIconLeft(R.drawable.ic_inbox_alert_info_24_dp, iconSizeInDP);

        }
        snack.setIconPadding(15);
        // to set layout gravity center
        TSnackbar.SnackbarLayout snackBarView = (TSnackbar.SnackbarLayout) snack.getView();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) snackBarView.getChildAt(0).getLayoutParams();
        params.gravity = Gravity.CENTER_VERTICAL;
        snackBarView.getChildAt(0).setLayoutParams(params);

        // to set background with radius
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(rootView.getContext(), R.drawable.a_inbox_alert_background);
        assert unwrappedDrawable != null;
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, backGroundColor);

        // to set margin for view
//        FrameLayout.LayoutParams snackViewParams = (FrameLayout.LayoutParams) snackView.getLayoutParams();
//        snackViewParams.setMargins(50, getStatusBarHeight(rootView.getContext()), 50, 10);
//        snackView.setLayoutParams(snackViewParams);
        snackView.setBackground(wrappedDrawable);

        // using default
        TextView textView = snackView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//        } else {
//            textView.setGravity(Gravity.CENTER);
//        }
        textView.setTextSize(12.0f);
        textView.setTextColor(textColor);
        snack.show();

    }

    public static void showSnackBarMessageInDialog(View rootView, String message, AlertType alertType) {


        TSnackbar snack = TSnackbar
                .make(rootView, message, TSnackbar.LENGTH_LONG);
        snack.setActionTextColor(Color.WHITE);
        View snackView = snack.getView();

        //alert colors
        int backGroundColor = getBackgroundColorFromType(rootView, alertType);
        int textColor = getTextColorFromType(rootView, alertType);
        switch (alertType) {
            case ERROR:
                snack.setIconLeft(R.drawable.ic_inbox_alert_error_24_dp, iconSizeInDP);
                break;

            case SUCCESS:
                snack.setIconLeft(R.drawable.ic_inbox_alert_success_24_dp, iconSizeInDP);

                break;

            default:
                snack.setIconLeft(R.drawable.ic_inbox_alert_info_24_dp, iconSizeInDP);

        }
        snack.setIconPadding(15);
        // to set layout gravity center
        TSnackbar.SnackbarLayout snackBarView = (TSnackbar.SnackbarLayout) snack.getView();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) snackBarView.getChildAt(0).getLayoutParams();
        params.gravity = Gravity.CENTER_VERTICAL;
        snackBarView.getChildAt(0).setLayoutParams(params);

        // to set background with radius
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(rootView.getContext(), R.drawable.a_inbox_alert_background);
        assert unwrappedDrawable != null;
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, backGroundColor);

        // to set margin for view
        FrameLayout.LayoutParams snackViewParams = (FrameLayout.LayoutParams) snackView.getLayoutParams();
        snackViewParams.setMargins(50, getStatusBarHeight(rootView.getContext()) , 50, 10);
        snackView.setLayoutParams(snackViewParams);
        snackView.setBackground(wrappedDrawable);

        // using default
        TextView textView = snackView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//        } else {
//            textView.setGravity(Gravity.CENTER);
//        }
        textView.setTextSize(12.0f);
        textView.setTextColor(textColor);
        snack.show();

    }


    private static int getBackgroundColorFromType(View rootView, AlertType alertType) {

        int backGroundColor;
        switch (alertType) {
            case SUCCESS:
                backGroundColor = ContextCompat.getColor(rootView.getContext(),R.color.alert_background_success);
                break;

            case ERROR:
                backGroundColor = ContextCompat.getColor(rootView.getContext(),R.color.alert_background_error);
                break;

            case INFORMATION:
                backGroundColor =  ContextCompat.getColor(rootView.getContext(),R.color.alert_background_information);
                break;

            default:
                backGroundColor = ContextCompat.getColor(rootView.getContext(),R.color.color_text_primary);

        }

        return backGroundColor;

    }

    private static int getTextColorFromType(View rootView, AlertType alertType) {

        int textColor;
        switch (alertType) {

            case INFORMATION:
            case ERROR:
            case SUCCESS:
            default:
                textColor = rootView.getContext().getResources().getColor(R.color.white);

        }

        return textColor;

    }


    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


}
