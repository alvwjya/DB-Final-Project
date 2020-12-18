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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CashierController implements Initializable {

    public TextField customerIdField, productIdField, qtyField, changeField, payField, subtotalField;
    public Button addButton;
    public String salesId;
    public String subtotal;
    Connection connect = new Connection();

    ObservableList<ModelTableCashier> oblist = FXCollections.observableArrayList();
    @FXML
    private TableView<ModelTableCashier> cashierTable;


    public void newSalesButton() throws SQLException {
        // add query yg mskin customerId, sama "now" date, trus return "salesId"
        // nanti set variable "salesId" dari query tadi.
        // ini link reference: https://stackoverflow.com/questions/11442926/return-a-value-from-an-insert-query-in-mysql
        PreparedStatement prepStat = connect.getPrepStat("INSERT INTO Sales (customerId, salesDate) VALUES (" + customerIdField.getText() + ", now());");
        prepStat.executeUpdate();
        PreparedStatement prepStatSalesId = connect.getPrepStat("SELECT last_insert_id();");
        ResultSet rs = prepStatSalesId.executeQuery();
        salesId = rs.getString("last_insert_id");

    }

    public void AddButton() throws SQLException {
        // tambahin query buat mskin item nya ke "SALES DETAILS" table di database
        // yg dimasukin salesId, productId
        // trus add query yg return harga, and total harga (harga*qty)
        // and also bikin query yg return sum dari totalnya ke subtotal
        PreparedStatement prepStat = connect.getPrepStat("INSERT INTO SalesDetails (salesId, productId) VALUES (" + salesId + ", " + productIdField.getText() + ");");
        prepStat.executeUpdate();

    }

    public void payButton(){
        // bikin query yg mskin "paid" amount
    }

    public void showTable(){
        // add query yg show itemnya apa aja dari table "sales details"
        try {
            PreparedStatement prepStat = connect.getPrepStat("SELECT detailId, productName, productPrice,  FROM SalesDetails;");
            ResultSet rs = prepStat.executeQuery();

            while (rs.next()) {
                oblist.add(new ModelTableCashier(rs.getInt("detailId"), rs.getString()))
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showTable();

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
