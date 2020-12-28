package sample;

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

public class CustomerAddController implements Initializable {

    public TextField customerNameField, customerContactField;
    public TextArea customerAddressField;
    public String selectedCity;
    public Connection connect = new Connection();

    ObservableList<ModelTableCity> oblist = FXCollections.observableArrayList();

    @FXML
    private TableView<ModelTableCity> cityTable;


    public void getCity() {
        ModelTableCity city = cityTable.getSelectionModel().getSelectedItem();
        selectedCity = String.valueOf(city.getCityId());
    }


    public void saveButton() throws SQLException {
        if (customerAddressField.getText().isEmpty() || customerContactField.getText().isEmpty() || customerNameField.getText().isEmpty() || (selectedCity.equals(""))) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Something Wrong!");
            alert.setHeaderText("Check your input");
            alert.setContentText("Make sure you fill all field with correct value");
            alert.show();
        } else {
            PreparedStatement prepStat = connect.getPrepStat("INSERT INTO Customer (customerName, customerAddress, cityId, customerContact) VALUES ('" + customerNameField.getText() + "', '" + customerAddressField.getText() + "', " + selectedCity + ", '" + customerContactField.getText() + "');");
            prepStat.executeUpdate();
            Stage closeWindow = (Stage) customerNameField.getScene().getWindow();
            closeWindow.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setContentText("Save Successful!");
            alert.setHeaderText("SAVED");

            alert.show();
        }
    }


    public void showTable() {
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
