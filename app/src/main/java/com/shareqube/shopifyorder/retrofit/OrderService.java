package com.shareqube.shopifyorder.retrofit;



import  com.google.gson.JsonObject;



import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by judeebene on 5/10/17.
 */
public class OrderService {

   // https://shopicruit.myshopify.com/admin/orders.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6
    private static String BASE_URL =  "https://shopicruit.myshopify.com/admin/";

    //&fields=created_at,id,total-price,title
    public interface  SportifyOrderAPI{
        @GET("orders.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
        Call<JsonObject> getResults();
    }

    public SportifyOrderAPI getAPI()  {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(SportifyOrderAPI.class);








    }
}
