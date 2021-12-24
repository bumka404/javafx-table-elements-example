package com.example.javafxtableexample;

import java.util.function.Predicate;

public class PredicateContains<T> implements Predicate<DataRow> {

    private String oldValue;
    private String newValue;

    public void setValues(String oldValue, String newValue) {
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @Override
    public boolean test(DataRow row) {
        if (newValue == null || newValue.isEmpty()) return true;
        if (row.getStringData().contains(newValue) || String.valueOf(row.getIntData()).contains(newValue))
            return true;
        return false;
    }
}
