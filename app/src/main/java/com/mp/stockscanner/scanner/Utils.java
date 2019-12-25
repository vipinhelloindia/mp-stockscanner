package com.mp.stockscanner.scanner;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.View;

import androidx.annotation.NonNull;

import com.mp.stockscanner.models.Variable;

import java.util.ArrayList;
import java.util.HashMap;

final public class Utils {
    private Utils() {

    }


    public static SpannableStringBuilder getBuilder(String text, HashMap<String, Variable> variableHashMap,
                                                    SpanClickListener spanClickListener) {
        SpanableData spanableData = getSpanableData(text);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(spanableData.modifiedText);
        for (SpanableData.SpannableIndex spannableIndex : spanableData.spannableIndices) {
            ClickableSpan clickableSpan = new ClickableSpanListener(spannableIndex.key, variableHashMap, spanClickListener);
            spannableStringBuilder.setSpan(clickableSpan,
                    spannableIndex.start, spannableIndex.end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        }

        return spannableStringBuilder;
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
    }

    private static SpanableData getSpanableData(String text) {
        SpanableData spanableData = new SpanableData();
        spanableData.spannableIndices = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        int startIndex = 0;
        int endIndex = 0;
        while (true) {
            int tempStartIndex = startIndex;
            startIndex = text.indexOf("$", startIndex);
            if (startIndex == -1) {
                stringBuilder.append(text.substring(tempStartIndex + 1, text.length()));
                break;
            }
            String subString = text.substring(endIndex, startIndex);
            stringBuilder.append(subString);

            int tempIndex = startIndex;
            while (tempIndex < text.length()) {
                tempIndex++;
                if (!Character.isDigit(text.charAt(tempIndex))) {
                    if (tempIndex - startIndex > 1) {
                        stringBuilder.append("(");
                        String key = text.substring(startIndex, tempIndex);
                        stringBuilder.append(key);
                        stringBuilder.append(")");
                        SpanableData.SpannableIndex spannableIndex = new SpanableData.SpannableIndex();
                        spannableIndex.start = startIndex - 1;
                        spannableIndex.end = tempIndex + 1;
                        spannableIndex.key = key;
                        spanableData.spannableIndices.add(spannableIndex);

                    } else {
                        stringBuilder.append(text.substring(startIndex, tempIndex));
                    }
                    endIndex = tempIndex;
                    startIndex++;
                    break;
                }
            }
        }
        spanableData.modifiedText = stringBuilder.toString();
        return spanableData;
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

