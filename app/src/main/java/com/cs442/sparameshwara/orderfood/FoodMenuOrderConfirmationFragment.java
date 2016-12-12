package com.cs442.sparameshwara.orderfood;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sowmyaparameshwara on 10/2/16.
 */
public class FoodMenuOrderConfirmationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.food_menu_order_confirmation_fragment, container, false);
    }
}
