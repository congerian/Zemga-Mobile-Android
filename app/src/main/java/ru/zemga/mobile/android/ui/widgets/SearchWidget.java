package ru.zemga.mobile.android.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import ru.zemga.mobile.android.R;

public class SearchWidget extends ConstraintLayout
{

    public SearchWidget(Context context)
    {
        this(context, null);
    }

    public SearchWidget(Context context, @Nullable AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public SearchWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.search_widget, this);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();

    }



}
