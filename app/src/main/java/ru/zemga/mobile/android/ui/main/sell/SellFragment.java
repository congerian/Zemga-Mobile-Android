package ru.zemga.mobile.android.ui.main.sell;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ru.zemga.mobile.android.databinding.SellFragmentBinding;

public class SellFragment extends Fragment
{
    SellFragmentBinding binding;

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
        binding = SellFragmentBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        return root;
    }


}