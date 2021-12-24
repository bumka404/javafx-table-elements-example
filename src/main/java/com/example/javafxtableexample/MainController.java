package com.example.javafxtableexample;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.function.Predicate;

public class MainController {
    @FXML
    private Button generateBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private Button addBtn;

    @FXML
    private ToggleButton toggleOddBtn;
    @FXML
    private ToggleButton toggleWithDBtn;
    @FXML
    private ToggleButton toggleTrueBtn;
    @FXML
    private ToggleButton toggleRedBtn;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<DataRow> mainTable;
    @FXML
    private TableColumn<DataRow, Integer> integerTableColumn;
    @FXML
    private TableColumn<DataRow, String> stringTableColumn;
    @FXML
    private TableColumn<DataRow, Boolean> booleanTableColumn;
    @FXML
    private TableColumn<DataRow, Boolean> buttonsColumn;

    private ObservableList<DataRow> dataRowObservableList = FXCollections.observableArrayList();
    private FilteredList<DataRow> filteredData;
    ObservableList<Predicate<DataRow>> dataFilters = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        integerTableColumn.setCellValueFactory(new PropertyValueFactory<>("intData"));
        stringTableColumn.setCellValueFactory(new PropertyValueFactory<>("stringData"));
        booleanTableColumn.setCellValueFactory(new PropertyValueFactory<>("boolData"));

        Callback<TableColumn<DataRow, Boolean>, TableCell<DataRow, Boolean>> cellFactory =
                (final TableColumn<DataRow, Boolean> param) -> {
                    final TableCell<DataRow, Boolean> cell = new TableCell<DataRow, Boolean>() {
                        private Button btn = new Button();

                        {
                            btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                            btn.setOnAction(click -> {
                                if (btn.getText().contains("GREEN")) changeBtnToRed();
                                else changeBtnToGreen();
                            });
                        }

                        public void updateItem(Boolean item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                if (item.booleanValue()) changeBtnToGreen();
                                else changeBtnToRed();
                                setGraphic(btn);
                            }
                        }

                        private void changeBtnToGreen() {
                            if (getGraphic() != null) getTableRow().getItem().setBtnData(true);
                            btn.setText("GREEN");
                            btn.setStyle("-fx-background-color: #00ff00"); //GREEN
                        }

                        private void changeBtnToRed() {
                            if (getGraphic() != null) getTableRow().getItem().setBtnData(false);
                            btn.setText("RED");
                            btn.setStyle("-fx-background-color: #ee0000"); //RED
                        }
                    };
                    return cell;
                };

        dataRowObservableList.addAll(generate());

        buttonsColumn.setCellValueFactory(cellData -> cellData.getValue().btnDataProperty());
        buttonsColumn.setCellFactory(cellFactory);

        filteredData = new FilteredList<DataRow>(dataRowObservableList, p -> true);

        searchField.textProperty().addListener(getSearchChangeListener());
        PredicateContains<DataRow> predicateContains = new PredicateContains<DataRow>();
        dataFilters.add(predicateContains);
        filteredData.predicateProperty().bind(Bindings.createObjectBinding(() ->
                        dataFilters.stream().reduce(x -> true, Predicate::and),
                dataFilters));

        setDataFilterElements();

        mainTable.setItems(filteredData);
    }

    @FXML
    private void onGenerateBtnClick(ActionEvent actionEvent) {
        dataRowObservableList.clear();
        dataRowObservableList.addAll(generate());
    }

    @FXML
    private void onClearBtnClick(ActionEvent actionEvent) {
        dataRowObservableList.clear();
    }

    @FXML
    private void onAddBtnClick(ActionEvent actionEvent) {
        dataRowObservableList.add(DataRow.getRandomRow());
    }

    private ObservableList<DataRow> generate() {
        ObservableList<DataRow> dataRows = FXCollections.observableArrayList();
        for (int i = 0; i < 10; i++) {
            dataRows.add(DataRow.getRandomRow());
        }
        return dataRows;
    }

    private ChangeListener<String> getSearchChangeListener() {
        return new ChangeListener<String>() {
            PredicateContains<DataRow> predicateContains = new PredicateContains<DataRow>();

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldvalue, String newValue) {
                dataFilters.remove(predicateContains);
                predicateContains.setValues(oldvalue, newValue);
                dataFilters.add(predicateContains);
            };
        };
    }

    private ChangeListener<Boolean> getTableFiltersChangeListener(Predicate p) {
        return new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (t1) dataFilters.add(p);
                else dataFilters.remove(p);
            }
        };
    }

    private void setDataFilterElements() {
        Predicate<DataRow> predicateInt = row -> row.getIntData() % 2 != 0;
        Predicate<DataRow> predicateTrue = row -> row.boolDataProperty().get();
        Predicate<DataRow> predicateRed = row -> !row.btnDataProperty().get();
        Predicate<DataRow> predicateWithD = row -> row.getStringData().toLowerCase().contains("d");

        toggleOddBtn.selectedProperty().addListener(getTableFiltersChangeListener(predicateInt));
        toggleTrueBtn.selectedProperty().addListener(getTableFiltersChangeListener(predicateTrue));
        toggleRedBtn.selectedProperty().addListener(getTableFiltersChangeListener(predicateRed));
        toggleWithDBtn.selectedProperty().addListener(getTableFiltersChangeListener(predicateWithD));
    }

}