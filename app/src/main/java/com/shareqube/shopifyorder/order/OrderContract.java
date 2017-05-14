package com.shareqube.shopifyorder.order;

import android.content.Context;
import android.view.Menu;

import com.shareqube.shopifyorder.BasePresenter;
import com.shareqube.shopifyorder.BaseView;



/**
 * Created by judeebene on 5/8/17.
 * // this is a contract between the  views and the presenters
 */
public class OrderContract {

    interface View extends BaseView<Presenter> {

        void displayTotalOrder(Double total);
        void displayAllSoldKeyBoard(int numberSold);
        void showError(String errorMessage);


    }

    interface Presenter extends BasePresenter {


        void calculateTotalOrder();




    }


}
