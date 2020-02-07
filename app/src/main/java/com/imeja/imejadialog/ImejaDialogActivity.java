package com.imeja.imejadialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.kiprotich.japheth.Imejadialog.ImejaDialog;


/**
 * IOSDialog examples
 */
public class ImejaDialogActivity extends AppCompatActivity implements View.OnClickListener {

    Button defaultDialog, titleDialog, messageDialog;
    ImejaDialog dialog0, dialog1, dialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defaultDialog = findViewById(R.id.defaultDialog);
        titleDialog = findViewById(R.id.titleDialog);
        messageDialog = findViewById(R.id.messageDialog);

        /*actions*/
        defaultDialog.setOnClickListener(this);
        titleDialog.setOnClickListener(this);
        messageDialog.setOnClickListener(this);

        dialog0 = new ImejaDialog.Builder(ImejaDialogActivity.this)
                .setTitle("Default Imeja Dialog bar")
                .setTitleColorRes(R.color.gray)
                .setCancelable(true)
                .build();


        dialog1 = new ImejaDialog.Builder(ImejaDialogActivity.this)
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                    }
                })

                .setSpinnerColorRes(R.color.colorGreen)
                .setMessageColorRes(R.color.colorAccent)
                .setTitle(R.string.standard_title)
                .setTitleColorRes(R.color.colorPrimary)
                .setMessageContent(getString(R.string.standard_message))
                .setCancelable(true)
                .setMessageContentGravity(Gravity.END)
                .build();

        dialog2 = new ImejaDialog.Builder(ImejaDialogActivity.this)
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                    }
                })
                .setSpinnerColorRes(R.color.ios_gray_text)
                .setMessageColorRes(R.color.primaryDark)
                .setTitleColorRes(R.color.accent)
                .setMessageContent("My message")
                .setSpinnerClockwise(false)
                .setSpinnerDuration(120)
                .setMessageContentGravity(Gravity.END)
                .setOneShot(false)
                .setCancelable(true)
                .build();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.defaultDialog:
                dialog0.show();
                break;
            case R.id.titleDialog:
                dialog2.show();
                break;
            case R.id.messageDialog:
                dialog1.show();
                break;
        }
    }
}
