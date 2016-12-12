package com.cs442.sparameshwara.orderfood;

import android.app.Activity;
import android.app.ListFragment;
import android.view.View;
import android.widget.ListView;

/**
 * Created by sowmyaparameshwara on 10/2/16.
 */
public class FoodMenuListFragment extends ListFragment{

    OnClickMenuListListener onClickMenuListListener;

    public interface OnClickMenuListListener{
        public void onClick(int pos);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            onClickMenuListListener = (OnClickMenuListListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
                    " must implement OnNewItemAddedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        super.onListItemClick(l, v, pos, id);
        onClickMenuListListener.onClick(pos);
    }


    public void updateQuantity(int qty,int pos) {
         FoodMenuItem foodMenuItem = (FoodMenuItem) getListAdapter().getItem(pos);
        foodMenuItem.setQuantity(qty);
        if(qty>0) {
            foodMenuItem.setSelected(true);
        }else{
            foodMenuItem.setSelected(false);
        }
        ((FoodMenuItemAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onStop(){
        super.onStop();
        this.setRetainInstance(true);
    }
}
