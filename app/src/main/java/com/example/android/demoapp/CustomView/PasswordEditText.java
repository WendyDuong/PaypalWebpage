package com.example.android.demoapp.CustomView;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.android.demoapp.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Lenovo S410p on 6/29/2016.
 */
public class PasswordEditText extends androidx.appcompat.widget.AppCompatEditText {
    private TextInputLayout textInputLayout ;
    Drawable eye,eyeStrike;
    Boolean visible = false;
    Boolean useStrike = false;
    Boolean useValidate = false;
    Drawable drawable;
    int ALPHA = (int) (255 * .70f);
    String MATCHER_PATTERN = "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,20})"; // (?=.*\d)
    Pattern pattern;
    Matcher matcher;

    public PasswordEditText(Context context) {
        super(context);
        khoitao(null);

    }



    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoitao(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        khoitao(attrs);
    }

    private void khoitao(AttributeSet attrs){

        this.pattern = Pattern.compile(MATCHER_PATTERN);
        if(attrs != null){
            TypedArray array = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.PasswordEditText,0,0);
            this.useStrike = array.getBoolean(R.styleable.PasswordEditText_useStrike,false);
            this.useValidate = array.getBoolean(R.styleable.PasswordEditText_useValidate,false);
        }
        eye = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black_24dp).mutate();
        eyeStrike = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black_24dp).mutate();

        if(this.useValidate){
            setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if(!b) {
                        String chuoi = getText().toString();
                        textInputLayout = (TextInputLayout) view.getParent().getParent();
                        matcher = pattern.matcher(chuoi);
                        if(!matcher.matches()){
                            textInputLayout.setErrorEnabled(true);
                            textInputLayout.setError("Mật khẩu phải bao gồm 6 ký tự và một chữ hoa");
                        }else{
                            textInputLayout.setErrorEnabled(false);
                            textInputLayout.setError("");
                        }


                    }
                }
            });
        }
        caidat();
    }

    private void caidat(){
        setInputType(InputType.TYPE_CLASS_TEXT |(visible? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        Drawable[] drawables = getCompoundDrawables();
        drawable = useStrike && !visible? eyeStrike : eye;
        drawable.setAlpha(ALPHA);
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP && event.getX() >= (getRight() - drawable.getBounds().width()) ){
            visible = !visible;
            caidat();
            invalidate();
        }
        return super.onTouchEvent(event);
    }

}
