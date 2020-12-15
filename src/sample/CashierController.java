package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CashierController implements Initializable {

    public TextField customerIdField, productIdField, qtyField, changeField, payField, subtotalField;
    public Button addButton, deleteButton;

    ObservableList<ModelTableCashier> oblist = FXCollections.observableArrayList();
    @FXML
    private TableView<ModelTableCashier> cashierTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TableColumn idCol = new TableColumn("No.");
        idCol.setMinWidth(50);
        idCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableCashier, Integer>("number"));
        TableColumn nameCol = new TableColumn("Product");
        nameCol.setMinWidth(250);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableCashier, String>("productName"));
        TableColumn addressCol = new TableColumn("Price");
        addressCol.setMinWidth(235);
        addressCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableCashier, String>("productPrice"));
        TableColumn cityCol = new TableColumn("Qty.");
        cityCol.setMinWidth(50);
        cityCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableCashier, String>("productQty"));
        TableColumn contactCol = new TableColumn("Total");
        contactCol.setMinWidth(235);
        contactCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableCashier, String>("total"));
        cashierTable.setItems(oblist);
        cashierTable.getColumns().addAll(idCol, nameCol, addressCol, cityCol, contactCol);

    }
}
