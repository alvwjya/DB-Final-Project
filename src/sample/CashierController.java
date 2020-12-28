package sample;

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

public class CashierController implements Initializable {

    public TextField customerIdField, productIdField, qtyField, changeField, payField, subtotalField;
    public Button addButton;
    public String salesId;
    public int subtotal;
    Connection connect = new Connection();

    ObservableList<ModelTableCashier> oblist = FXCollections.observableArrayList();
    @FXML
    private TableView<ModelTableCashier> cashierTable;


    public void newSalesButton() throws SQLException {

        if (customerIdField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Something Wrong!");
            alert.setHeaderText("Check your input");
            alert.setContentText("Make sure you fill all field with correct value");

            alert.show();
        } else {
            PreparedStatement prepStat = connect.getPrepStat("INSERT INTO Sales (customerId, salesDate) VALUES (" + customerIdField.getText() + ", now());");
            prepStat.executeUpdate();
            PreparedStatement prepStatSalesId = connect.getPrepStat("SELECT LAST_INSERT_ID();");
            ResultSet rs = prepStatSalesId.executeQuery();

            if (rs.next()) {
                salesId = String.valueOf(rs.getInt(1));
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Success created new sales");

            alert.show();
        }
    }


    public void AddButton() throws SQLException {

        if (productIdField.getText().isEmpty() || qtyField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Something Wrong!");
            alert.setHeaderText("Check your input");
            alert.setContentText("Make sure you fill all field with correct value");

            alert.show();
        } else {
            PreparedStatement prepStatCheck = connect.getPrepStat("SELECT productQty FROM Inventory WHERE productId = " + productIdField.getText() + ";");
            ResultSet rsc = prepStatCheck.executeQuery();

            if (rsc.next()) {

                if (rsc.getInt("productQty") < Integer.parseInt(qtyField.getText())) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Something Wrong");
                    alert.setContentText("Not Enough Product!");

                    alert.show();
                } else {
                    PreparedStatement prepStat = connect.getPrepStat("INSERT INTO SalesDetails (salesId, productId, qty, total) VALUES (" + salesId + ", " + productIdField.getText() + ", " + qtyField.getText() + ", (SELECT (productPrice * " + qtyField.getText() + ") FROM Inventory WHERE productId = " + productIdField.getText() + "));");
                    prepStat.executeUpdate();

                    PreparedStatement prepStatTotal = connect.getPrepStat("SELECT SUM(total) FROM SalesDetails WHERE salesId = " + salesId + ";");
                    ResultSet rsTotal = prepStatTotal.executeQuery();

                    while (rsTotal.next()) {
                        subtotal = rsTotal.getInt(1);
                    }

                    PreparedStatement prepStatUpdateInventory = connect.getPrepStat("UPDATE Inventory SET productQty = productQty - " + qtyField.getText() + " WHERE productId = " + productIdField.getText() + ";");
                    prepStatUpdateInventory.executeUpdate();
                    subtotalField.setText(String.valueOf(subtotal));
                    qtyField.clear();
                    productIdField.clear();
                }
            }
        }
        showTable();
    }

    public void payButton() throws SQLException {
        if (payField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Something Wrong!");
            alert.setHeaderText("Check your input");
            alert.setContentText("Make sure you fill all field with correct value");

            alert.show();
        } else {
            PreparedStatement prepStatUpdateSubtotal = connect.getPrepStat("UPDATE Sales SET subTotal = " + subtotal + " WHERE salesId = " + salesId + ";");
            prepStatUpdateSubtotal.executeUpdate();

            if (Integer.parseInt(payField.getText()) <= subtotal) {
                PreparedStatement prepStatPay = connect.getPrepStat("UPDATE Sales SET paid = " + payField.getText() + " WHERE salesId = " + salesId + ";");
                prepStatPay.executeUpdate();
                changeField.setText("0");
            } else {
                PreparedStatement prepStatPay = connect.getPrepStat("UPDATE Sales SET paid = " + subtotal + " WHERE salesId = " + salesId + ";");
                prepStatPay.executeUpdate();
                changeField.setText(String.valueOf(Integer.parseInt(payField.getText()) - subtotal));
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setContentText("Payment Successful!");
            alert.setHeaderText("PAID");

            alert.showAndWait();

            cashierTable.getItems().clear();
            subtotalField.clear();
            changeField.clear();
            qtyField.clear();
            productIdField.clear();
            customerIdField.clear();
            payField.clear();
        }

    }


    public void showTable() {
        cashierTable.getItems().clear();
        try {
            PreparedStatement prepStat = connect.getPrepStat("SELECT * FROM SalesDetails INNER JOIN Inventory ON SalesDetails.productId = Inventory.productId WHERE SalesDetails.salesId = " + salesId + ";");
            ResultSet rs = prepStat.executeQuery();

            while (rs.next()) {
                oblist.add(new ModelTableCashier(rs.getString("Inventory.productName"), rs.getInt("Inventory.productPrice"), rs.getInt("salesDetails.qty"), rs.getInt("salesDetails.total")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn nameCol = new TableColumn("Product");
        nameCol.setMinWidth(250);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableCashier, String>("product"));
        TableColumn addressCol = new TableColumn("Price");
        addressCol.setMinWidth(235);
        addressCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableCashier, Integer>("price"));
        TableColumn cityCol = new TableColumn("Qty.");
        cityCol.setMinWidth(50);
        cityCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableCashier, Integer>("qty"));
        TableColumn contactCol = new TableColumn("Total");
        contactCol.setMinWidth(235);
        contactCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableCashier, Integer>("total"));
        cashierTable.setItems(oblist);
        cashierTable.getColumns().addAll(nameCol, addressCol, cityCol, contactCol);
    }
}
