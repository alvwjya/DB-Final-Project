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
import java.util.ResourceBundle;

public class InventoryController implements Initializable {

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

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Nu Aneka-Edit Item");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        oblist.add(new ModelTableInventory(1, "KOKO", "3", "ju", "3000"));

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
