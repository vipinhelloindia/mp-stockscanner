package com.mp.stockscanner.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StockScanner implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("criteria")
    @Expose
    private List<Criterium> criteria = null;
    public final static Parcelable.Creator<StockScanner> CREATOR = new Creator<StockScanner>() {


        @SuppressWarnings({
                "unchecked"
        })
        public StockScanner createFromParcel(Parcel in) {
            return new StockScanner(in);
        }

        public StockScanner[] newArray(int size) {
            return (new StockScanner[size]);
        }

    };

    protected StockScanner(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.tag = ((String) in.readValue((String.class.getClassLoader())));
        this.color = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.criteria, (Criterium.class.getClassLoader()));
    }

    public StockScanner() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Criterium> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<Criterium> criteria) {
        this.criteria = criteria;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("id " + id).append("name " + name).append("tag " + tag).append("color " + color).append("criteria " + criteria).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(tag);
        dest.writeValue(color);
        dest.writeList(criteria);
    }

    public int describeContents() {
        return 0;
    }

}
