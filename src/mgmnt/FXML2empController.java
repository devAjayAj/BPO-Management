/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgmnt;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ajay
 */
public class FXML2empController implements Initializable {

    @FXML
    private Button newcustb;
    @FXML
    private Button existcustb;
    @FXML
    public Button logb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void newcust() throws Exception{
        try{
        Stage stage; 
        Parent root;
        stage=(Stage) newcustb.getScene().getWindow();
        stage.setTitle("New Customer");
        root = FXMLLoader.load(getClass().getResource("empnewcust.fxml"));
        Scene sc = new Scene(root,600,400);
        sc.getStylesheets().add(getClass().getResource("empnewcust.css").toExternalForm());
        stage.setScene(sc);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void existcust() throws Exception{
        try{
        Stage stage; 
        Parent root;
        stage=(Stage) existcustb.getScene().getWindow();
        stage.setTitle("Existing Customer");
        root = FXMLLoader.load(getClass().getResource("empexistingcust.fxml"));
        Scene sc = new Scene(root,600,400);
        sc.getStylesheets().add(getClass().getResource("empexistingcust.css").toExternalForm());
        stage.setScene(sc);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void logout() throws Exception{
        Stage stage; 
        Parent root;
        stage=(Stage) logb.getScene().getWindow();
        stage.setTitle("WELCOME");
        root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene sc = new Scene(root,600,400);
        sc.getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());
        stage.setScene(sc);
        stage.centerOnScreen();
    }
}
