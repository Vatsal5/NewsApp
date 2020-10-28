package com.androidstudio.newsassist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {
    private Context context;
    private ArrayList<News> news;

    public NewsAdapter(Context context, ArrayList<News> list) {
        super(context, R.layout.layout_news, list);

        this.context = context;
        news = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.layout_news, parent, false);

        ImageView ivImage;
        TextView tvTitle, tvDate,tvDescription;

        ivImage = v.findViewById(R.id.ivImage);
        tvTitle = v.findViewById(R.id.tvTitle);
        tvDate = v.findViewById(R.id.tvDate);
        tvDescription = v.findViewById(R.id.tvDescription);

        tvTitle.setText(ApplicationClass.news.get(position).getTitle());
        tvDate.setText(ApplicationClass.news.get(position).getPublishedate());
        tvDescription.setText(ApplicationClass.news.get(position).getDescription());
        new ImageLoadTask(ApplicationClass.news.get(position).getImageurl(), ivImage).execute();

        return v;
    }

    public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private ImageView imageView;

        public ImageLoadTask(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
        }

    }

}

