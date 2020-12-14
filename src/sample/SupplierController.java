package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SupplierController {

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

    public void editButton(){
        try{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("SupplierEdit.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Nu Aneka-Edit Supplier");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
