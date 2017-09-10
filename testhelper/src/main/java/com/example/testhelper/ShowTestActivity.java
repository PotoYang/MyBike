package com.example.testhelper;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.LruCache;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class ShowTestActivity extends AppCompatActivity {

    TextView tv1;
    TextView textv2;
    NetworkImageView image;

    //我的本地服务器的接口，如果在你自己的服务器上需要更改相应的url
    private String url = "http://youth.swjtu.edu.cn/develops/handsSwjtu/login.php?stuCode=2014112252&passWord=5842724609";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_test);
        tv1 = (TextView) findViewById(R.id.tv1);
        textv2 = (TextView) findViewById(R.id.tv2);
        image = (NetworkImageView) findViewById(R.id.netwok_image);

        /*StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                Log.e("volley", s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("volleyerror", "error2");
            }
        });*/

        /*JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.e("volley", jsonObject.toString());
                try {
                    String state = jsonObject.getString("State");
                    String message = jsonObject.getString("Message");
                    tv1.setText("State: " + state);
                    textv2.setText("Message: " + message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("volleyerror", "error2");
            }
        });

        VolleySingleton.getVolleySingleton(this.getApplicationContext()).addToRequestQueue(jr);*/

        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());

        image.setDefaultImageResId(R.mipmap.heart);
        image.setErrorImageResId(R.mipmap.icon);
        image.setImageUrl("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", imageLoader);



    }

    public class BitmapCache implements ImageLoader.ImageCache {

        private LruCache<String, Bitmap> mCache;

        public BitmapCache() {
            int maxSize = 10 * 1024 * 1024;
            mCache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String s) {
            return mCache.get(url);
        }

        @Override
        public void putBitmap(String s, Bitmap bitmap) {
            mCache.put(url, bitmap);
        }
    }
}
