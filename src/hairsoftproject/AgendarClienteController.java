/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hairsoftproject;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author guist
 */
public class AgendarClienteController implements Initializable {

    @FXML
    private ComboBox<String> cpfTxt;
    @FXML
    private TextField nomeTxt;
    @FXML
    private Button btnAgen;
    @FXML
    private TextArea descTxt;
    @FXML
    private TextField txtCliente;
    @FXML
    private DatePicker dataAgen;
    @FXML
    private TextField precoTxt;
    
    private ObservableList<String> dataCpf;
    private ObservableList<String> checkBoxServ;
    Connection MyConn = hairsoftproject.DAO.ConexaoMySql.getConexaoMySql();

    public void preencheCmbCpf(){
        cpfTxt.getItems().removeAll(cpfTxt.getItems());
        dataCpf = FXCollections.observableArrayList();
        try {
            Statement Stmt = MyConn.createStatement();
            ResultSet myRs = Stmt.executeQuery("SELECT CPF"
                    + "                           FROM CLIENTE"
                    + "                          WHERE IFNULL(ATIVO, 'SIM') = 'SIM'");
            while (myRs.next()){
                
                dataCpf.add(myRs.getString("CPF"));
             
            }
            cpfTxt.setItems(dataCpf);
        } catch (SQLException ex) {
            Logger.getLogger(AgendarClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void preencheNome(){
        String cpfNome = cpfTxt.getValue();
        try {
            Statement Stmt = MyConn.createStatement();
            ResultSet myRs = Stmt.executeQuery("SELECT CLIENTE_ID, NOME"
                    + "                           FROM CLIENTE"
                    + "                          WHERE IFNULL(ATIVO, 'SIM') = 'SIM'"
                    + "                            AND CPF = '"+cpfNome+"'");
            while (myRs.next()){
                
                String nome = myRs.getString("NOME");
                nomeTxt.setText(nome);
                String cliente = myRs.getString("CLIENTE_ID");
                txtCliente.setText(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgendarClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeTxt.setDisable(true);
        txtCliente.setDisable(true);
        preencheCmbCpf();
        precoTxt.textProperty().addListener((ObservableValue<? extends String> observable, String antigo, String novo) -> {
            if (!novo.matches("\\d*")) {
                precoTxt.setText(novo.replaceAll("[^\\d]", ""));
            }
        });
    }
    
    public void verificaCampos(){
        if (cpfTxt.getValue()== null){
            JOptionPane.showMessageDialog(null, "Favor selecionar um CPF!","Aviso",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (nomeTxt.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Nome não cadastrado na base de dados, contate o suporte!","Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }        
        if (descTxt.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Favor preencher o campo Serviços!","Aviso",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (txtCliente.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Erro no ID do cliente, favor entrar em contato com o suporte!","Aviso",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (dataAgen.getValue()== null){
            JOptionPane.showMessageDialog(null, "Favor preencher o campo Data de agendamento!","Aviso",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (precoTxt.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Favor preencher o campo Preço!","Aviso",JOptionPane.WARNING_MESSAGE);
            return;
        }
    }

    public void agendarBtn() {
        
        verificaCampos();
        
        java.util.Date data = 
        java.util.Date.from(dataAgen.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date dataSql = new java.sql.Date(data.getTime());
        try{                 
        PreparedStatement stmt = MyConn.prepareStatement
                ("INSERT INTO AGENDAMENTO "
               + "(SERVICO_DES, CLIENTE_ID, DATA_AGENDAMENTO, DATA_CRIACAO, PRECO, ATIVO)"
                + "VALUES(?,?,?,SYSDATE(),?,'SIM')");
        
        stmt.setString(1, descTxt.getText());
        stmt.setString(2, txtCliente.getText());
        stmt.setDate(3, dataSql);
        stmt.setString(4, precoTxt.getText());
          
        stmt.executeUpdate();  
        JOptionPane.showMessageDialog(null, "Agendamento efetuado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        stmt.close();        
        
        nomeTxt.clear();
        descTxt.clear();
        txtCliente.clear();
        dataAgen.setValue(null);
        precoTxt.clear();
        
        }catch (SQLException e){e.printStackTrace(); 
            JOptionPane.showMessageDialog(null, "Falha ao efetuar o cadastro do cliente! Contate o suporte.","Aviso",JOptionPane.WARNING_MESSAGE);
        }
    }
}
