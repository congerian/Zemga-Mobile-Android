package ru.zemga.mobile.android.ui.main.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ru.zemga.mobile.android.databinding.HomeFragmentBinding;

public class HomeFragment extends Fragment
{
    HomeFragmentBinding binding;

    @Override
    public void onStart ()
    { super.onStart(); }

    @Override
    public void onPause ()
    { super.onPause(); }

    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = HomeFragmentBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        return root;
    }


}