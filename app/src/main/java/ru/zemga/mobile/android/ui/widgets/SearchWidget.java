package ru.zemga.mobile.android.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import ru.zemga.mobile.android.R;
import ru.zemga.mobile.android.ui.main.search.SearchFragment;

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
        this(context, attrs, defStyleAttr, R.style.Theme_Zemga_SearchWidget);
    }

    public SearchWidget(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context)
    {
        inflateLayout(context);
        setClickListener(context);
    }

    private void inflateLayout(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.search_widget, this);
    }

    private void setClickListener(Context context)
    {
        setOnClickListener((view) ->
       {
           SearchFragment fragment = new SearchFragment();
           WidgetUtils.getActivity(context).getSupportFragmentManager().beginTransaction()
                   .replace(R.id.fragment_host, fragment)
                   .addToBackStack(null)
                   .commit();
           System.out.println("Создаем новый");
       });
    }

    protected void onFinishInflate() {
        super.onFinishInflate();

    }



}
