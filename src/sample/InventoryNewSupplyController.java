package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryNewSupplyController {

    public TextField supplierIdField, productIdField, qtyField, priceField;
    Connection connect = new Connection();


    public void saveButton() throws SQLException {
        if (supplierIdField.getText().isEmpty() || productIdField.getText().isEmpty() || qtyField.getText().isEmpty() || priceField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Something Wrong!");
            alert.setHeaderText("Check your input");
            alert.setContentText("Make sure you fill all field with correct value");

            alert.show();
        } else {
            PreparedStatement prepStat = connect.getPrepStat("INSERT INTO Restock (supplierId, productId, restockDate, restockPrice) " +
                    "VALUES (" + supplierIdField.getText() + ", " + productIdField.getText() + ", now(), " + priceField.getText() + ");");
            PreparedStatement prepStatUpdate = connect.getPrepStat("UPDATE Inventory SET productQty = productQty + " + qtyField.getText() + " " +
                    "WHERE productId = " + productIdField.getText() + ";");
            prepStat.executeUpdate();
            prepStatUpdate.executeUpdate();
            Stage closeWindow = (Stage) productIdField.getScene().getWindow();
            closeWindow.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setContentText("Save Successful!");
            alert.setHeaderText("SAVED");

            alert.show();
        }
    }

}
