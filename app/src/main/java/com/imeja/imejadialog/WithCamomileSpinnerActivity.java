package com.imeja.imejadialog;

import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.kiprotich.japheth.Imejadialog.ImejaSpinner;
import com.kiprotich.japheth.Imejadialog.utils.DialogUtils;


/**
 * CamomileSpinner  examples
 */

public class WithCamomileSpinnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.with_spinner_activity);

        LinearLayout activity_main =  findViewById(R.id.rootView);

        //Creation from xml
        ImejaSpinner spinner1 =  findViewById(R.id.spinner1);
        spinner1.start();
        ImejaSpinner spinner2 =  findViewById(R.id.spinner2);
        spinner2.start();

        //Change properties on the fly
        ImejaSpinner spinner3 =  findViewById(R.id.spinner3);
        spinner3.start();
        spinner3.recreateWithParams(
                WithCamomileSpinnerActivity.this,
                DialogUtils.getColor(this, R.color.colorYellow),
                120,
                true
        );

        //Creation programmatically
        ImejaSpinner spinner4 = new ImejaSpinner(
                WithCamomileSpinnerActivity.this,
                DialogUtils.getColor(this, R.color.colorGreen),
                ImejaSpinner.DEFAULT_DURATION,
                false
        );
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            params.setMargins(
                    (int) getResources().getDimension(R.dimen.camomile_spinner_margin),
                    (int) getResources().getDimension(R.dimen.camomile_spinner_margin),
                    (int) getResources().getDimension(R.dimen.camomile_spinner_margin),
                    (int) getResources().getDimension(R.dimen.camomile_spinner_margin));
        }
        spinner4.setLayoutParams(params);
        spinner4.start();
        activity_main.addView(spinner4);

    }

}
