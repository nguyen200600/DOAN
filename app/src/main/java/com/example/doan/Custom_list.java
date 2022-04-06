package com.example.doan;

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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Custom_list extends ArrayAdapter<Entry> {
    ImageView img;
    public Custom_list(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public Custom_list(Context context, int resource, List<Entry> items) {
        super(context, resource, items);


    }



    @Override
    public View getView(int postion , View view , ViewGroup parent){
        View v = view ;
        if (v==null){
            LayoutInflater vi ;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.custom_new,null);

        }
        Entry item = getItem(postion);

        if (item != null){
            // ánh xạ + giá trị
            img = (ImageView) v.findViewById(R.id.imge);
            loadAnh asyscTask = new loadAnh();
            asyscTask.execute(item.getImage());


            TextView title = (TextView) v.findViewById(R.id.title);
            title.setText(item.getTitle());




        }
        return v ;
    }


    private class loadAnh extends AsyncTask<String, Integer, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bmp = null;
            URL u;
            try {
                u = new URL(params[0]);
                bmp = BitmapFactory.decodeStream(u.openConnection().getInputStream());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            img.setImageBitmap(bitmap);
        }


    }
}

