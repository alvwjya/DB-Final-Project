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

public class CustomerEditController implements Initializable {

    public TextField customerNameField, customerContactField;
    public TextArea customerAddressField;
    public String selectedCityId;
    public int customerId;
    Connection connect = new Connection();

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

    public void saveButton() throws SQLException {

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
            PreparedStatement prepStat = connect.getPrepStat("UPDATE Customer SET customerName = '" + customerNameField.getText() + "', customerAddress = '" + customerAddressField.getText() + "', customerContact = '" + customerContactField.getText() + "', cityId = " + selectedCityId + " WHERE customerId = " + customerId + ";");
            prepStat.executeUpdate();
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

    public void preselectCityAndOthers() throws SQLException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                cityTable.requestFocus();
                cityTable.getSelectionModel().select(getCityIndex());
                cityTable.getFocusModel().focus(getCityIndex());
            }
        });

        //Add query yg return customer address, name, and contact berdasarkan customerId yg udh dipilih.
        //customerAddressField.setText(); <- ini buat set address field
        //customerContactField.setText(); <- ini buat set contact field
        //customerNameField.setText(); <- ini buat set name field
        PreparedStatement prepStat = connect.getPrepStat("SELECT customerName, customerAddress, customerContact FROM Customer WHERE customerId = " + customerId + ";");
        ResultSet rs = prepStat.executeQuery();
        customerNameField.setText(rs.getString("customerName"));
        customerAddressField.setText(rs.getString("customerAddress"));
        customerContactField.setText(rs.getString("customerContact"));
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
        try {
            preselectCityAndOthers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
