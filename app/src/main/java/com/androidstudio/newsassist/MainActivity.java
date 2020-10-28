package com.androidstudio.newsassist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ListView list;
    NewsAdapter adapter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list);
        adapter = new NewsAdapter(MainActivity.this, ApplicationClass.news);

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Please Wait..");
        dialog.setTitle("Loading");
        dialog.show();

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v2/top-headlines?country=in&apiKey=5c881d51fc324ae893678710a5e9e169",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try
                {
                    JSONArray articles = response.getJSONArray("articles");
                    for(int i=0;i<articles.length();i++)
                    {
                        JSONObject item = articles.getJSONObject(i);
                        ApplicationClass.news.add(new News(item.getString("title"),item.getString("urlToImage"),item.getString("publishedAt").substring(0,10),item.getString("description")));
                    }
                    list.setAdapter(adapter);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        }
                    });
                    dialog.dismiss();
                }
                catch (JSONException e)
                {
                    e.getCause();
                    dialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.getMessage();
                dialog.dismiss();
            }
        });
        queue.add(request);
    }

}
