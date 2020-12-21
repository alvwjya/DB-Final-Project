package sample;

import javafx.application.Platform;
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

public class SupplierController implements Initializable {

    public int supplierId;
    Connection connect = new Connection();

    ObservableList<ModelTableSupplier> oblist = FXCollections.observableArrayList();
    @FXML
    private TableView<ModelTableSupplier> supplierTable;

    public void addSupplierButton(){
        try{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("SupplierAdd.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Nu Aneka-New Supplier");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getSupplierId(){
        ModelTableSupplier supplier = supplierTable.getSelectionModel().getSelectedItem();
        supplierId = supplier.getSupplierId();
        System.out.println("THIS IS BEFORE " + supplierId); //Check Only

    }

    public void editButton(){
        try{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("SupplierEdit.fxml"));
            Parent root = loader.load();
            SupplierEditController sController = loader.getController();
            sController.setSupplierId(supplierId);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Nu Aneka-Edit Supplier");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showTable(){
        // add code + query here to fill the table with supplier details
        try {
            PreparedStatement prepStat = connect.getPrepStat("SELECT supplierId, supplierName, supplierAddress, city, supplierContact FROM Supplier, City WHERE Supplier.cityId = City.cityId;");
            ResultSet rs = prepStat.executeQuery();

            while (rs.next()) {
                oblist.add(new ModelTableSupplier(rs.getInt("supplierId"), rs.getString("supplierName"), rs.getString("supplierAddress"), rs.getString("city"), rs.getString("supplierContact")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void refreshButton(){
        supplierTable.getItems().clear();
        showTable();
    }

    public void deleteButton(){
        // add query delete
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showTable();


        TableColumn idCol = new TableColumn("ID");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableSupplier, Integer>("supplierId"));
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(200);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableSupplier, String>("supplierName"));
        TableColumn addressCol = new TableColumn("Address");
        addressCol.setMinWidth(250);
        addressCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableSupplier, String>("supplierAddress"));
        TableColumn cityCol = new TableColumn("City");
        cityCol.setMinWidth(150);
        cityCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableSupplier, String>("city"));
        TableColumn contactCol = new TableColumn("Contact");
        contactCol.setMinWidth(190);
        contactCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableSupplier, String>("supplierContact"));
        supplierTable.setItems(oblist);
        supplierTable.getColumns().addAll(idCol, nameCol, addressCol, cityCol, contactCol);
    }

}
