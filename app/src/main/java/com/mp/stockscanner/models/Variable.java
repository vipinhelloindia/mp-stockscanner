package com.mp.stockscanner.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Variable implements Parcelable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("study_type")
    @Expose
    private String studyType;
    @SerializedName("parameter_name")
    @Expose
    private String parameterName;
    @SerializedName("min_value")
    @Expose
    private Integer minValue;
    @SerializedName("max_value")
    @Expose
    private Integer maxValue;
    @SerializedName("default_value")
    @Expose
    private Integer defaultValue;

    public List<Double> getValues() {
        return values;
    }

    @SerializedName("values")
    @Expose
    private List<Double> values = null;
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

    protected Variable(Parcel in) {
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.studyType = ((String) in.readValue((String.class.getClassLoader())));
        this.parameterName = ((String) in.readValue((String.class.getClassLoader())));
        this.minValue = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.maxValue = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.defaultValue = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.values, (java.lang.Integer.class.getClassLoader()));
    }

    public Variable() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStudyType() {
        return studyType;
    }

    public void setStudyType(String studyType) {
        this.studyType = studyType;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public Integer getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Integer defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("type " + type).append("studyType " + studyType).append("parameterName " + parameterName).append("minValue " + minValue).append("maxValue " + maxValue).append("defaultValue " + defaultValue).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeValue(studyType);
        dest.writeValue(parameterName);
        dest.writeValue(minValue);
        dest.writeValue(maxValue);
        dest.writeValue(defaultValue);
        dest.writeList(values);
    }

    public int describeContents() {
        return 0;
    }

}