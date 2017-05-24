package com.example.admin.study2;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by admin on 5/22/2017.
 */

public class ProductListAdapter extends ArrayAdapter<Product> {

    private List<Product> products;// constructor for list adapter
    public ProductListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull
            List<Product> objects) {
        super(context, resource, objects);

        //persistant connection to list
        this.products = objects;
    }
    //getv method

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       if (convertView == null){
           convertView = LayoutInflater.from(getContext()).
                   inflate(R.layout.content_list,parent,false);
       }
        Product product = products.get(position);
        TextView tv = (TextView) convertView.findViewById(R.id.nameText);
        tv.setText(product.getName());

        //format double
        NumberFormat formater = NumberFormat.getCurrencyInstance();
        String price = formater.format(product.getPrice());
       //get textview for price
        TextView priceText = (TextView) convertView.findViewById(R.id.priceText);
        priceText.setText(price);

        //return images remeber to always use convertview in datadapater class
        ImageView imageVw =  (ImageView) convertView.findViewById(R.id.clothImage);
        //make a refrence to a bitmap and load the method with productid var
        Bitmap bitmap = getBitmapFromAsset(product.getProductId());
        //display with setimage bitmap method
        imageVw.setImageBitmap(bitmap);

        return convertView;


    }
    private Bitmap getBitmapFromAsset(String productid){
        //call asset manager to acess assets folder
        AssetManager assetManager = getContext().getAssets();
        //make an input stream
        InputStream stream = null;

        // now  get the stream and put it inside an asset mangager thats formated
        try {
            stream = assetManager.open(productid + ".png");
            //decodes stream to a pic
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
