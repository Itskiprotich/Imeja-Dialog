package com.kiprotich.japheth.Imejadialog;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.ColorInt;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.kiprotich.japheth.Imejadialog.utils.DialogUtils;


//TODO:: try to extends from view
public class ImejaSpinner extends AppCompatImageView {

    public static final int DEFAULT_DURATION = 60;
    @ColorInt
    public static final int DEFAULT_COLOR;
    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            DEFAULT_COLOR = Resources.getSystem().getColor(android.R.color.white, null);
        } else {
            DEFAULT_COLOR = Resources.getSystem().getColor(android.R.color.white);
        }
    }
    public static final boolean DEFAULT_CLOCKWISE = true;

    private int spinnerColor;
    private int duration;
    private boolean clockwise = DEFAULT_CLOCKWISE;

    public ImejaSpinner(Context context) {
        super(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.setBackground(ContextCompat.getDrawable(context, R.drawable.spinner));
        } else {
            this.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.spinner));
        }
    }

    public ImejaSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCustomAttr(context, attrs);
    }

    public ImejaSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCustomAttr(context, attrs);
    }


    public ImejaSpinner(Context context, @ColorInt int spinnerColor) {
        this(context, spinnerColor, DEFAULT_DURATION);
    }

    public ImejaSpinner(Context context, @ColorInt int spinnerColor, int duration) {
        this(context, spinnerColor, duration, DEFAULT_CLOCKWISE);
    }

    public ImejaSpinner(Context context, @ColorInt int spinnerColor, int duration, boolean clockwise) {
        super(context);
        this.spinnerColor = spinnerColor;
        this.duration = duration;
        this.clockwise = clockwise;
        AnimationDrawable spinnerAnimation = createSpinner(context, spinnerColor, duration, clockwise);
        updateSpinner(spinnerAnimation);
    }

    private void initCustomAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ImejaSpinner,
                0, 0);
        try {
            duration = typedArray.getInteger(R.styleable.ImejaSpinner_duration, DEFAULT_DURATION);
            spinnerColor = typedArray.getColor(R.styleable.ImejaSpinner_spinnerColor, DEFAULT_COLOR);
            clockwise = typedArray.getBoolean(R.styleable.ImejaSpinner_clockwise, DEFAULT_CLOCKWISE);
            AnimationDrawable spinnerAnimation = createSpinner(context, spinnerColor, duration, clockwise);
            updateSpinner(spinnerAnimation);
        } finally {
            typedArray.recycle();
        }
    }

    private AnimationDrawable createSpinner(Context context, @ColorInt int spinnerColor, int duration, boolean clockwise) {
        if (spinnerColor == DEFAULT_COLOR && duration == DEFAULT_DURATION && clockwise == DEFAULT_CLOCKWISE) {
            //create from xml if default params
            return DialogUtils.createAnimation(context);
        } else {
            //create programmatically if params is not default
            return DialogUtils.createAnimation(context, spinnerColor, duration, clockwise);
        }
    }

    private void updateSpinner(AnimationDrawable spinnerAnimation) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.setBackground(spinnerAnimation);
        } else {
            this.setBackgroundDrawable(spinnerAnimation);
        }
    }

    public void start() {
        ((AnimationDrawable) this.getBackground()).start();
    }

    public void stop() {
        ((AnimationDrawable)this.getBackground()).stop();
    }

    public void recreateWithParams(Context context, @ColorInt int spinnerColor, int duration, boolean clockwise) {
        boolean wasRunning = false;
        boolean oneShot = isOneShot();

        if (((AnimationDrawable)this.getBackground()).isRunning()) {
            wasRunning = true;
            stop();
        }

        AnimationDrawable newSpinner = createSpinner(context, spinnerColor, duration, clockwise);
        if (newSpinner != null) {
            if (oneShot) newSpinner.setOneShot(true);
            this.spinnerColor = spinnerColor;
            this.duration = duration;
            this.clockwise = clockwise;
            updateSpinner(newSpinner);
        }

        if (wasRunning) start();
    }

    public void setOneShot(boolean oneShot) {
        ((AnimationDrawable) this.getBackground()).setOneShot(oneShot);
    }

    public boolean isOneShot() {
        return ((AnimationDrawable) this.getBackground()).isOneShot();
    }

}
