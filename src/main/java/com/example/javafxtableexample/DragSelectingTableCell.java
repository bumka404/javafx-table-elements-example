package com.example.javafxtableexample;

import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

public class DragSelectingTableCell<R, T> extends TableCell<R, T> {
    private R row;
    private T type;

    public DragSelectingTableCell() {
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
    public void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
        } else {
            setText(String.valueOf(item));
        }
    }
}
