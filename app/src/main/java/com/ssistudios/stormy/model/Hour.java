package com.ssistudios.stormy.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fc on 7/31/2015.
 */
public class Hour implements Parcelable{

    public Hour(){}


    private  Hour(Parcel in){}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }


    public static Creator<Hour> CREATOR = new Creator<Hour>() {
        @Override
        public Hour createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public Hour[] newArray(int size) {
            return new Hour[0];
        }
    };

}
