package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InventoryController {

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
}
