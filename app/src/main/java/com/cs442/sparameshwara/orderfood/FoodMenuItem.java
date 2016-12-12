package com.cs442.sparameshwara.orderfood;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sowmyaparameshwara on 9/25/16.
 */
public class FoodMenuItem implements Parcelable{

    public int position;
    public int resId;
    public String foodName;
    public String price;
    public String description;
    public boolean selected;
    private int quantity;

    public FoodMenuItem(String foodName, String price, int resId, int position, String description){
        this.foodName = foodName;
        this.price = price;
        this.resId = resId;
        this.position = position;
        this.description = description;
    }

    protected FoodMenuItem(Parcel in) {
        position = in.readInt();
        resId = in.readInt();
        foodName = in.readString();
        price = in.readString();
        description = in.readString();
        selected = in.readInt() != 0;
        quantity = in.readInt();
    }

    public static final Creator<FoodMenuItem> CREATOR = new Creator<FoodMenuItem>() {
        @Override
        public FoodMenuItem createFromParcel(Parcel in) {
            return new FoodMenuItem(in);
        }

        @Override
        public FoodMenuItem[] newArray(int size) {
            return new FoodMenuItem[size];
        }
    };

    public void setSelected(boolean selected){
        this.selected = selected;
    }

    public boolean isSelected(){
        return selected;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getQuantity(){
        return quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(position);
        dest.writeInt(resId);
        dest.writeString(foodName);
        dest.writeString(price);
        dest.writeString(description);
        dest.writeInt((selected?1:0));
        dest.writeInt(quantity);

    }
}
