package com.example.javafxtableexample;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DataRow {

    private final SimpleIntegerProperty intData;
    private final SimpleStringProperty stringData;
    private final SimpleBooleanProperty boolData;
    private final SimpleBooleanProperty btnData;

    public DataRow(int intData, String stringData, boolean boolData, boolean btnData) {
        this.intData = new SimpleIntegerProperty(intData);
        this.stringData = new SimpleStringProperty(stringData);
        this.boolData = new SimpleBooleanProperty(boolData);
        this.btnData = new SimpleBooleanProperty(btnData);
    }

    public static DataRow getRandomRow(){
        return new DataRow(
                (int) (Math.random() * 100),
                getRandomString(10),
                Math.random() < 0.5,
                Math.random() < 0.5);
    }

    public int getIntData() {
        return intData.get();
    }

    public SimpleIntegerProperty intDataProperty() {
        return intData;
    }

    public void setIntData(int intData) {
        this.intData.set(intData);
    }

    public String getStringData() {
        return stringData.get();
    }

    public SimpleStringProperty stringDataProperty() {
        return stringData;
    }

    public void setStringData(String stringData) {
        this.stringData.set(stringData);
    }

    public boolean isBoolData() {
        return boolData.get();
    }

    public SimpleBooleanProperty boolDataProperty() {
        return boolData;
    }

    public void setBoolData(boolean boolData) {
        this.boolData.set(boolData);
    }

    public boolean isBtnData() {
        return btnData.get();
    }

    public SimpleBooleanProperty btnDataProperty() {
        return btnData;
    }

    public void setBtnData(boolean btnData) {
        this.btnData.set(btnData);
    }

    private static String getRandomString(int n) {
        String AlphaNumericString = "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
