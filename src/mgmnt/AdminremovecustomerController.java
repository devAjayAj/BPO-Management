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
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ajay
 */
public class AdminremovecustomerController implements Initializable {
    @FXML
    private TextField custid;
    @FXML
    private Button custremb;
    @FXML
    private Button showb;
    @FXML
    private ObservableList<ObservableList> data;
    @FXML
    private TableView table;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table.setPlaceholder(new Text(""));
    }    
    public void remove() throws Exception{
        
        try{
        Class.forName("org.sqlite.JDBC");
        Connection conn;
        conn = DriverManager.getConnection("jdbc:sqlite:project.db");
        ResultSet res;
        Statement stat = conn.createStatement();
        String a = custid.getText();
        String str = "DELETE from customer where custid = '"+a+"'";
                int z = stat.executeUpdate(str);
                
                if(z!=0){
                    JOptionPane.showMessageDialog(null,"Customer Removed");
                    table.getColumns().clear();
                    data = FXCollections.observableArrayList();
        try{
        String query = "SELECT custid,custname,custmail from customer";
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

                table.getColumns().addAll(col); 
            }
        while(res.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=res.getMetaData().getColumnCount(); i++){
                    row.add(res.getString(i));
                }
                data.add(row);
                table.setItems(data);
            }
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
        }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Customer Doesn't Exist or Incorrect Details");
                }
                stat.close();
                conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void buildData(){
        showb.setDisable(true);
        data = FXCollections.observableArrayList();
        try{
        Class.forName("org.sqlite.JDBC");
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:sqlite:project.db");
        ResultSet res = null;
        Statement stat = null;
        stat = conn.createStatement();
        String query = "SELECT custid,custname,custmail from customer";
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

                table.getColumns().addAll(col); 
            }
        while(res.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=res.getMetaData().getColumnCount(); i++){
                    row.add(res.getString(i));
                }
                data.add(row);
                table.setItems(data);
            }
            res.close();
            stat.close();
            conn.close();
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
        }
}
}
