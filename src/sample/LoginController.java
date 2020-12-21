package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    public Button loginButton;
    public TextField usernameField;
    public PasswordField passwordField;
    Connection connect = new Connection();

    public void loginButton() throws SQLException {
        PreparedStatement prepStat = connect.getPrepStat("SELECT * FROM Employee WHERE username = '" + usernameField.getText() + "' AND pass = '" + passwordField.getText() + "';");
        ResultSet rs = prepStat.executeQuery();
        if (!rs.next()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Something Wrong!");
            alert.setHeaderText("Wrong Username or Password!");
            alert.setContentText("Make sure you input the right info!");

        } else {
            try {
                FXMLLoader loader= new FXMLLoader(getClass().getResource("Home.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Nu Aneka");
                stage.show();
                Stage closeWindow = (Stage) loginButton.getScene().getWindow();
                closeWindow.close();
            } catch (IOException e){
                System.out.println("File not found!");
            }
        }



    }
}
