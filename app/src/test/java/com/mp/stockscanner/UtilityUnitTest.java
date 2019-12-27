package com.mp.stockscanner;

import com.mp.stockscanner.models.Variable;
import com.mp.stockscanner.scanner.Utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UtilityUnitTest {
    @Test
    public void assertSpanAreReplaced() {
        HashMap<String, Variable> stringHashMap = new HashMap<>();
        String text = "Today's Volume > prev $2 Vol SMA by $3 x";
        Variable variable = new Variable();
        variable.setDefaultValue(50);
        stringHashMap.put("$2", variable);

        String modifiedText = Utils.getSpanableData(text, stringHashMap).modifiedText;

        assertEquals(modifiedText, "Today's Volume > prev (50) Vol SMA by $3 x");
    }

    @Test
    public void assertSpanAreReplacedForArray() {
        HashMap<String, Variable> stringHashMap = new HashMap<>();
        String text = "Today's Volume > prev $2 Vol SMA by $3 x";
        Variable variable = new Variable();
        variable.setDefaultValue(50);
        List<Double> doubles = new ArrayList<>();
        doubles.add(100.0);
        doubles.add(200.0);
        variable.setValues(doubles);
        stringHashMap.put("$2", variable);

        Variable variableNew = new Variable();
        variableNew.setDefaultValue(50);
        stringHashMap.put("$3", variableNew);

        String modifiedText = Utils.getSpanableData(text, stringHashMap).modifiedText;

        assertEquals(modifiedText, "Today's Volume > prev (100.0) Vol SMA by (50) x");
    }
}