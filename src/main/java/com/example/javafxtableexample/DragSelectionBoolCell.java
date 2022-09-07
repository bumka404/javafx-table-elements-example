package com.example.javafxtableexample;

import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

public class DragSelectionBoolCell extends TableCell<DataRow, Boolean> {

    public DragSelectionBoolCell() {
        setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                startFullDrag();
                getTableColumn().getTableView().getSelectionModel().select(getIndex(), getTableColumn());
            }
        });
        setOnMouseDragEntered(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent event) {

                getTableColumn().getTableView().getSelectionModel().select(getIndex(), getTableColumn());
            }
        });

    }
    @Override
    public void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
        } else {
            setText(String.valueOf(item));
        }
    }
}
