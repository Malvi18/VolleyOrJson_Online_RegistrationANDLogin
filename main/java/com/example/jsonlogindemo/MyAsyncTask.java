package com.example.jsonlogindemo;

import android.os.AsyncTask;


import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Dell on 16-04-2018.
 */

public class MyAsyncTask extends AsyncTask<String, Void, String> {

    MyAsyncCallBack myAsyncCallBack;
    HashMap<String, String> hashMap;
    int flag;

//    public MyAsyncTask(RegistrationFragment myAsyncCallBack, int flag) {
//        this.myAsyncCallBack = myAsyncCallBack;
//        this.flag = flag;
//    }

    public MyAsyncTask(MyAsyncCallBack myAsyncCallBack,HashMap<String, String> hashMap, int flag) {
        this.myAsyncCallBack = myAsyncCallBack;
        this.hashMap = hashMap;
        this.flag = flag;
    }

//    public MyAsyncTask(HashMap<String, String> hashMap) {
//        this.hashMap = hashMap;
//    }


//    public void setHashMap(HashMap<String, String> hashMap) {
//        this.hashMap = hashMap;
//    }

    @Override
    protected String doInBackground(String... strings) {

        OkHttpClient client = new OkHttpClient();
        Request.Builder request = new Request.Builder().url(strings[0]);
        if (hashMap != null) {
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            Set<String> keySet = hashMap.keySet();
            for (String key : keySet) {
                bodyBuilder.add(key, hashMap.get(key));
            }

            FormBody formBody = bodyBuilder.build();
            request.post(formBody);
        }
        Request request1 = request.build();
        try {
            Response response = client.newCall(request1).execute();
            String result = response.body().string();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        myAsyncCallBack.CallBack(s,flag);
    }
}
