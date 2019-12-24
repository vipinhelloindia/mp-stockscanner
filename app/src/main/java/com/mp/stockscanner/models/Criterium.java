package com.mp.stockscanner.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class Criterium implements Parcelable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("variable")
    @Expose
    HashMap<String, Variable> variable;


    protected Criterium(Parcel in) {
        type = in.readString();
        text = in.readString();
        variable = (HashMap) in.readValue(HashMap.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(text);
        dest.writeValue(variable);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Criterium> CREATOR = new Parcelable.Creator<Criterium>() {
        @Override
        public Criterium createFromParcel(Parcel in) {
            return new Criterium(in);
        }

        @Override
        public Criterium[] newArray(int size) {
            return new Criterium[size];
        }
    };
}