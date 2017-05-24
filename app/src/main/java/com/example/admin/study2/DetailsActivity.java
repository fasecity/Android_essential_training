package com.example.admin.study2;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getting info from listactivity gets putEXTRA
        String productid = getIntent().getStringExtra(ListActivity.PRODUCT_ID);
       //makes instance of product and gets product hashmap list in dataprovider
        final Product product = DataProvider.productMap.get(productid);
        //bind to textveiw
        TextView tv = (TextView) findViewById(R.id.nameText);
        tv.setText(product.getName());

        /// //////////
        //format double
        NumberFormat formater = NumberFormat.getCurrencyInstance();
        String price = formater.format(product.getPrice());
        //get textview for price
        TextView priceText = (TextView) findViewById(R.id.priceText);
        priceText.setText(price);

        //return images remeber to always use convertview in datadapater class
        ImageView imageVw =  (ImageView) findViewById(R.id.imageView);
        //make a refrence to a bitmap and load the method with productid var
        Bitmap bitmap = getBitmapFromAsset(product.getProductId());
        //display with setimage bitmap method
        imageVw.setImageBitmap(bitmap);

        TextView descView = (TextView) findViewById(R.id.descriptionText);
        descView.setText(product.getDescription());



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();
                Intent data = new Intent();
                //data i want to send back to listview
                data.putExtra(ListActivity.RETURN_MSG, product.getName() + " added to cart");
                setResult(RESULT_OK,data);
                finish();

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private Bitmap getBitmapFromAsset(String productid){
        //call asset manager to acess assets folder
        AssetManager assetManager = getAssets();
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
