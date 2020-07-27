package com.midnightgeek.indoortracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.midnightgeek.indoortracker.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;

    public static MainFragment getInstance(){
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false);
        PrefHandler prefHandler=new PrefHandler(getContext());
        prefHandler.init();

        Mqtt.getInstance().init(prefHandler.getString(prefHandler.getTAG_USER_ID(),""),getContext());
        binding.lblName.setText(prefHandler.getString(prefHandler.getTAG_USER_NAME(),""));
        binding.lblEmaill.setText(prefHandler.getString(prefHandler.getTAG_USER_EMAIL(),""));
        binding.btnLogut.setOnClickListener(view -> {
            getActivity().onBackPressed();
            prefHandler.clear();
        });
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
