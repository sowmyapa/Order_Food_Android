package com.cs442.sparameshwara.orderfood;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static com.cs442.sparameshwara.orderfood.FoodMenuActivity.MENU_ITEM_IS_SELECTED;
import static com.cs442.sparameshwara.orderfood.FoodMenuActivity.MENU_ITEM_LIST;
import static com.cs442.sparameshwara.orderfood.FoodMenuActivity.SELECTED_MENU_ITEM_DESCRIPTION;
import static com.cs442.sparameshwara.orderfood.FoodMenuActivity.SELECTED_MENU_ITEM_NAME;
import static com.cs442.sparameshwara.orderfood.FoodMenuActivity.SELECTED_MENU_ITEM_POS;
import static com.cs442.sparameshwara.orderfood.FoodMenuActivity.SELECTED_MENU_ITEM_PRICE;
import static com.cs442.sparameshwara.orderfood.FoodMenuActivity.SELECTED_MENU_ITEM_QUANTITY;

/**
 * Created by sowmyaparameshwara on 10/2/16.
 */
public class FoodDetailsPageFragment extends Fragment {
    View view;
    EditText editText;
    Button backButton;
    TextView foodName;
    TextView foodDescription;
    TextView foodPrice;
    ArrayList<FoodMenuItem> menuItemList;
    boolean isSelected;
    int pos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.details_fragment, container, false);
        editText = (EditText) view.findViewById(R.id.detailsPageFoodOrderQuantity);
        backButton = (Button) view.findViewById(R.id.detailsPageBackButton);
        FoodMenuListFragment foodMenuListFrag = (FoodMenuListFragment) getFragmentManager().findFragmentById(R.id.FoodMenuListFragment);
        if(foodMenuListFrag!=null) {
            backButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    returnToFoodDetailsActivity();
                }
            });
        }

    /*    backButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                returnToFoodDetailsActivity();
            }
        });*/
        return view;
    }

    public void updateContent(FoodMenuItem menuItem) {
        Log.d("FoodDetailsPageFragment", "view  : "+view);
        foodName = (TextView) view.findViewById(R.id.detailsPageFoodName);
        foodDescription = (TextView)view.findViewById(R.id.detailsPageFoodDescription);
        foodPrice = (TextView) view.findViewById(R.id.detailsPageFoodOrderPrice);
        backButton = (Button) view.findViewById(R.id.detailsPageBackButton);

        editText = (EditText) view.findViewById(R.id.detailsPageFoodOrderQuantity);
        pos = menuItem.position-1;
        foodName.setText(menuItem.foodName);
        foodPrice.setText(menuItem.price);
        foodDescription.setText(menuItem.description);
        editText.setText(String.valueOf(menuItem.getQuantity()));


    }

    private void returnToFoodDetailsActivity() {
        FoodMenuListFragment foodMenuListFrag = (FoodMenuListFragment) getFragmentManager().findFragmentById(R.id.FoodMenuListFragment);
         if(foodMenuListFrag!=null){
             foodMenuListFrag.updateQuantity(Integer.parseInt(editText.getText().toString()),pos);
         }

    }



 /*   private void returnToFoodDetailsActivity() {
        Intent intent = new Intent(this,FoodMenuActivity.class);
        intent.putParcelableArrayListExtra(MENU_ITEM_LIST,menuItemList);
        intent.putExtra(SELECTED_MENU_ITEM_POS,pos);
        int qty = Integer.parseInt(editText.getText().toString());
        intent.putExtra(SELECTED_MENU_ITEM_QUANTITY,qty);
        if(qty>0){
            intent.putExtra(MENU_ITEM_IS_SELECTED,true);
        }else{
            intent.putExtra(MENU_ITEM_IS_SELECTED,false);
        }
        startActivity(intent);
    }*/

}
