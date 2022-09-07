package com.example.javafxtableexample;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class DragSelectionCellFactory<R, T> implements Callback<TableColumn<R, T>, TableCell<R, T>> {
    @Override
    public TableCell<R, T> call(final TableColumn<R, T> col) {
        return new DragSelectingTableCell<R, T>();
    }
}