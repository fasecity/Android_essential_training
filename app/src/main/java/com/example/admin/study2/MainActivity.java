package com.example.admin.study2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //feild for name
    private static final int MENU_ITEM_LOGOUT = 1001;
    public  static String urll = "http://elma86.azurewebsites.net/";
    public  static String email = "audiotrash112@gmail.com";

    private CoordinatorLayout cords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cords =(CoordinatorLayout) findViewById(R.id.cord);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et = (EditText) findViewById(R.id.nameText) ;
                String nameEntry = et.getText().toString();
                //snackbar is uses view instead of context/appplication context
               // Snackbar.make(view, "My name is: " + nameEntry, Snackbar.LENGTH_LONG)
                    //    .setAction("Action", null).show();

                String [] address = {email};
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                //set data
                emailIntent.setData(Uri.parse("mailto:"));
                //pass in key(EMAIL_EXTRA) and pass value tat is adress array
                emailIntent.putExtra(Intent.EXTRA_EMAIL,address);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hood tings");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "wats good brotha");
                if (emailIntent.resolveActivity(getPackageManager()) != null ){
                    startActivity(emailIntent);
                }
            }
        });
        StringBuilder builder = new StringBuilder();
        for (int i=0;i<10;i++){
            //gets string from lorem
            builder.append(getString(R.string.lorem) + "\n\n");
            TextView tv = (TextView) findViewById(R.id.DynatextView);
            tv.setText(builder.toString());

            ////-----Setting event with onClick lisiner directly in Java
            Button btn = (Button) findViewById(R.id.buttonClickHandler) ;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     EditText et = (EditText) findViewById(R.id.nameText);
                     String name = et.getText().toString();

                      Snackbar.make(cords, "Your name is " + name, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            });

            Log.d("Main Activity","onCreate");

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //To add menu item at run time need the number of the menu
        //an int final prop for like MENU_ITEM_LOGOUT that can = any number
        //the location ex 102
        // and the name its best to extract reasorce for name
        menu.add(0,MENU_ITEM_LOGOUT,105, R.string.Logout);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //making switch statment to handle muliple menu items
        switch (id){
            case R.id.action_settings:
                Snackbar.make(cords, "You pressed settings ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.action_about:
               // Snackbar.make(cords, "You pressed about ", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();

                //_________________IMPORTANT______________
                //This is how u make an intent to go to another activity
                Intent inten = new Intent(this,AboutActivity.class);
                startActivity(inten);
                return true;
            case R.id.action_web:
               //go to web implicit intent , intent obj knows i want to view something and uri
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urll));
                //know we need to resolve the intent
                if (webIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(webIntent);
                }

                return true;
            // when creating menu items dynamically use the feild you made at the top---
            case MENU_ITEM_LOGOUT:
                Snackbar.make(cords, "You logged out ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.action_cart:
                Snackbar.make(cords, "You pressed cart ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.action_lister:

                //_________________IMPORTANT______________
                //This is how u make an intent to go to another activity
                Intent intent = new Intent(this,ListActivity.class);
                startActivity(intent);
                return true;

        }

        //noinspection SimplifiableIfStatement---------depricated use switch
       // if (id == R.id.action_settings) {
           // Toast.makeText(this, "You pressed settings ", Toast.LENGTH_SHORT).show();

            //cool thing that makes snack bar swing its  getting set to cordinator layout
          //  Snackbar.make(cords, "You pressed settings ", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show();

          //  return true;
       // }

        return super.onOptionsItemSelected(item);
    }



    //button handler using the onClick in XML activity main
  //  public void buttonOnClickHandle(View view) {
    //  EditText et = (EditText) findViewById(R.id.nameText);
     //  String name = et.getText().toString();

     //   Snackbar.make(cords, "Your name is " + name, Snackbar.LENGTH_LONG)
       //         .setAction("Action", null).show();
   // }
}
