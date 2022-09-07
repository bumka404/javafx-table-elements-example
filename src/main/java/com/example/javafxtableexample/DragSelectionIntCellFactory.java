package com.example.javafxtableexample;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class DragSelectionIntCellFactory implements Callback<TableColumn<DataRow, Integer>, TableCell<DataRow, Integer>> {
    @Override
    public TableCell<DataRow, Integer> call(final TableColumn<DataRow, Integer> col) {
        return new DragSelectionIntCell();
    }
}