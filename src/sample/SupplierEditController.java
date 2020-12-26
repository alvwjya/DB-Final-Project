package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SupplierEditController {

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
            getCity();
            PreparedStatement prepStat = connect.getPrepStat("UPDATE Supplier SET supplierName = '" + supplierNameField.getText() + "', supplierAddress = '" + supplierAddressField.getText() + "', supplierContact = '" + supplierContactField.getText() + "', cityId = " + selectedCityId + " WHERE supplierId = " + supplierId + ";");
            prepStat.executeUpdate();
            Stage closeWindow = (Stage) supplierNameField.getScene().getWindow();
            closeWindow.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setContentText("Save Successful!");
            alert.setHeaderText("SAVED");
            alert.show();

        }
    }

    public int getCityIndex() throws SQLException {
        int cityId = 0;
        try{
            PreparedStatement prepStat = connect.getPrepStat("SELECT cityId FROM Supplier WHERE supplierId = " + supplierId + ";");
            ResultSet rs = prepStat.executeQuery();
            if(rs.next()){
                cityId = rs.getInt("cityId");
            }
            for (int i = 0; i < cityTable.getItems().size(); i++) {
                if (cityTable.getItems().get(i).getCityId() == cityId) {
                    return i;
                }
            }
            return 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }

    }

    public void preselectCityAndOthers() throws SQLException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                cityTable.requestFocus();
                try {
                    cityTable.getSelectionModel().select(getCityIndex());
                    cityTable.getFocusModel().focus(getCityIndex());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        PreparedStatement prepStat = connect.getPrepStat("SELECT supplierName, supplierAddress, supplierContact FROM Supplier WHERE supplierId = " + supplierId + ";");
        ResultSet rs = prepStat.executeQuery();
        if(rs.next()){
            supplierNameField.setText(rs.getString("supplierName"));
            supplierAddressField.setText(rs.getString("supplierAddress"));
            supplierContactField.setText(rs.getString("supplierContact"));
        }
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


    public void loadFirst() {
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
