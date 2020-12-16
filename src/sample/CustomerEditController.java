package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerEditController implements Initializable {

    public TextField customerNameField, customerContactField;
    public TextArea customerAddressField;
    public String selectedCityId;
    public int customerId;

    ObservableList<ModelTableCity> oblist = FXCollections.observableArrayList();
    @FXML
    private TableView<ModelTableCity> cityTable;

    public void getCity(){
        ModelTableCity city = cityTable.getSelectionModel().getSelectedItem();
        selectedCityId = String.valueOf(city.getCityId());
    }

    public void setCustomerId(Integer customerId){
        this.customerId = customerId;
    }

    public void saveButton(){

        System.out.println("THIS IS AFTER " + customerId); //Check Only
        if (customerAddressField.getText().isEmpty() || customerContactField.getText().isEmpty() || customerNameField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Something Wrong!");
            alert.setHeaderText("Check your input");
            alert.setContentText("Make sure you fill all field with correct value");
            alert.show();
        }
        else{
            // add query here to edit customer, customernya bisa diambil dari variable customerId, trus yg bisa diganti nama, address, contact, city

            getCity();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setContentText("Save Successful!");
            alert.setHeaderText("SAVED");
            alert.show();

        }

        // This is for test only
        System.out.println(customerAddressField.getText());
        System.out.println(customerContactField.getText());
        System.out.println(customerNameField.getText());
        System.out.println(selectedCityId);
    }



        public int getCityIndex(){
            for (int i = 0; i < cityTable.getItems().size(); i++) {
                if (cityTable.getItems().get(i).getCityId() == 1) { // <- ini '==' nya diliat dari database, bikin query yg return cityId customer
                    return i;
                }
            }
            return 0;
    }

    public void preselectCity(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                cityTable.requestFocus();
                cityTable.getSelectionModel().select(getCityIndex());
                cityTable.getFocusModel().focus(getCityIndex());
            }
        });
    }


    public void showTable(){
        // add code + query here to fill the table with cityId, city, and province.
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        preselectCity();

        // This is for test only
        oblist.add(new ModelTableCity(1, "Bandung", "JAWA BARAT"));
        oblist.add(new ModelTableCity(2, "Depok", "JAWA BARAT"));

        showTable();

        System.out.println(oblist);
        TableColumn movCol = new TableColumn("City");
        movCol.setMinWidth(200);
        movCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableCity, String>("city"));

        TableColumn ratCol = new TableColumn("Province");
        ratCol.setMinWidth(200);
        ratCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableCity, String>("province"));
        cityTable.setItems(oblist);
        cityTable.getColumns().addAll(movCol, ratCol);
    }
}
