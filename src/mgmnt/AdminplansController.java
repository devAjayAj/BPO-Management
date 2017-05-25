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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author Ajay
 */
public class AdminplansController implements Initializable {
    @FXML
    private ObservableList<ObservableList> data;
    @FXML
    private TableView tableview;
    @FXML
    private Button planb;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableview.setPlaceholder(new Text(""));
    }    
    public void buildData(){
        planb.setDisable(true);
        data = FXCollections.observableArrayList();
        try{
        Class.forName("org.sqlite.JDBC");
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:sqlite:project.db");
        ResultSet res = null;
        Statement stat = null;
        stat = conn.createStatement();
        String query = "SELECT * from plans";
        res = stat.executeQuery(query);
        for(int i=0 ; i<res.getMetaData().getColumnCount(); i++){
                final int j = i;                
                TableColumn col = new TableColumn(res.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
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
}
