/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgmnt;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ajay
 */
public class EmpnewcustController implements Initializable {
    @FXML
    private TextField custname;
    @FXML
    private TextField custphno;
    @FXML
    private TextField custmail;
    @FXML
    private TableView tableview;
    @FXML
    private ObservableList<ObservableList> data;
    @FXML
    public Button backb;
    @FXML
    public Button plan;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void addcust() throws Exception{
        try{
        Class.forName("org.sqlite.JDBC");
        Connection conn;
        conn = DriverManager.getConnection("jdbc:sqlite:project.db");
        Statement stat = conn.createStatement();
        ResultSet res;
                String a = custname.getText();
                String b = custphno.getText();
                String c = custmail.getText();
                String str = "INSERT INTO newcustomer(name,phno,email)" + "values ('"+a+"','"+b+"','"+c+"')";
                int i = stat.executeUpdate(str);
                
                if(i != 0){
                    JOptionPane.showMessageDialog(null,"Added");
                }
                stat.close();
                conn.close();
        }
        catch(Exception e){
         JOptionPane.showMessageDialog(null,e);
         }
        }
    public void plans() throws Exception{
        plan.setDisable(true);
        data = FXCollections.observableArrayList();
        try{
        Class.forName("org.sqlite.JDBC");
        Connection conn;
        conn = DriverManager.getConnection("jdbc:sqlite:project.db");
        ResultSet res;
        Statement stat;
        stat = conn.createStatement();
        String query = "SELECT * from plans";
        res = stat.executeQuery(query);
        for(int i=0 ; i<res.getMetaData().getColumnCount(); i++){
                final int j = i;                
                TableColumn col = new TableColumn(res.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });

                tableview.getColumns().addAll(col); 
            }
        while(res.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=res.getMetaData().getColumnCount(); i++){
                    row.add(res.getString(i));
                }
                data.add(row);
                tableview.setItems(data);
            }
            res.close();
            stat.close();
            conn.close();


        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
        }
    }
    public void newexistscreen() throws Exception{
        Stage stage; 
        Parent root;
        stage=(Stage) backb.getScene().getWindow();
        stage.setTitle("Executive");
        root = FXMLLoader.load(getClass().getResource("FXML2emp.fxml"));
        Scene sc = new Scene(root,600,400);
        sc.getStylesheets().add(getClass().getResource("fxml2emp.css").toExternalForm());
        stage.setScene(sc);
        stage.centerOnScreen();
    }
}
