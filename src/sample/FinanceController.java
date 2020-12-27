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

    //Ini buat table "FINANCES"
    ObservableList<ModelTableFinances> oblist1 = FXCollections.observableArrayList();

    //Ini buat table "EXPENSES DETAILS"
    ObservableList<ModelTableExpensesDetails> oblist2 = FXCollections.observableArrayList();

    @FXML
    private TableView<ModelTableFinances> financesTable;

    @FXML
    private TableView<ModelTableExpensesDetails> expensesDetailsTable;





    public void showFinancesTable(){
        PreparedStatement preparedStat = connect.getPrepStat("SELECT salesDate FROM sales UNION SELECT restockDate FROM Restock;");
        try {
            ResultSet rs = preparedStat.executeQuery();
            while(rs.next()){
                calcSales(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void calcSales(String date){
        PreparedStatement prepStat = connect.getPrepStat("SELECT (SELECT COALESCE(SUM(paid),0) FROM Sales WHERE salesDate = DATE('" + date + "')), (SELECT COALESCE(SUM(restockPrice),0) FROM restock WHERE restockDate = DATE('" + date + "')); ");

        try {
            ResultSet rs = prepStat.executeQuery();
            if(rs.next()){
                oblist1.add(new ModelTableFinances(date, rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /*public void showFinancesTable(){
        try {


            PreparedStatement prepStat = connect.getPrepStat("SELECT restockDate or salesDate, SUM(Sales.paid), SUM(Restock.restockPrice) FROM Sales, Restock GROUP BY DATE(salesDate), DATE(restockDate);");
            ResultSet rs = prepStat.executeQuery();
            while (rs.next()) {
                oblist1.add(new ModelTableFinances(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public void getFinancesDetails(){
        expensesDetailsTable.getItems().clear();
        ModelTableFinances customer = financesTable.getSelectionModel().getSelectedItem();
        date = customer.getDate();
        System.out.println(date);
        showExpensesDetails();
    }

    public void showExpensesDetails(){
        try {
            PreparedStatement prepStat = connect.getPrepStat("SELECT salesDate, paid FROM Sales WHERE salesDate = DATE('" + date + "');");
            ResultSet rs = prepStat.executeQuery();
            PreparedStatement prepStatRestock = connect.getPrepStat("SELECT restockDate, restockPrice FROM Restock WHERE restockDate = DATE('" + date + "');");
            ResultSet rsRestock = prepStatRestock.executeQuery();
            while (rs.next()) {
                oblist2.add(new ModelTableExpensesDetails(rs.getString(1), "Sales Income", rs.getString(2)));
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
