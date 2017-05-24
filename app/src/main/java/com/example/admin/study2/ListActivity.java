package com.example.admin.study2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ListActivity extends AppCompatActivity {
   //public   CoordinatorLayout coordinatorLayout;
    //passing data
    private static final int DETAIL_REQUEST = 1111;
    public static final String RETURN_MSG = "RETURN_MSG";
    public CoordinatorLayout coords;

    //////
    public static final String PRODUCT_ID = "PRODUCT_ID";
    private List<Product> products = DataProvider.productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coords =(CoordinatorLayout) findViewById(R.id.cord);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //getting a string reasorce array and dispaying it-- remember this id 4 fixed lists
        //1. declare array of strings from clothes.xml
        //String [] clothItems = getResources().getStringArray(R.array.clothing);
        //2. Make Array adapter binds data tolist:context,layout(choose frm built in sdk,
        // textid from choosen list, name of array you intanciated)
       // ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,
          //      android.R.id.text1,clothItems);
        //hook up listview to adapter
       // ListView list = (ListView) findViewById(R.id.clist);
       // list.setAdapter(adapter);
        ////////////////////////////////////////////

        //////////////////////custom adapter///////////////////////////

        //1. declare array of strings from clothes.xml
        //String [] clothItems = getResources().getStringArray(R.array.clothing);

        //2. Make Array adapter binds data tolist:context,layout(choose frm built in sdk,
       // textid from choosen list, name of array you intanciated)
        ProductListAdapter adapter = new ProductListAdapter(this, R.layout.content_list,products);


        //hook up listview to adapter
        ListView list = (ListView) findViewById(R.id.listView);
        //--------!!!!!!!!!!!!Make SURE LAYOUT HEIGHT AND WIDTH IS WRAP_CONTENT
        //list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
       // list.setFocusable(true);
        //list.callOnClick();

        list.setAdapter(adapter);

        //4. to click on idividual items and pass to details view
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //make an intent
                Intent intent = new Intent(ListActivity.this,DetailsActivity.class);
               //this passes info into details activity
                Product product = products.get(position);
                intent.putExtra(PRODUCT_ID,product.getProductId());

               // startActivity(intent);-----regular style
                //new style add details request to startactivity
                startActivityForResult(intent,DETAIL_REQUEST);
            }
        });


        ////////////////////////////////////////////////////////////////
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    //get data back from details view


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DETAIL_REQUEST){
            if (resultCode == RESULT_OK ){
                String msg = data.getStringExtra(RETURN_MSG);

                //------------!----FUCK FOR GOD SAKE PUT AN ID ON THE
                // COODINATOR LAYOUT IN THE ACTIVITY.XML
                Snackbar.make(coords, msg, Snackbar.LENGTH_LONG)
                        .setAction("Go to cart", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(ListActivity.this, " You went to cart!", Toast.LENGTH_SHORT).show();
                            }
                        }).show();

            }
        }
    }
}
