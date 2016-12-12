package com.cs442.sparameshwara.orderfood;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FoodMenuActivity extends AppCompatActivity implements FoodMenuListFragment.OnClickMenuListListener, CompoundButton.OnCheckedChangeListener{

    int index = 1;
 ArrayList<FoodMenuItem> menuItemList;
     FoodMenuItemAdapter menuItemAdapter;
    ListView foodListView;
    TextView totalAmount;
    public static final String SELECTED_MENU_ITEM_NAME = "1";
    public static final String SELECTED_MENU_ITEM_PRICE = "2";
    public static final String SELECTED_MENU_ITEM_DESCRIPTION = "3";
    public static final String SELECTED_MENU_ITEM_QUANTITY = "4";
    public static final String SELECTED_MENU_ITEM_POS = "5";
    public static final String MENU_ITEM_IS_SELECTED = "6";
    public static final String MENU_ITEM_LIST = "7";
    public static final String SUM = "8";

    public static final int CALCULATE = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_menu_activity);
        Intent intent = getIntent();
        if(intent.getParcelableArrayListExtra(MENU_ITEM_LIST)!=null){
            menuItemList = intent.getParcelableArrayListExtra(MENU_ITEM_LIST);
            FoodMenuItem updatedFoodMenuItem = menuItemList.get(intent.getIntExtra(SELECTED_MENU_ITEM_POS,0));
            updatedFoodMenuItem.setQuantity(intent.getIntExtra(SELECTED_MENU_ITEM_QUANTITY,0));
            updatedFoodMenuItem.setSelected(intent.getBooleanExtra(MENU_ITEM_IS_SELECTED,true));

        }else{
            menuItemList = new ArrayList<FoodMenuItem>();
            prepopulateMenu(menuItemList);
        }

            FragmentManager fragmentManager = getFragmentManager();
            FoodMenuListFragment menuListFragment = (FoodMenuListFragment) fragmentManager.findFragmentById(R.id.FoodMenuListFragment);
            menuItemAdapter = new FoodMenuItemAdapter(this, R.layout.food_menu_item_fragment, menuItemList);
            menuListFragment.setListAdapter(menuItemAdapter);
            foodListView = menuListFragment.getListView();

           Button orderConfirmationButton = (Button) findViewById(R.id.orderConfimationButton);
        totalAmount = (TextView) findViewById(R.id.orderAmountView);
        orderConfirmationButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String toastMessage= "Order confirmed. \nTotal order amount : "+totalAmount.getText().toString().substring(9);
                Toast.makeText(FoodMenuActivity.this,toastMessage, Toast.LENGTH_LONG).show();

            }
        });


    }


    private void prepopulateMenu(ArrayList<FoodMenuItem> menuItemList) {
        String[] name = new String[]{"VEG CHEESE PIZZA","VEG BURGER COMBO","SUBWAY CLUB","SPICY SZECHUAN NOODLES","PANEER TIKKA ROLL","PANCAKE","CEREALS"};
        String[] price = new String[]{"10.00 USD", "12.05 USD","09.00 USD","11.25 USD", "08.25 USD","06.00 USD","05.00 USD"};
        int[] images = new int[]{R.drawable.pizza,R.drawable.burger,R.drawable.subs,R.drawable.noodles,R.drawable.rolls,R.drawable.pancake,R.drawable.cereals};
        String[] description = new String[]{"Veg Cheese Pizza : 10.00 USD \n Taste : Medium Spicy \n Ingredients : Cheese, Tomato & Spices served on thin crust.",
                "Veg Burger Combo : 12.05 USD \nTaste : Medium Spicy \nIngredients : Vegetable patty & an additional cheese patty served with french fries.",
                "Subway Club : 09.00 USD \nTaste : Medium Spicy \nIngredients : Pick your choice of vegetables among lettuce, jalapenoes, potato, beans, carrot, olives.",
                "Spicy Szechuan Noodles : 11.25 USD \nTaste : Spicy \nIngredients : Noodles served with hot garlic sauce and vegetables.",
                "Paneer Tikka Roll : 08.25 USD \nTaste : Spicy \nIngredients : Grilled paneer tikka wrapped in maida roll.",
                "Pancakes : 06.00 USD \nTaste : Sweet \nIngredients : Freshly made pancakes served with Maple Syrup.",
                "Cereals : 05.00 USD \nTaste : Sweet \nIngredients : Corn Flakes/Chocos/Maple Loops sereved with hot/cold milk."};

        for(int i = 0 ; i <7 ; i++){
            FoodMenuItem foodMenuItem = new FoodMenuItem(name[i],price[i],images[i],index++,description[i]);
            menuItemList.add(foodMenuItem);
        }
    }

    @Override
    public void onClick(int pos) {
        FoodDetailsPageFragment foodDetailsFrag = (FoodDetailsPageFragment) getFragmentManager().findFragmentById(R.id.FoodDetailsPageFragment);
        FoodMenuItem menuItem = menuItemList.get(pos);
        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT || foodDetailsFrag == null) {
            Intent intent = new Intent(this, FoodDetailsPageActivity.class);

            intent.putExtra(SELECTED_MENU_ITEM_NAME, menuItem.foodName);
            intent.putExtra(SELECTED_MENU_ITEM_DESCRIPTION, menuItem.description);
            intent.putExtra(SELECTED_MENU_ITEM_PRICE, menuItem.price);
            intent.putExtra(SELECTED_MENU_ITEM_QUANTITY, menuItem.getQuantity());
            intent.putExtra(SELECTED_MENU_ITEM_POS, pos);
            intent.putExtra(MENU_ITEM_IS_SELECTED,menuItem.isSelected());

            intent.putParcelableArrayListExtra(MENU_ITEM_LIST,menuItemList);
            startActivity(intent);
        } else {
            // DisplayFragment (Fragment B) is in the layout (tablet layout),
            // so tell the fragment to update
            foodDetailsFrag.updateContent(menuItem);
        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
            // Make sure the request was successful
            if (requestCode==CALCULATE && resultCode == RESULT_OK) {
                double total = data.getDoubleExtra(SUM,0.0);
                totalAmount.setText(" TOTAL : "+total+" $");
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

                // Do something with the contact here (bigger example below)
            }

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
     int pos = foodListView.getPositionForView(buttonView);
        if(pos != -1 && pos<=menuItemList.size()){
            FoodMenuItem foodMenuItem = menuItemList.get(pos);
            if(isChecked==foodMenuItem.isSelected()){
                return;
            }
            foodMenuItem.setSelected(isChecked);

            if(isChecked && foodMenuItem.getQuantity()==0){
                foodMenuItem.setQuantity(1);
            }else{
                foodMenuItem.setQuantity(0);
            }
            createAmountCalculationActivity();
            menuItemAdapter.notifyDataSetChanged();

        }
    }

    private void createAmountCalculationActivity() {
        Intent intent = new Intent(this,AmountCalculationActivity.class);
        intent.putParcelableArrayListExtra(MENU_ITEM_LIST,menuItemList);
        startActivityForResult(intent,CALCULATE);

    }

    private void reCalculateTotal() {
        double total=0.0;
        for(int i = 0 ; i< menuItemList.size();i++){
            FoodMenuItem foodMenuItem = menuItemList.get(i);
            System.out.println(foodMenuItem.price.indexOf("U"));
            System.out.println();
            double price = Double.parseDouble(foodMenuItem.price.substring(0,foodMenuItem.price.indexOf("U")-1));
            total += foodMenuItem.getQuantity()*price;
        }
        totalAmount.setText(" TOTAL : "+total+" $");
    }

//    public

}
