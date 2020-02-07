package com.kiprotich.japheth.Imejadialog;

import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.UiThread;

import com.kiprotich.japheth.Imejadialog.utils.DialogUtils;


public class DialogInit {


    @LayoutRes
    static int getInflateLayout(ImejaDialog.Builder builder) {
        if (builder.customView != null) {
            return -1;
            //TODO:: realize custom view
//            return R.layout.md_dialog_custom;
        } else {
            return R.layout.ios_progress_dialog;
        }
    }


    @UiThread
    public static void init(final ImejaDialog dialog) {
        final ImejaDialog.Builder builder = dialog.builder;

        // Retrieve references to views
        dialog.titleFrame = (LinearLayout) dialog.rootView.findViewById(R.id.title_frame);
        dialog.titleIcon = (ImageView) dialog.rootView.findViewById(R.id.title_icon);
        dialog.title = (TextView) dialog.rootView.findViewById(R.id.title_text);
        dialog.spinner = (ImejaSpinner) dialog.rootView.findViewById(R.id.spinner);
        dialog.message = (TextView) dialog.rootView.findViewById(R.id.message);

        // Set cancelable flag
        dialog.setCancelable(builder.cancelable);
        // Set dialog background color //TODO:: back not worked
        if (builder.isBackgroundColorSet) {
            GradientDrawable drawable = new GradientDrawable();
            drawable.setCornerRadius(
                    builder.context.getResources().getDimension(R.dimen.ios_bg_corner_radius)
            );
            drawable.setColor(builder.backgroundColor);
            dialog.getWindow().setBackgroundDrawable(drawable);
        } else {
            builder.backgroundColor = DialogUtils
                    .getColor(builder.context, R.color.dark_gray_background);
        }

        // Retrieve default title colors
        if (!builder.isTitleColorSet) {
            builder.titleColor = DialogUtils
                    .getColor(builder.context, R.color.standard_white);
        }
        if (!builder.isMessageColorSet) {
            builder.messageColor = DialogUtils
                    .getColor(builder.context, R.color.standard_white);
        }

        setupTitle(dialog, builder);
        setupSpinner(dialog, builder);
        setupMessageContent(dialog, builder);
        setupListeners(dialog, builder);


        dialog.setContentView(dialog.rootView);
    }

    private static void setupTitle(ImejaDialog dialog, ImejaDialog.Builder builder) {
        if (dialog.title != null) {
            //TODO;; uncomment when add supporting
//            dialog.setTypeface(dialog.title, builder.mediumFont);
            dialog.title.setTextColor(builder.titleColor);
            dialog.title.setGravity(builder.titleGravity);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                //noinspection ResourceType
                dialog.title.setTextAlignment(builder.titleGravity);
            }

            if (builder.title == null) {
                dialog.titleFrame.setVisibility(View.GONE);
            } else {
                dialog.title.setText(builder.title);
                dialog.titleFrame.setVisibility(View.VISIBLE);
            }
        }
    }

    private static void setupSpinner(ImejaDialog dialog, ImejaDialog.Builder builder) {
        if (builder.spinnerColor == 0) builder.spinnerColor = ImejaSpinner.DEFAULT_COLOR;
        if (builder.spinnerDuration == 0) builder.spinnerDuration = ImejaSpinner.DEFAULT_DURATION;
        if (builder.oneShot) dialog.spinner.setOneShot(true);
        dialog.spinner.recreateWithParams(
                builder.context,
                builder.spinnerColor,
                builder.spinnerDuration,
                builder.spinnerClockwise
        );
    }

    private static void setupListeners(ImejaDialog dialog, ImejaDialog.Builder builder) {
        // Setup user listeners
        if (builder.showListener != null) {
            dialog.setOnShowListener(builder.showListener);
        }
        if (builder.cancelListener != null) {
            dialog.setOnCancelListener(builder.cancelListener);
        }
        if (builder.dismissListener != null) {
            dialog.setOnDismissListener(builder.dismissListener);
        }
        if (builder.keyListener != null) {
            dialog.setOnKeyListener(builder.keyListener);
        }
    }

    private static void setupMessageContent(ImejaDialog dialog, ImejaDialog.Builder builder) {
        // Setup content
        if (dialog.message != null) {
            dialog.message.setMovementMethod(new LinkMovementMethod());
//            dialog.setTypeface(dialog.message, builder.regularFont);
//            dialog.message.setLineSpacing(0f, builder.contentLineSpacingMultiplier);
//            if (builder.linkColor == null) {
//                dialog.message.setLinkTextColor(
//                        DialogUtils.resolveColor(dialog.getContext(), android.R.attr.textColorPrimary));
//            } else {
//                dialog.message.setLinkTextColor(builder.linkColor);
//            }
            dialog.message.setTextColor(builder.messageColor);
            dialog.message.setGravity(builder.messageGravity);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                //noinspection ResourceType
                dialog.message.setTextAlignment(builder.messageGravity);
            }

            if (builder.message != null) {
                dialog.message.setText(builder.message);
                dialog.message.setVisibility(View.VISIBLE);
            } else {
                dialog.message.setVisibility(View.GONE);
            }
        }
    }
}
