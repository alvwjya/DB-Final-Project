package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {

    public int productId;
    Connection connect = new Connection();

    ObservableList<ModelTableInventory> oblist = FXCollections.observableArrayList();

    @FXML
    private TableView<ModelTableInventory> inventoryTable;


    public void newSupplyButton(){
        try{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("InventoryNewSupply.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Nu Aneka-New Supply");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void newItemButton(){
        try{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("InventoryNewItem.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Nu Aneka-New Item");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void editButton(){
        try{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("InventoryEdit.fxml"));
            Parent root = loader.load();
            InventoryEditController iController = loader.getController();
            iController.setProductId(productId);
            iController.loadFirst();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Nu Aneka-Edit Item");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getProductId(){
        ModelTableInventory inventory = inventoryTable.getSelectionModel().getSelectedItem();
        productId = inventory.getProductId();
    }


    public void showTable(){
        try {
            PreparedStatement prepStat = connect.getPrepStat("SELECT productId, productName, productQty, category, productPrice " +
                    "FROM Inventory, Category WHERE Inventory.categoryId = Category.categoryId;");
            ResultSet rs = prepStat.executeQuery();

            while (rs.next()) {
                oblist.add(new ModelTableInventory(rs.getInt("productId"), rs.getString("productName"),
                        rs.getString("productQty"), rs.getString("category"), rs.getString("productPrice")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void refreshButton(){
        inventoryTable.getItems().clear();
        showTable();
    }


    public void deleteButton() throws SQLException {
        try {
            // show confirmation of deletion
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("This will remove it permanently from the database.");
            alert.setHeaderText("Are you sure want to delete this product?");
            Optional<ButtonType> result = alert.showAndWait();

            // If user press "OK" button
            if (result.isPresent() && result.get() == ButtonType.OK) {
                PreparedStatement prepStat = connect.getPrepStat("DELETE FROM Inventory WHERE productId = " + productId + ";");
                prepStat.executeUpdate();
                productId = 0;
                refreshButton();
            } else {
                alert.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        refreshButton();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showTable();

        TableColumn idCol = new TableColumn("ID");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableInventory, Integer>("productId"));
        TableColumn nameCol = new TableColumn("Product Name");
        nameCol.setMinWidth(300);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableInventory, String>("productName"));
        TableColumn qtyCol = new TableColumn("Qty.");
        qtyCol.setMinWidth(100);
        qtyCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableInventory, String>("productQty"));
        TableColumn catCol = new TableColumn("Category");
        catCol.setMinWidth(200);
        catCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableInventory, String>("productCategory"));
        TableColumn priceCol = new TableColumn("Price");
        priceCol.setMinWidth(190);
        priceCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableInventory, String>("productPrice"));
        inventoryTable.setItems(oblist);
        inventoryTable.getColumns().addAll(idCol, nameCol, qtyCol, catCol, priceCol);
    }
}
