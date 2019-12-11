package fpt.edu.cocshop.Custom;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import androidx.annotation.Nullable;


public class AlphaTextView extends AppCompatTextView {

    public AlphaTextView(Context context) {
        super(context);
    }

    public AlphaTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlphaTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onSetAlpha(int alpha) {
        setTextColor(getTextColors().withAlpha(alpha));
        setHintTextColor(getHintTextColors().withAlpha(alpha));
        setLinkTextColor(getLinkTextColors().withAlpha(alpha));
        if (getBackground() != null) {
            getBackground().setAlpha(alpha);
        }
        return true;
    }
}