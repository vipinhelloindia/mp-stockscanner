package com.mp.stockscanner.scanner;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mp.stockscanner.R;
import com.mp.stockscanner.models.Criterium;
import com.mp.stockscanner.models.Variable;
import com.mp.stockscanner.scanner.listener.SpanClickListener;

import java.util.ArrayList;
import java.util.HashMap;

final public class Utils {
    private Utils() {

    }

    private static SpannableStringBuilder getBuilder(String text, HashMap<String, Variable> variableHashMap,
                                                     SpanClickListener spanClickListener) {
        SpanableData spanableData = getSpanableData(text, variableHashMap);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(spanableData.modifiedText);
        for (SpanableData.SpannableIndex spannableIndex : spanableData.spannableIndices) {
            ClickableSpan clickableSpan = new ClickableSpanListener(spannableIndex.key, variableHashMap, spanClickListener);
            spannableStringBuilder.setSpan(clickableSpan,
                    spannableIndex.start, spannableIndex.end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        }

        return spannableStringBuilder;
    }

    public static void setText(TextView textView, String value) {
        if (value != null && value.length() > 0) {
            textView.setText(value);
            return;
        }
        textView.setText("");

    }

    public static void setTextColor(TextView textView, String color) {
        try {
            int textColor = textView.getContext().getResources().getColor(ColorType.valueOf(color).textColor);
            textView.setTextColor(textColor);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void setSpanable(TextView nameView, Criterium stockScanner, SpanClickListener spanClickListener) {
        try {
            SpannableStringBuilder spannableStringBuilder = getBuilder(stockScanner.getText(), stockScanner.getVariable(), spanClickListener);
            nameView.setText(spannableStringBuilder);
        } catch (Exception exce) {
            exce.printStackTrace();
        }
    }

    public enum ColorType {

        green("Green Color", R.color.greenTextColor),
        red("Red Color", R.color.redTextColor),
        white("White Default", R.color.whiteTextColor);

        private String color;
        private Integer textColor;

        ColorType(String color, Integer textColor) {
            this.color = color;
            this.textColor = textColor;
        }
    }

    public static final class ClickableSpanListener extends ClickableSpan {
        HashMap<String, Variable> variableHashMap;
        String key;
        SpanClickListener spanClickListener;

        public ClickableSpanListener(String key, HashMap<String, Variable> variableHashMap, SpanClickListener spanClickListener) {
            this.key = key;
            this.variableHashMap = variableHashMap;
            this.spanClickListener = spanClickListener;
        }

        @Override
        public void onClick(@NonNull View widget) {
            if (spanClickListener != null) {
                spanClickListener.onSpanClick(variableHashMap.get(key));
            }

        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
            ds.setColor(Color.parseColor("#2A5696"));
        }
    }


    private static SpanableData getSpanableData(String text, HashMap<String, Variable> variableHashMap) {
        SpanableData spanableData = new SpanableData();
        spanableData.spannableIndices = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        int startIndex = 0;
        int endIndex = 0;
        if (!text.contains("$")) {
            spanableData.modifiedText = text;
            return spanableData;
        }
        outer:
        while (true) {
            int tempStartIndex = startIndex;
            startIndex = text.indexOf("$", startIndex);
            if (startIndex == -1) {
                stringBuilder.append(text.substring(tempStartIndex, text.length()));
                break;
            }
            String subString = text.substring(endIndex, startIndex);
            stringBuilder.append(subString);

            tempStartIndex = startIndex + 1;
            while (tempStartIndex < text.length()) {
                //find if Digit and contains in our map as key
                if (!Character.isDigit(text.charAt(tempStartIndex))) {
                    String key = text.substring(startIndex, tempStartIndex);
                    //condition for valid key and min length
                    if (canAddSpanableIndex(tempStartIndex, startIndex, key, variableHashMap)) {
                        addSpannableIndex(stringBuilder, spanableData, key, variableHashMap);
                    } else {
                        stringBuilder.append(key);
                    }
                    startIndex = tempStartIndex;
                    endIndex = startIndex;
                    continue outer;
                }
                tempStartIndex++;
            }
            String key = text.substring(startIndex, tempStartIndex);
            if (canAddSpanableIndex(tempStartIndex, startIndex, key, variableHashMap)) {
                addSpannableIndex(stringBuilder, spanableData, key, variableHashMap);
            }
            startIndex = tempStartIndex;
            endIndex = startIndex;
        }

        spanableData.modifiedText = stringBuilder.toString();
        return spanableData;
    }

    private static boolean canAddSpanableIndex(int tempStartIndex, int startIndex,
                                               String key, HashMap<String, Variable> variableHashMap) {
        return tempStartIndex - startIndex > 0 && variableHashMap.containsKey(key);
    }

    private static void addSpannableIndex(StringBuilder stringBuilder, SpanableData spanableData,
                                          String key, HashMap<String, Variable> variableHashMap) {
        Variable variable = variableHashMap.get(key);

        SpanableData.SpannableIndex spannableIndex = new SpanableData.SpannableIndex();
        spannableIndex.start = stringBuilder.length();
        spannableIndex.key = key;

        stringBuilder.append("(");
        if (variable != null) {
            if (variable.getValues() != null && variable.getValues().size() > 0) {
                stringBuilder.append(variable.getValues().get(0));
            } else {
                stringBuilder.append(variable.getDefaultValue());
            }
        }
        stringBuilder.append(")");

        spannableIndex.end = stringBuilder.length();
        spanableData.spannableIndices.add(spannableIndex);
    }

    static class SpanableData {
        String modifiedText;
        ArrayList<SpannableIndex> spannableIndices;

        static class SpannableIndex {
            int start;
            int end;
            String key;
        }
    }
}

