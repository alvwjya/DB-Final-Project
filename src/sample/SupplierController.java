package sample;

import javafx.application.Platform;
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

public class SupplierController implements Initializable {

    ObservableList<ModelTableSupplier> oblist = FXCollections.observableArrayList();
    @FXML
    private TableView<ModelTableSupplier> supplierTable;

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





            ModelTableSupplier movie = supplierTable.getSelectionModel().getSelectedItem();
            String hehe = movie.getSupplierAddress();
            System.out.println(hehe);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //TESTNYA ALVIAN
    public int hehe(){
        for (int i = 0; i < supplierTable.getItems().size(); i++) {
            if (supplierTable.getItems().get(i).getSupplierId() == 3) {
                return i;
            }
        }
        return 0;
    } //STOP DISINI DULU

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        oblist.add(new ModelTableSupplier(1, "KOKO", "IJ", "ju", "beenuse"));
        oblist.add(new ModelTableSupplier(2, "KOKO", "IJ", "ju", "beenuse"));
        oblist.add(new ModelTableSupplier(3, "KOKO", "IJ", "ju", "beenuse"));


        //INI JUGA TEST ALVIAN
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                supplierTable.requestFocus();
                supplierTable.getSelectionModel().select(hehe());
                supplierTable.getFocusModel().focus(hehe());


            }
        }); //UDH SAMPE SINI



        TableColumn idCol = new TableColumn("ID");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableSupplier, Integer>("supplierId"));
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(200);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableSupplier, String>("supplierName"));
        TableColumn addressCol = new TableColumn("Address");
        addressCol.setMinWidth(250);
        addressCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableSupplier, String>("supplierAddress"));
        TableColumn cityCol = new TableColumn("City");
        cityCol.setMinWidth(150);
        cityCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableSupplier, String>("city"));
        TableColumn contactCol = new TableColumn("Contact");
        contactCol.setMinWidth(190);
        contactCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableSupplier, String>("supplierContact"));
        supplierTable.setItems(oblist);
        supplierTable.getColumns().addAll(idCol, nameCol, addressCol, cityCol, contactCol);
    }

}
