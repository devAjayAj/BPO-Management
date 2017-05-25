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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ajay
 */
public class AdminaddcustomerController implements Initializable {

    @FXML
    private TextField custname;
    @FXML
    private TextField custid;
    @FXML
    private TextField custstate;
    @FXML
    private TextField custcity;
    @FXML
    private TextField custaddress;
    @FXML
    private TextField custmail;
    @FXML
    private TextField custphno;
    @FXML
    private ComboBox custplan;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void addcustdetails(){
        try{
        Class.forName("org.sqlite.JDBC");
        Connection conn;
        conn = DriverManager.getConnection("jdbc:sqlite:project.db");
        ResultSet res;
        Statement stat = conn.createStatement();
                String a = custname.getText();
                String b = custid.getText();
                String c = custstate.getText(); 
                String d = custcity.getText();
                String e = custaddress.getText();
                String f = custmail.getText();
                String g = custphno.getText();
                String h = custplan.getValue().toString();
                String str = "INSERT INTO customer(custname,custid,custstate,custcity,custaddress,custmail,custphno,custplan)" + "values ('"+a+"','"+b+"','"+c+"','"+d+"','"+e+"','"+f+"','"+g+"','"+h+"')";
                int i = stat.executeUpdate(str);
                
                if(i!=0){
                    JOptionPane.showMessageDialog(null,"Added");
                }
                stat.close();
                conn.close();
            
        }
        catch(ClassNotFoundException | SQLException | HeadlessException e){
         JOptionPane.showMessageDialog(null,e);
         }
        }
}
