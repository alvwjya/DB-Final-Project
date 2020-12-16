package sample;

import javafx.application.Platform;
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
import java.util.ResourceBundle;

public class InventoryEditController implements Initializable {
    public TextField productNameField, priceField;
    public String selectedCategory;
    public int productId;

    ObservableList<ModelTableCategory> oblist = FXCollections.observableArrayList();
    @FXML
    private TableView<ModelTableCategory> categoryTable;

    public void getCategory(){
        ModelTableCategory category = categoryTable.getSelectionModel().getSelectedItem();
        selectedCategory = String.valueOf(category.getCategoryId());
    }

    public void setProductId(Integer productId){
        this.productId = productId;
    }

    public void saveButton(){
        System.out.println("THIS IS AFTER " + productId); //Check Only
        if (productNameField.getText().isEmpty() || priceField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Something Wrong!");
            alert.setHeaderText("Check your input");
            alert.setContentText("Make sure you fill all field with correct value");
            alert.show();
        }
        else{
            // add query here to edit customer, customernya bisa diambil dari variable customerId, trus yg bisa diganti nama, address, contact, city

            getCategory();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setContentText("Save Successful!");
            alert.setHeaderText("SAVED");
            alert.show();

        }

        // This is for test only
        System.out.println(priceField.getText());
        System.out.println(productNameField.getText());
        System.out.println(selectedCategory);
    }

    public void showTable(){
        // add code + query here to fill the table with categoryId, category.

    }

    public int getCategoryIndex(){
        for (int i = 0; i < categoryTable.getItems().size(); i++) {
            if (categoryTable.getItems().get(i).getCategoryId() == 1) { // <- ini '==' nya diliat dari database, bikin query yg return categoryId
                return i;
            }
        }
        return 0;
    }

    public void preselectCategory(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                categoryTable.requestFocus();
                categoryTable.getSelectionModel().select(getCategoryIndex());
                categoryTable.getFocusModel().focus(getCategoryIndex());
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showTable();
        preselectCategory();

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
