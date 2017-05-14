package com.shareqube.shopifyorder.order;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shareqube.shopifyorder.R;


/**
 * Created by judeebene on 5/9/17.
 */
public class OrderActivity extends AppCompatActivity  implements  OrderContract.View {


    OrderPresenter orderPresenter;

    TextView totalRevenue ,numberSoldView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();


        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);

        }

        totalRevenue = (TextView) findViewById(R.id.totalRevenueView);
        numberSoldView = (TextView) findViewById(R.id.numberSold);



         orderPresenter = new OrderPresenter(this, getApplicationContext());

        orderPresenter.calculateTotalOrder();



    }

    @Override
    public void displayTotalOrder(Double total) {


        if(total != null ) {


                totalRevenue.setText(" " + total);

        }

    }


    @Override
    public void displayAllSoldKeyBoard(int numberSold) {


            numberSoldView.setText("" +numberSold);

    }


    @Override
    public void showError(String errorMessage) {

        Toast.makeText(this , errorMessage , Toast.LENGTH_LONG).show();

    }

    @Override
    public void setPresenter(OrderContract.Presenter presenter) {

        //would have you if it was fragment
    }


}
