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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ajay
 */
public class AdminaddempController implements Initializable {

@FXML
private TextField empname;
@FXML
private TextField empid;
@FXML
private TextField empdes;
@FXML
private TextField empgen;
@FXML
private TextField empaddrss;
@FXML
private TextField empmail;
@FXML
private TextField empphno;
@FXML
private PasswordField emppass;
@FXML
private AnchorPane anchorpane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void addempdetails() throws Exception{
        try{
        Class.forName("org.sqlite.JDBC");
        Connection conn;
        conn = DriverManager.getConnection("jdbc:sqlite:project.db");
        Statement stat = conn.createStatement();
                String a = empname.getText();
                String b = empid.getText();
                String c = empdes.getText(); 
                String d = empgen.getText();
                String e = empaddrss.getText();
                String f = empmail.getText();
                String g = empphno.getText();
                String h = emppass.getText();
                String str = "INSERT INTO executive(name,userid,designation,gender,address,email,phonenumber,pass)" + "values ('"+a+"','"+b+"','"+c+"','"+d+"','"+e+"','"+f+"','"+g+"','"+h+"')";
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
    

