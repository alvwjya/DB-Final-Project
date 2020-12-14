package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomerController {

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

    public void editButton(){
        try{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("CustomerEdit.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Nu Aneka-Edit Customer");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
