package com.example.javafxtableexample;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class DragSelectionStringCellFactory implements Callback<TableColumn<DataRow, String>, TableCell<DataRow, String>> {
    @Override
    public TableCell<DataRow, String> call(final TableColumn<DataRow, String> col) {
        return new DragSelectionStringCell();
    }
}