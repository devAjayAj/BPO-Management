/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgmnt;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ajay
 */
public class Controller implements Initializable {
    @FXML
    private Button adminb;
    @FXML
    private Button exec;
    @FXML
    private Button adminloginb;
    @FXML
    private TextField adminname;
    @FXML
    private PasswordField adminpass;
    @FXML
    private Button emploginb;
    @FXML
    private TextField empname;
    @FXML
    private PasswordField emppass;
    @FXML
    private AnchorPane AnchorPane;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void adminb()throws Exception{
        Stage stage; 
        Parent root;
        stage=(Stage) adminb.getScene().getWindow();
        stage.setTitle("Admin Login");
        root = FXMLLoader.load(getClass().getResource("FXML1admin.fxml"));
        Scene sc = new Scene(root,600,400);
        sc.getStylesheets().add(getClass().getResource("fxml1admin.css").toExternalForm());
        stage.setScene(sc);
    }
    
    public void empb()throws Exception{
        Stage stage; 
        Parent root;
        stage=(Stage) exec.getScene().getWindow();
        stage.setTitle("Executive Login");
        root = FXMLLoader.load(getClass().getResource("FXML1emp.fxml"));
        Scene sc = new Scene(root,600,400);
        sc.getStylesheets().add(getClass().getResource("fxml1emp.css").toExternalForm());
        stage.setScene(sc);
    }
    public static void centerOnScreen(Stage stage) {
        stage.centerOnScreen();
        stage.setY(stage.getY() * 3f / 2f);
    }
   
    public void adminlogin() throws Exception{
        Stage stage; 
        Parent root;
        if(adlogin(adminname.getText(),adminpass.getText())){
        stage=(Stage) adminloginb.getScene().getWindow();
        stage.setTitle("Admin");
        root = FXMLLoader.load(getClass().getResource("FXML2admin2.fxml"));
        Scene sc = new Scene(root,800,600);
        sc.getStylesheets().add(getClass().getResource("fxml2admin.css").toExternalForm());
        stage.setScene(sc);
        stage.centerOnScreen();
        }
        else{
            JOptionPane.showMessageDialog(null,"Incorrect Details!");
        }
    }
    public boolean adlogin(String user,String pass) throws SQLException{
        try{
        Class.forName("org.sqlite.JDBC");
        Connection conn;
        conn = DriverManager.getConnection("jdbc:sqlite:project.db");
        ResultSet res = null;
        PreparedStatement stat;
        String str = "select * from admin where userid = ? and pass = ?";
        stat = conn.prepareStatement(str);
        stat.setString(1,user);
        stat.setString(2,pass);
        res = stat.executeQuery();
        if(res.next()){
            res.close();
            stat.close();
            conn.close();
            return true;
        }
        else
            return false;
        
        }
        
        catch(Exception e){
         JOptionPane.showMessageDialog(null,e);
         return false;
        }
    }
    public void execlogin() throws Exception{
        Stage stage; 
        Parent root;
        if(exlogin(empname.getText(),emppass.getText())){
        stage=(Stage) emploginb.getScene().getWindow();
        stage.setTitle("Executive");
        root = FXMLLoader.load(getClass().getResource("FXML2emp.fxml"));
        Scene sc = new Scene(root,600,400);
        sc.getStylesheets().add(getClass().getResource("fxml2emp.css").toExternalForm());
        stage.setScene(sc);
        stage.centerOnScreen();
        }
        else{
            JOptionPane.showMessageDialog(null,"Incorrect Details!");
        }
    }
    public boolean exlogin(String user,String pass) throws SQLException{
        try{
        Class.forName("org.sqlite.JDBC");
        Connection conn;
        conn = DriverManager.getConnection("jdbc:sqlite:project.db");
        ResultSet res = null;
        PreparedStatement stat;
        String str = "select * from executive where userid = ? and pass = ?";
        stat = conn.prepareStatement(str);
        stat.setString(1,user);
        stat.setString(2,pass);
        res = stat.executeQuery();
        if(res.next()){
            res.close();
            stat.close();
            conn.close();
            return true;
        }
        else
            return false;
        }
        
        catch(ClassNotFoundException | SQLException e){
         JOptionPane.showMessageDialog(null,e);
         return false;
        }
    }
}

