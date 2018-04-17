package com.example.jsonlogindemo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashMap;

import static com.example.jsonlogindemo.Constants.BASE_URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment implements View.OnClickListener ,MyAsyncCallBack{
    EditText etUsername, etEmail, etPassword;
    RadioGroup radioGender;
    Button btnRegister;
    ProgressBar pg;
    Context context;
    private View view;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_registration, container, false);

        context=getActivity();
        pg=view.findViewById(R.id.pg);
        etUsername=view.findViewById(R.id.etUsername);
        etEmail=view.findViewById(R.id.etEmail);
        etPassword=view.findViewById(R.id.etPassword);
        radioGender=view.findViewById(R.id.radioGender);
        btnRegister=view.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        String username=etUsername.getText().toString();
        String email=etEmail.getText().toString();
        String password=etPassword.getText().toString();
        String gender=((RadioButton)view.findViewById(radioGender.getCheckedRadioButtonId())).getText().toString();

        if(TextUtils.isEmpty(username)){
            etUsername.setText("Enter the username");
            etUsername.requestFocus();
            return;
        }


        HashMap<String,String> hm=new HashMap<>();
        hm.put("username",username);
        hm.put("password",password);
        hm.put("email",email);
        hm.put("gender",gender);

        String url=BASE_URL+"registration.php";


        MyAsyncTask myAsyncTask=new MyAsyncTask(this,hm,100);
    //    myAsyncTask.setHashMap(hm);
        myAsyncTask.execute(url);
    }

    @Override
    public void CallBack(String s, int flag) {
       // Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();

        Fragment fragment=new LoginFragment();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.Frame_Layout,fragment)
                .commit();
    }
}
