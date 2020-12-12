package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    public Button loginButton;
    public TextField usernameField;
    public PasswordField passwordField;

    public void loginButton(){
        try{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Nu Aneka");
            stage.show();
            Stage closeWindow = (Stage) loginButton.getScene().getWindow();
            closeWindow.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
