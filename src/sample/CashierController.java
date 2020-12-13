package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CashierController {

    public void addSalesButton(){
        try{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("AddSales.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Nu Aneka");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
