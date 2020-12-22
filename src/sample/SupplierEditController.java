package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SupplierEditController implements Initializable {

    public TextField supplierNameField, supplierContactField;
    public TextArea supplierAddressField;
    public String selectedCityId;
    public int supplierId;
    Connection connect = new Connection();

    ObservableList<ModelTableCity> oblist = FXCollections.observableArrayList();
    @FXML
    private TableView<ModelTableCity> cityTable;

    public void getCity(){
        ModelTableCity city = cityTable.getSelectionModel().getSelectedItem();
        selectedCityId = String.valueOf(city.getCityId());
    }

    public void setSupplierId(Integer supplierId){
        this.supplierId = supplierId;
    }

    public void saveButton() throws SQLException {
        System.out.println("THIS IS AFTER " + supplierId); //Check Only
        if (supplierAddressField.getText().isEmpty() || supplierContactField.getText().isEmpty() || supplierNameField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Something Wrong!");
            alert.setHeaderText("Check your input");
            alert.setContentText("Make sure you fill all field with correct value");
            alert.show();
        }
        else{
            // add query here to edit customer, customernya bisa diambil dari variable customerId, trus yg bisa diganti nama, address, contact, city
            getCity();
            PreparedStatement prepStat = connect.getPrepStat("UPDATE Supplier SET supplierName = '" + supplierNameField.getText() + "', supplierAddress = '" + supplierAddressField.getText() + "', supplierContact = '" + supplierContactField.getText() + "', cityId = " + selectedCityId + " WHERE customerId = " + supplierId + ";");
            prepStat.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setContentText("Save Successful!");
            alert.setHeaderText("SAVED");
            alert.show();

        }

        // This is for test only
        System.out.println(supplierAddressField.getText());
        System.out.println(supplierContactField.getText());
        System.out.println(supplierNameField.getText());
        System.out.println(selectedCityId);
    }

    public int getCityIndex(){
        for (int i = 0; i < cityTable.getItems().size(); i++) {
            if (cityTable.getItems().get(i).getCityId() == 1) { // <- ini '==' nya diliat dari database, bikin query yg return cityId nya supplier
                return i;
            }
        }
        return 0;
    }

    public void preselectCityAndOthers() throws SQLException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                cityTable.requestFocus();
                cityTable.getSelectionModel().select(getCityIndex());
                cityTable.getFocusModel().focus(getCityIndex());
            }
        });
        // add query yg return name, contact, address supplier dari supplierId yg udh diselect
        //supplierNameField.setText(); <- ini buat set name field
        //supplierContactField.setText(); <- ini buat set conact field
        //supplierAddressField.setText(); <- ini buat set address field
        PreparedStatement prepStat = connect.getPrepStat("SELECT supplierName, supplierAddress, supplierContact FROM Supplier WHERE supplierId = " + supplierId + ";");
        ResultSet rs = prepStat.executeQuery();
    }


    public void showTable(){
        // add code + query here to fill the table with cityId, city, and province.
        try {
            PreparedStatement prepStat = connect.getPrepStat("SELECT cityId, city, province FROM City, Province WHERE City.provinceId = Province.provinceId;");
            ResultSet rs = prepStat.executeQuery();

            while (rs.next()) {
                oblist.add(new ModelTableCity(rs.getInt("cityId"), rs.getString("city"), rs.getString("province")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showTable();
        try {
            preselectCityAndOthers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
