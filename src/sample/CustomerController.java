package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    public int customerId;
    Connection connect = new Connection();

    ObservableList<ModelTableCustomer> oblist = FXCollections.observableArrayList();
    @FXML
    private TableView<ModelTableCustomer> customerTable;

    public void addCustomerButton(){
        try{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("CustomerAdd.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Nu Aneka-New Customer");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getCustomerId(){
        ModelTableCustomer customer = customerTable.getSelectionModel().getSelectedItem();
        customerId = customer.getCustomerId();
        System.out.println("THIS IS BEFORE "  + customerId); //Check Only
    }

    public void editButton(){
        try{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("CustomerEdit.fxml"));
            Parent root = loader.load();
            CustomerEditController cController = loader.getController();
            cController.setCustomerId(customerId);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Nu Aneka-Edit Customer");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showTable(){
        // add code + query here to fill the table with customer details
        try {
            PreparedStatement prepStat = connect.getPrepStat("SELECT customerId, customerName, customerAddress, city, customerContact FROM Customer, City WHERE Customer.cityId = City.cityId;");
            ResultSet rs = prepStat.executeQuery();

            while (rs.next()) {
                oblist.add(new ModelTableCustomer(rs.getInt("customerId"), rs.getString("customerName"), rs.getString("customerAddress"), rs.getString("city"), rs.getString("customerContact")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void refreshButton(){
        customerTable.getItems().clear();
        customerTable.getColumns().clear();
        showTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        oblist.add(new ModelTableCustomer(1, "KOKO", "IJ", "ju", "binus"));

        TableColumn idCol = new TableColumn("ID");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableCustomer, Integer>("customerId"));
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(200);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableCustomer, String>("customerName"));
        TableColumn addressCol = new TableColumn("Address");
        addressCol.setMinWidth(250);
        addressCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableCustomer, String>("customerAddress"));
        TableColumn cityCol = new TableColumn("City");
        cityCol.setMinWidth(150);
        cityCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableCustomer, String>("city"));
        TableColumn contactCol = new TableColumn("Contact");
        contactCol.setMinWidth(190);
        contactCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableCustomer, String>("customerContact"));
        customerTable.setItems(oblist);
        customerTable.getColumns().addAll(idCol, nameCol, addressCol, cityCol, contactCol);
    }
}
