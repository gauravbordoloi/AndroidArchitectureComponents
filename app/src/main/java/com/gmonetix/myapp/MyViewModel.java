package com.gmonetix.myapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmonetix.myapp.model.Page;
import com.gmonetix.myapp.room.DatabaseCreator;
import com.gmonetix.myapp.room.UserDao;
import com.gmonetix.myapp.util.Util;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gaurav Bordoloi on 1/19/2018.
 */

public class MyViewModel extends AndroidViewModel {

    private MutableLiveData<List<Page>> pages;
    private Context context;
    private final UserDao userDao;

    public MyViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
        userDao = DatabaseCreator.getAppDatabase(application).userDao();
    }

    public LiveData<List<Page>> getPages() {
        if (pages == null) {
            pages = new MutableLiveData<List<Page>>();
            loadPages();
        }
        return pages;
    }

    private void loadPages() {

        if (Util.isConnectedToInternet(context)) {
            StringRequest request = new StringRequest(Request.Method.GET, Util.JSON_DATA, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    List<Page> list = new ArrayList<>();
                    try {
                        JSONArray array = new JSONArray(response);
                        for (int i=0; i<array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            Page page = new Page(object.getString("id"),object.getString("title"));
                            list.add(page);
                            userDao.insert(page);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } finally {
                        pages.setValue(list);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    pages = null;
                }
            });
            RequestQueue Q = Volley.newRequestQueue(context);
            Q.add(request);
        } else {
            pages.setValue(userDao.getAll());
        }

    }

}
