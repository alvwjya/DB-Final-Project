package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerAddController implements Initializable {

    public TextField customerNameField, customerContactField;
    public TextArea customerAddressField;
    public String selectedCity;

    ObservableList<ModelTableCity> oblist = FXCollections.observableArrayList();
    @FXML
    private TableView<ModelTableCity> cityTable;

    public void getCity(){
        ModelTableCity city = cityTable.getSelectionModel().getSelectedItem();
        selectedCity = String.valueOf(city.getCityId());
    }

    public void saveButton(){


        if (customerAddressField.getText().isEmpty() || customerContactField.getText().isEmpty() || customerNameField.getText().isEmpty() || (selectedCity.equals(""))){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Something Wrong!");
            alert.setHeaderText("Check your input");
            alert.setContentText("Make sure you fill all field with correct value");
            alert.show();
        }
        else{
            // add query here to add customer
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
        System.out.println(selectedCity);



    }

    public void showTable(){
        // add code + query here to fill the table with cityId, city, and province.
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // This is for test only
        oblist.add(new ModelTableCity(1, "Bandung", "JAWA BARAT"));
        oblist.add(new ModelTableCity(2, "Depok", "JAWA BARAT"));

        showTable();


        TableColumn cityCol = new TableColumn("City");
        cityCol.setMinWidth(200);
        cityCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableCity, String>("city"));

        TableColumn provinceCol = new TableColumn("Province");
        provinceCol.setMinWidth(200);
        provinceCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableCity, String>("province"));
        cityTable.setItems(oblist);
        cityTable.getColumns().addAll(cityCol, provinceCol);
    }
}
