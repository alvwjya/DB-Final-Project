package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InventoryNewItemController implements Initializable {
    public TextField productNameField, priceField;
    public String selectedCategory;
    Connection connect = new Connection();

    ObservableList<ModelTableCategory> oblist = FXCollections.observableArrayList();
    @FXML
    private TableView<ModelTableCategory> categoryTable;

    public void getCategory(){
        ModelTableCategory category = categoryTable.getSelectionModel().getSelectedItem();
        selectedCategory = String.valueOf(category.getCategoryId());
    }

    public void saveButton() throws SQLException {
        if (productNameField.getText().isEmpty() || priceField.getText().isEmpty() || selectedCategory.equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Something Wrong!");
            alert.setHeaderText("Check your input");
            alert.setContentText("Make sure you fill all field with correct value");
            alert.show();
        }
        else{
            getCategory();
            PreparedStatement prepStat = connect.getPrepStat("INSERT INTO Inventory (productName, categoryId, productPrice) VALUES ('" + productNameField.getText() + "', " + selectedCategory + ", " + priceField.getText() + ");");
            prepStat.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setContentText("Save Successful!");
            alert.setHeaderText("SAVED");
            alert.show();
        }

        // This is for test only
        System.out.println(productNameField.getText());
        System.out.println(priceField.getText());
        System.out.println(selectedCategory);
    }

    public void showTable(){
        // add code + query here to fill the table with categoryId, category.
        try {
            PreparedStatement prepStat = connect.getPrepStat("SELECT * FROM Category;");
            ResultSet rs = prepStat.executeQuery();

            while (rs.next()) {
                oblist.add(new ModelTableCategory(rs.getInt("categoryId"), rs.getString("category")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        oblist.add(new ModelTableCategory(1, "Ballpoint"));
        oblist.add(new ModelTableCategory(2, "Drawing Book"));
        showTable();


        TableColumn idCol = new TableColumn("ID");
        idCol.setMinWidth(50);
        idCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableCategory, String>("categoryId"));

        TableColumn catCol = new TableColumn("Category");
        catCol.setMinWidth(100);
        catCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableCategory, String>("category"));
        categoryTable.setItems(oblist);
        categoryTable.getColumns().addAll(idCol, catCol);
    }
}
