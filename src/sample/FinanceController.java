package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class FinanceController implements Initializable {

    ObservableList<ModelTableFinances> oblist1 = FXCollections.observableArrayList();
    ObservableList<ModelTableExpensesDetails> oblist2 = FXCollections.observableArrayList();
    @FXML
    private TableView<ModelTableFinances> financesTable;

    @FXML
    private TableView<ModelTableExpensesDetails> expensesDetailsTable;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn dateCol = new TableColumn("Date");
        dateCol.setMinWidth(130);
        dateCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableFinances, String>("date"));
        TableColumn incomeCol = new TableColumn("Income");
        incomeCol.setMinWidth(150);
        incomeCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableFinances, String>("income"));
        TableColumn expensesCol = new TableColumn("Expenses");
        expensesCol.setMinWidth(150);
        expensesCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableFinances, String>("expenses"));
        financesTable.getColumns().addAll(dateCol, incomeCol, expensesCol);

        TableColumn dateCol2 = new TableColumn("Date");
        dateCol2.setMinWidth(130);
        dateCol2.setCellValueFactory(
                new PropertyValueFactory<ModelTableExpensesDetails, String>("date"));
        TableColumn detailCol = new TableColumn("Detail");
        detailCol.setMinWidth(150);
        detailCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableExpensesDetails, String>("detail"));
        TableColumn amountCol = new TableColumn("Amount");
        amountCol.setMinWidth(150);
        amountCol.setCellValueFactory(
                new PropertyValueFactory<ModelTableExpensesDetails, String>("expenses"));
        expensesDetailsTable.getColumns().addAll(dateCol2, detailCol, amountCol);

    }
}
