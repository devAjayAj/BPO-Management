/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgmnt;

import java.awt.HeadlessException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ajay
 */
public class EmpexistingcustController implements Initializable {
    @FXML
    private TextField custid;
    @FXML
    private Pane pane;
    @FXML
    private ObservableList<ObservableList> data;
    @FXML
    private TableView tableview;
    @FXML
    private TextField complainttitle;
    @FXML 
    private TextField complaintcomment;
    @FXML 
    private TextField cid;
    @FXML
    private ComboBox combobox;
    @FXML
    private TextArea disconnectreason;
    String a;
    static String c;
    int rand;
    @FXML
    public Button newexistback;
    @FXML
    public Button backb;
    @FXML
    public Button details;
    @FXML
    public Button planb;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void next(){
        try{
        Class.forName("org.sqlite.JDBC");
        Connection conn;
        conn = DriverManager.getConnection("jdbc:sqlite:project.db");
        ResultSet res;
        a = custid.getText();
        c = a;
        Statement stat = conn.createStatement();
        String str = "SELECT custid from customer where custid = '"+c+"'";
        res = stat.executeQuery(str);
        if(res.next()){
        URL url = getClass().getResource("empexistingcust11.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Pane pan = (Pane) fxmlLoader.load(url.openStream()); 
        pan.getStylesheets().add(getClass().getResource("empexistingcust1.css").toString());
        pane.getChildren().clear();
        pane.getChildren().add(pan);
        
        }
        else{
            JOptionPane.showMessageDialog(null,"Incorrect ID");
        }
        res.close();
        stat.close();
        conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void back() throws Exception{
        URL url = getClass().getResource("empexistingcust11.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Pane pan = (Pane) fxmlLoader.load(url.openStream()); 
        pan.getStylesheets().add(getClass().getResource("empexistingcust1.css").toString());
        pane.getChildren().clear();
        pane.getChildren().add(pan);
    }
    public void custdetails() throws Exception{
        details.setDisable(true);
        data = FXCollections.observableArrayList();
        try{
        Class.forName("org.sqlite.JDBC");
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:sqlite:project.db");
        ResultSet res = null;
        Statement stat = null;
        stat = conn.createStatement();
        String query = "SELECT * from customer where custid = '"+c+"'";
        res = stat.executeQuery(query);
        String b = res.getMetaData().toString();
        for(int i=0 ; i<(res.getMetaData().getColumnCount()-1); i++){
                //We are using non property style for making dynamic table
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
    public void plans() throws Exception{
        planb.setDisable(true);
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
    public int random() {
    Random r = new Random( System.currentTimeMillis() );
    return 10000 + r.nextInt(20000);
    }
     
    public void regcomplaint() throws Exception{
        URL url = getClass().getResource("empexistingcust22.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Pane pan = (Pane) fxmlLoader.load(url.openStream());
        pan.getStylesheets().add(getClass().getResource("empexistingcust22.css").toString());
        pane.getChildren().clear();
        pane.getChildren().add(pan);
    }
    public void complaintsubmit() throws Exception{
        rand = random();
        try{
        Class.forName("org.sqlite.JDBC");
        Connection conn;
        conn = DriverManager.getConnection("jdbc:sqlite:project.db");
        ResultSet res;
        Statement stat = conn.createStatement();
                String d = complainttitle.getText();
                String e = complaintcomment.getText();
                String str = "INSERT INTO complaint(custid,title,comment,complaintnumber)" + "values ('"+c+"','"+d+"','"+e+"','"+rand+"')";
                int i = stat.executeUpdate(str);
                stat.close();
                conn.close();
                if(i!=0){
                    JOptionPane.showMessageDialog(null,"Complaint Submitted and ID:" + rand);
                }
            
        }
        catch(Exception e){
         complaintsubmit();
         JOptionPane.showMessageDialog(null,e);
        }
        }
    public void reqplanchange() throws Exception{
        URL url = getClass().getResource("empexistingcust3.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Pane pan = (Pane) fxmlLoader.load(url.openStream()); 
        pan.getStylesheets().add(getClass().getResource("empexistingcust3.css").toString());
        pane.getChildren().clear();
        pane.getChildren().add(pan);
    }
    public void addplan() throws Exception{
        try{
        Class.forName("org.sqlite.JDBC");
        Connection conn;
        conn = DriverManager.getConnection("jdbc:sqlite:project.db");
        ResultSet res;
        Statement stat = conn.createStatement();
                String v = combobox.getValue().toString();
                String str = "UPDATE customer SET custplan ='"+v+"' where custid = '"+c+"'";
                int i = stat.executeUpdate(str);
                stat.close();
                conn.close();
                if(i!=0){
                    JOptionPane.showMessageDialog(null,"Plan Changed");
                }
            
        }
        catch(Exception e){
         JOptionPane.showMessageDialog(null,e);
         }
    }
    public void complaintstatus() throws Exception{
        URL url = getClass().getResource("empexistingcust4.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Pane pan = (Pane) fxmlLoader.load(url.openStream()); 
        pan.getStylesheets().add(getClass().getResource("empexistingcust4.css").toString());
        pane.getChildren().clear();
        pane.getChildren().add(pan);
    }
    public void checkstatus() throws Exception{
        data = FXCollections.observableArrayList();
        String id = cid.getText();
        try{
        Class.forName("org.sqlite.JDBC");
        Connection conn;
        conn = DriverManager.getConnection("jdbc:sqlite:project.db");
        ResultSet res;
        Statement stat;
        stat = conn.createStatement();
        String query = "SELECT custid,title,comment,Status from complaint where complaintnumber = '"+id+"'";
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
    public void reqdisconnect() throws Exception{
        URL url = getClass().getResource("empexistingcust5.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Pane pan = (Pane) fxmlLoader.load(url.openStream()); 
        pan.getStylesheets().add(getClass().getResource("empexistingcust5.css").toString());
        pane.getChildren().clear();
        pane.getChildren().add(pan);
    }
    public void disconnect() throws Exception{
        try{
        Class.forName("org.sqlite.JDBC");
        Connection conn;
        conn = DriverManager.getConnection("jdbc:sqlite:project.db");
        Statement stat = conn.createStatement();
                String d = disconnectreason.getText();
                String str = "INSERT INTO Disconnect(custid,reason)" + "values ('"+c+"','"+d+"')";
                int i = stat.executeUpdate(str);
                if(i!=0){
                    JOptionPane.showMessageDialog(null,"Disconnect Reqest Placed");
                }
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
        stage=(Stage) newexistback.getScene().getWindow();
        stage.setTitle("Executive");
        root = FXMLLoader.load(getClass().getResource("FXML2emp.fxml"));
        Scene sc = new Scene(root,600,400);
        sc.getStylesheets().add(getClass().getResource("fxml2emp.css").toExternalForm());
        stage.setScene(sc);
        stage.centerOnScreen();
    }
    public void newexistscreen1() throws Exception{
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

