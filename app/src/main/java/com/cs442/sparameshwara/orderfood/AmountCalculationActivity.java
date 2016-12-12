package com.cs442.sparameshwara.orderfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;

import java.util.ArrayList;

/**
 * Created by sowmyaparameshwara on 10/2/16.
 */
public class AmountCalculationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        double total = reCalculateTotal(intent.getParcelableArrayListExtra(FoodMenuActivity.MENU_ITEM_LIST));
        intent.putExtra(FoodMenuActivity.SUM,total);
        setResult(RESULT_OK,intent);
        finish();
    }

    private double reCalculateTotal(ArrayList<Parcelable> menuItemList) {
        double total=0.0;
        for(int i = 0 ; i< menuItemList.size();i++){
            FoodMenuItem foodMenuItem = (FoodMenuItem) menuItemList.get(i);
            System.out.println(foodMenuItem.price.indexOf("U"));
            System.out.println();
            double price = Double.parseDouble(foodMenuItem.price.substring(0,foodMenuItem.price.indexOf("U")-1));
            total += foodMenuItem.getQuantity()*price;
        }
        return total;
    }
}
