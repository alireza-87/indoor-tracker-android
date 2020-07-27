package com.midnightgeek.indoortracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.midnightgeek.indoortracker.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {
    public static final String TAG="LoginFragment";
    private FragmentLoginBinding binding;
    public static LoginFragment getInstance(){
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false);
        initClick();
        return binding.getRoot();
    }

    private void initClick(){
        binding.btnEnter.setOnClickListener(view -> {
            if (binding.edtEmail.getText()==null || binding.edtPass.getText()==null){
                Toast.makeText(getContext(),"Fill the email/pass ",Toast.LENGTH_LONG).show();
            }else {
                login(binding.edtEmail.getText().toString(),binding.edtPass.getText().toString());
            }
        });
    }

    private void login(String email,String password){

        ApiHandler.getInstance().login(email, password, new ApiCallback() {
            @Override
            public void onSuccess(Object data) {
                PrefHandler prefHandler=new PrefHandler(getContext());
                prefHandler.init();
                prefHandler.setPreference(prefHandler.getTAG_USER_ID(),((LoginUser)data).getUid());
                prefHandler.setPreference(prefHandler.getTAG_USER_NAME(),((LoginUser)data).getName());
                prefHandler.setPreference(prefHandler.getTAG_USER_EMAIL(),((LoginUser)data).getEmail());
                prefHandler.setPreference(prefHandler.getTAG_USER_RULE(),((LoginUser)data).getRule());
                prefHandler.setPreference(prefHandler.getTAG_USER_SURENAME(),((LoginUser)data).getSurename());
                prefHandler.setPreference(prefHandler.getTAG_USER_TOKEN(),((LoginUser)data).getTokenid());
                prefHandler.setPreference(prefHandler.getTAG_USER_TELL(),((LoginUser)data).getTell());
                prefHandler.setPreference(prefHandler.getTAG_USER_PASS(),((LoginUser)data).getPassword());
                Mqtt.getInstance().init(((LoginUser)data).getUid(),getContext());
                ((MainActivity)getActivity()).openMainFragment();

            }

            @Override
            public void onFail() {

            }
        });

    }

}
