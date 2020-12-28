package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FinanceController implements Initializable {

    public String date;
    Connection connect = new Connection();


    ObservableList<ModelTableFinances> oblist1 = FXCollections.observableArrayList();
    ObservableList<ModelTableExpensesDetails> oblist2 = FXCollections.observableArrayList();

    @FXML
    private TableView<ModelTableFinances> financesTable;

    @FXML
    private TableView<ModelTableExpensesDetails> expensesDetailsTable;


    public void showFinancesTable() {
        PreparedStatement preparedStat = connect.getPrepStat("SELECT salesDate FROM sales UNION SELECT restockDate FROM Restock;");
        try {
            ResultSet rs = preparedStat.executeQuery();
            while (rs.next()) {
                PreparedStatement prepStat = connect.getPrepStat(
                        "SELECT (SELECT COALESCE(SUM(paid),0) " +
                        "FROM Sales WHERE salesDate = DATE('" + rs.getString(1) + "')), " +
                        "(SELECT COALESCE(SUM(restockPrice),0) " +
                        "FROM restock WHERE restockDate = DATE('" + rs.getString(1) + "')); ");
                    ResultSet rsD = prepStat.executeQuery();
                    if (rsD.next()) {
                        oblist1.add(new ModelTableFinances(rs.getString(1), rsD.getString(1), rsD.getString(2)));
                    }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void refreshButton(){
        date = null;
        financesTable.getItems().clear();
        showFinancesTable();
        expensesDetailsTable.getItems().clear();
        showExpensesDetails();
    }


    public void getFinancesDetails() {
        expensesDetailsTable.getItems().clear();
        ModelTableFinances customer = financesTable.getSelectionModel().getSelectedItem();
        date = customer.getDate();
        showExpensesDetails();
    }


    public void showExpensesDetails() {
        try {
            PreparedStatement prepStat = connect.getPrepStat("SELECT salesDate, paid FROM " +
                    "Sales WHERE salesDate = DATE('" + date + "');");
            PreparedStatement prepStatRestock = connect.getPrepStat("SELECT restockDate, restockPrice FROM " +
                    "Restock WHERE restockDate = DATE('" + date + "');");
            ResultSet rsIncome = prepStat.executeQuery();
            ResultSet rsRestock = prepStatRestock.executeQuery();
            while (rsIncome.next()) {
                oblist2.add(new ModelTableExpensesDetails(rsIncome.getString(1), "Sales Income", rsIncome.getString(2)));
            }
            while (rsRestock.next()) {
                oblist2.add(new ModelTableExpensesDetails(rsRestock.getString(1), "Restock Expenses", rsRestock.getString(2)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showFinancesTable();

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
        financesTable.setItems(oblist1);
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
        expensesDetailsTable.setItems(oblist2);
        expensesDetailsTable.getColumns().addAll(dateCol2, detailCol, amountCol);
    }
}
