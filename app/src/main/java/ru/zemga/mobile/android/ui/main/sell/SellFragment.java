package ru.zemga.mobile.android.ui.main.sell;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.zemga.mobile.android.databinding.SellFragmentBinding;
import ru.zemga.mobile.android.ui.main.MainActivity;

public class SellFragment extends Fragment
{
    SellFragmentBinding binding;

    @Override
    public void onStart ()
    { super.onStart(); }

    @Override
    public void onPause ()
    {
        super.onPause();
        MainActivity activity = (MainActivity) getActivity();
        activity.hideNavBar();
    }

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        MainActivity activity = (MainActivity) getActivity();
        activity.showNavBar();
    }

}