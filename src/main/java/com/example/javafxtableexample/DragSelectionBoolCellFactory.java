package com.example.javafxtableexample;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class DragSelectionBoolCellFactory implements Callback<TableColumn<DataRow, Boolean>, TableCell<DataRow, Boolean>> {
    @Override
    public TableCell<DataRow, Boolean> call(final TableColumn<DataRow, Boolean> col) {
        return new DragSelectionBoolCell();
    }
}