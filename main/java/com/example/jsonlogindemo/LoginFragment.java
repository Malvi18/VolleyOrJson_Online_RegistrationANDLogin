package com.example.jsonlogindemo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener ,MyAsyncCallBack{


    EditText etUsername,etPassword;
    Button btnLogin;
     Context context;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        context=getActivity();
        etUsername=view.findViewById(R.id.etUsername);
        etPassword=view.findViewById(R.id.etPassword);
        btnLogin=view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        String username=etUsername.getText().toString();
        String password=etPassword.getText().toString();


        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("username",username);
        hashMap.put("password",password);


        MyAsyncTask myAsyncTask=new MyAsyncTask(this,hashMap,100);
      // myAsyncTask.setHashMap(hashMap);
       myAsyncTask.execute(Constants.LOGIN_URL);
    }

    @Override
    public void CallBack(String result, int flag) {
        Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();

 /* {"result":[{"id":"1","username":"mlv","email":"mlv",
            "password":"mlv","gender":"Female"}]}*/
        try {
            JSONObject jsonObject=new JSONObject(result);
            JSONArray jsonArray=jsonObject.getJSONArray("result");

         //   for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject1=jsonArray.getJSONObject(0);

                UtilityHelper utilityHelper=new UtilityHelper();
                utilityHelper.writeUser(getActivity(),jsonObject1.toString());

                Fragment fragment=new RecyclerFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Frame_Layout,fragment)
                        .commit();
            } catch (JSONException e1) {
            e1.printStackTrace();
        }



    }
}
