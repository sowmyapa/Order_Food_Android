package com.cs442.sparameshwara.orderfood;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import static com.cs442.sparameshwara.orderfood.FoodMenuActivity.*;

/**
 * Created by sowmyaparameshwara on 10/2/16.
 */
public class FoodDetailsPageActivity extends AppCompatActivity{

    TextView foodName;
    TextView foodDescription;
    TextView foodPrice;
    EditText editText;
    Button backButton;
    ArrayList<FoodMenuItem> menuItemList;
    int pos;
    boolean isSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.details_page_activity);

        Intent intent = getIntent();
        foodName = (TextView) findViewById(R.id.detailsPageFoodName);
        foodDescription = (TextView)findViewById(R.id.detailsPageFoodDescription);
        foodPrice = (TextView) findViewById(R.id.detailsPageFoodOrderPrice);
        backButton = (Button) findViewById(R.id.detailsPageBackButton);
        editText = (EditText) findViewById(R.id.detailsPageFoodOrderQuantity);

        pos = intent.getIntExtra(SELECTED_MENU_ITEM_POS,0);
        menuItemList = intent.getParcelableArrayListExtra(MENU_ITEM_LIST);
        foodName.setText(intent.getStringExtra(SELECTED_MENU_ITEM_NAME));
        foodPrice.setText(intent.getStringExtra(SELECTED_MENU_ITEM_PRICE));
        foodDescription.setText(intent.getStringExtra(SELECTED_MENU_ITEM_DESCRIPTION));
        editText.setText(String.valueOf(intent.getIntExtra(SELECTED_MENU_ITEM_QUANTITY,0)));
        editText.setSelection(editText.getText().length());
        backButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                returnToFoodDetailsActivity();
            }
        });

    }

    private void returnToFoodDetailsActivity() {
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
    }



}
