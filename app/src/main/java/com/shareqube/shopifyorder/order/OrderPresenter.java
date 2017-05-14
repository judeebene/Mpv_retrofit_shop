package com.shareqube.shopifyorder.order;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shareqube.shopifyorder.retrofit.OrderService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by judeebene on 5/8/17.
 */
public class OrderPresenter implements OrderContract.Presenter  {

    String LOG_TAG = OrderPresenter.class.getSimpleName();

    OrderContract.View mView ;

    // retrofit order servive
    OrderService orderService;
    Context mContext;


     public OrderPresenter(OrderContract.View view , Context context){

         mView = view;

         orderService = new OrderService();

         mContext = context;


     }

    @Override
    public void calculateTotalOrder() {

        final String aerodynamic_product = "Aerodynamic Cotton Keyboard";
        final List<String> aerodynamic_product_sold_list = new ArrayList<String>();


        if(isNetworkAvailable(mContext)) {
            orderService.getAPI().getResults().enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {


                    JsonObject result = response.body();

                    if (result != null) {
                        JsonArray ordersArrayJSON = result.getAsJsonArray("orders");

                        double subtotal_price = 0;
                        for (int i = 0; i < ordersArrayJSON.size(); i++) {


                            JsonElement customerOrderJson = ordersArrayJSON.get(i);


                            JsonObject customerOrderJsonAsJsonObject = customerOrderJson.getAsJsonObject();
                            subtotal_price += customerOrderJsonAsJsonObject.get("subtotal_price").getAsDouble();


                            JsonArray productArrays = customerOrderJsonAsJsonObject.get("line_items").getAsJsonArray();

                            JsonObject product_item = productArrays.get(0).getAsJsonObject();


                            String item_sold = product_item.get("title").getAsString();

                            if (item_sold.equals(aerodynamic_product)) {
                                aerodynamic_product_sold_list.add(item_sold);
                            }

                            //product_item.get("title")


                        }

                        Log.e(LOG_TAG, "c" + aerodynamic_product_sold_list.size());
                        if (mView != null) {

                            mView.displayTotalOrder(subtotal_price);
                            mView.displayAllSoldKeyBoard(aerodynamic_product_sold_list.size());

                        }

                    }


                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {

                }
            });
        }
        else {
            if (mView != null){

                mView.showError("No Network, Check your Network");

            }
        }

    }






    @Override
    public void start() {

    }

    public static Boolean isNetworkAvailable(Context c) {

        ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);


        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();


        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();

    }
}
