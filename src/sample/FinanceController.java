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
        // add query buat isi table "FINANCES"
        try {
            PreparedStatement prepStat = connect.getPrepStat("SELECT salesDate, sum(paid), sum(restockPrice) FROM Sales, Restock WHERE Sales.salesDate = Restock.restockDate GROUP BY salesDate ORDER BY salesDate;");
            ResultSet rs = prepStat.executeQuery();

            while (rs.next()) {
                oblist1.add(new ModelTableFinances(rs.getString("salesDate"), rs.getString("sum(paid)"), rs.getString("sum(restockPrice)")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getFinancesDetails(){
        ModelTableFinances customer = financesTable.getSelectionModel().getSelectedItem();
        date = customer.getDate();
    }

    public void showExpensesDetails(){
        // add query buat isi table "EXPENSES DETAILS" berdasarkan date yg udh di pilih dari "FINANCES" table
        try {
            PreparedStatement prepStat = connect.getPrepStat("SELECT salesDate, paid FROM Sales WHERE salesDate = " + date + ";");
            ResultSet rs = prepStat.executeQuery();
            PreparedStatement prepStatRestock = connect.getPrepStat("SELECT restockDate, restockPrice FROM Restock WHERE restockDate = " + date + ";");
            ResultSet rsRestock = prepStatRestock.executeQuery();

            while (rs.next()) {
                oblist2.add(new ModelTableExpensesDetails(rs.getString("salesDate"), "Sales Income", rs.getString("paid")));
            }

            while (rsRestock.next()) {
                oblist2.add(new ModelTableExpensesDetails(rsRestock.getString("restockDate"), "Restock Expenses", rsRestock.getString("restockPrice")));
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
