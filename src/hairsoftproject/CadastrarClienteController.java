/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hairsoftproject;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Eduardo Dias
 */
public class CadastrarClienteController implements Initializable {

    Connection connection = hairsoftproject.DAO.ConexaoMySql.getConexaoMySql();
    
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnFechar;
    @FXML
    private Button btnPesquisar;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtCPF;
    @FXML
    private TextField txtRG;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEndereco;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtCEP;
    @FXML
    private TextField txtCompl;
    @FXML
    private TextField txtBairro;
    @FXML
    private TextField txtCidade;
    @FXML
    private ComboBox cboEstado;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtEmail;
    @FXML
    private CheckBox chkAtivo;
    @FXML
    private Pane pai;
    @FXML
    //private AnchorPane mestre;
    
    public void carregaCBO(){
        
    ObservableList<String> list = FXCollections.observableArrayList("RO","AC","AM","RR","PA","AP","TO","MA","PI","CE","RN","PB","PE","AL","SE","BA","MG","ES","RJ","SP","PR","SC","RS","MS","MT","GO","DF");
    cboEstado.setItems(list);
     
      
    }
    
    public void desabilitaTela(){
        txtCodigo.setDisable(true);
        txtCPF.setDisable(false);
        txtRG.setDisable(true);
        txtNome.setDisable(true);
        txtEndereco.setDisable(true);
        txtNumero.setDisable(true);
        txtCEP.setDisable(true);
        txtCompl.setDisable(true);
        txtBairro.setDisable(true);
        txtCidade.setDisable(true);
        cboEstado.setDisable(true);
        txtTelefone.setDisable(true);
        txtEmail.setDisable(true);
        chkAtivo.setDisable(true);
        btnAlterar.setDisable(true);
        btnSalvar.setDisable(true);
        btnAlterar.setDisable(true);
        btnCancelar.setDisable(true);    
    } 
    
    public void habilitaCampos() throws SQLException{
        
        txtCodigo.setDisable(true);
        txtCPF.setDisable(false);
        txtRG.setDisable(false);
        txtNome.setDisable(false);
        txtEndereco.setDisable(false);
        txtNumero.setDisable(false);
        txtCompl.setDisable(false);
        txtBairro.setDisable(false);
        txtCEP.setDisable(false);
        txtCidade.setDisable(false);
        cboEstado.setDisable(false);
        txtTelefone.setDisable(false);
        txtEmail.setDisable(false);
        chkAtivo.setDisable(false);
        btnAlterar.setDisable(false);
        btnSalvar.setDisable(false);
        btnAlterar.setDisable(true);
        btnCancelar.setDisable(false);
        btnNovo.setDisable(true);
    }
    
    public void cancelaCadastro(){
        
        txtCodigo.setText("");
        txtCPF.setText("");
        txtRG.setText("");
        txtNome.setText("");
        txtEndereco.setText("");
        txtNumero.setText("");
        txtCompl.setText("");
        txtBairro.setText("");
        txtCEP.setText("");
        txtCidade.setText("");
        //cboEstado.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        chkAtivo.setSelected(false);
        btnNovo.setDisable(false);
        btnCancelar.setDisable(true);
        txtCodigo.setDisable(true);
        txtCPF.setDisable(false);
        txtRG.setDisable(true);
        txtNome.setDisable(true);
        txtEndereco.setDisable(true);
        txtCEP.setDisable(true);
        txtNumero.setDisable(true);
        txtCompl.setDisable(true);
        txtBairro.setDisable(true);
        txtCidade.setDisable(true);
        cboEstado.setDisable(true);
        txtTelefone.setDisable(true);
        txtEmail.setDisable(true);
        chkAtivo.setDisable(true);
        btnAlterar.setDisable(true);
        btnSalvar.setDisable(true);
        btnAlterar.setDisable(true);
    }

    public void salvaDados() throws SQLException{ 
        
        try{
        String sql = "SELECT MAX(CLIENTE_ID) AS ULTIMOID FROM CLIENTE";
        PreparedStatement prepare = (PreparedStatement) connection.prepareStatement(sql);
        ResultSet resultado = prepare.executeQuery();
        resultado.next();
        int CLIENTE_ID = resultado.getInt("ULTIMOID");
        txtCodigo.setText(Integer.toString(CLIENTE_ID +1));
        
        resultado.close();
	prepare.close();
        
        }catch(SQLException e){e.printStackTrace();JOptionPane.showMessageDialog(null,"Aviso","Falha ao recuperar código! Contate o suporte.",JOptionPane.WARNING_MESSAGE);}
        finally{
        }
        
        if (txtNome.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Favor preencher o campo Nome!","Aviso",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (txtCPF.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Favor preencher o campo CPF!","Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }        
        if (txtCEP.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Favor preencher o campo CEP!","Aviso",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (txtTelefone.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Favor preencher o campo Telefone!","Aviso",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try{                 
        PreparedStatement prepare = connection.prepareStatement
                ("INSERT INTO CLIENTE (CLIENTE_ID, NOME, "
                + "CPF, TELEFONE, RUA, CIDADE, ESTADO, NUMERO"
                + ", BAIRRO, CEP, RG, EMAIL, ATIVO)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        prepare.setString(1, txtCodigo.getText());
        prepare.setString(2, txtNome.getText());
        prepare.setString(3, txtCPF.getText());
        prepare.setString(4, txtTelefone.getText());
        prepare.setString(5, txtEndereco.getText());
        prepare.setString(6, txtCidade.getText());
        prepare.setString(7, (String) cboEstado.getValue());  
        if (txtNumero.getText().isEmpty()){
            prepare.setNull(8, Types.INTEGER);
        }
            else{
                prepare.setInt(8, Integer.parseInt(txtNumero.getText()));
            }        
        prepare.setString(9, txtBairro.getText());
        prepare.setString(10, txtCEP.getText());
        prepare.setString(11, txtRG.getText());
        prepare.setString(12, txtEmail.getText());
        if (chkAtivo.isSelected()){
            prepare.setString(13, "SIM");
        }
            else
                prepare.setString(13, "NAO");        
        
        prepare.executeUpdate();        
        
        JOptionPane.showMessageDialog(null, "Cliente Cadastrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        cancelaCadastro();
        prepare.close();        
        
        }catch(SQLException e){e.printStackTrace(); JOptionPane.showMessageDialog(null, "Falha ao efetuar o cadastro do cliente! Contate o suporte.","Aviso",JOptionPane.WARNING_MESSAGE);}
        finally{
        }
        
    }
    
    @FXML
    public void btnClickNovo(ActionEvent Event) throws SQLException{
        habilitaCampos();
    }
    
    @FXML
    public void btnSalvarClick(ActionEvent Event) throws SQLException{
        salvaDados();
    }
    
    @FXML
    public void btnFecharClick(ActionEvent Event){
        ((Node)Event.getSource()).getScene().getWindow().hide();           
    }
    
    @FXML
    public void btnClickCancela(ActionEvent Event){
        cancelaCadastro();
    }
    
       
    @FXML
    public void btnClickPesquisa(ActionEvent Event) throws IOException{
        
        txtCodigo.setText("");
                
        if (txtCPF.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Digite o CPF para pesquisar!","Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try{                 
        String sql = "SELECT CPF FROM CLIENTE WHERE CPF =" +txtCPF.getText();
        PreparedStatement prepare = connection.prepareStatement(sql);
        ResultSet consulta = prepare.executeQuery();
        if(consulta.next()){
            if (txtCPF.getText().equals(consulta.getString("CPF"))){
                JOptionPane.showMessageDialog(null, "CPF Localizado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            
                String sql2 = "SELECT * FROM CLIENTE WHERE CPF = " +txtCPF.getText();
                PreparedStatement prepare2 = connection.prepareStatement(sql2);
                ResultSet resultado = prepare2.executeQuery();
                resultado.next();
                    txtCodigo.setText(resultado.getString(1));
                    txtNome.setText(resultado.getString(2));
                    txtCPF.setText(resultado.getString(3));
                    txtTelefone.setText(resultado.getString(4));
                    txtEndereco.setText(resultado.getString(5));
                    txtCidade.setText(resultado.getString(6));
                    cboEstado.setValue(resultado.getString(7));  
                    txtNumero.setText(resultado.getString(8));                 
                    txtBairro.setText(resultado.getString(9));
                    txtCEP.setText(resultado.getString(10));
                    txtRG.setText(resultado.getString(11));
                    txtEmail.setText(resultado.getString(12));

                    habilitaCampos();
                
            btnSalvar.setDisable(true);
            btnAlterar.setDisable(false);
        
            resultado.close();
            prepare2.close();
        }else
        consulta.close();
        prepare.close();   
        }else{
            JOptionPane.showMessageDialog(null, "CPF Não Cadastrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
        
        }catch(SQLException e){e.printStackTrace(); JOptionPane.showMessageDialog(null, "Falha ao localizar o cadastro do cliente! Contate o suporte.","Aviso",JOptionPane.WARNING_MESSAGE);}
        finally{
        }
        
    }
    
    public void btnClickAltera(ActionEvent Event) throws IOException{
        
        if (txtNome.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Favor preencher o campo Nome!","Aviso",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (txtCPF.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Favor preencher o campo CPF!","Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }        
        if (txtCEP.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Favor preencher o campo CEP!","Aviso",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (txtTelefone.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Favor preencher o campo Telefone!","Aviso",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try{                 
        PreparedStatement prepare = connection.prepareStatement
                ("UPDATE CLIENTE SET NOME = ?, "
                + "CPF = ?, TELEFONE = ?, RUA = ?, CIDADE = ?, ESTADO = ?, NUMERO = ?"
                + ", BAIRRO = ?, CEP = ?, RG = ?, EMAIL = ?, ATIVO = ? WHERE CLIENTE_ID = ?");
        
        prepare.setString(1, txtNome.getText());
        prepare.setString(2, txtCPF.getText());
        prepare.setString(3, txtTelefone.getText());
        prepare.setString(4, txtEndereco.getText());
        prepare.setString(5, txtCidade.getText());
        prepare.setString(6, (String) cboEstado.getValue());  
        if (txtNumero.getText().isEmpty()){
            prepare.setNull(7, Types.INTEGER);
        }
            else{
                prepare.setInt(7, Integer.parseInt(txtNumero.getText()));
            }        
        prepare.setString(8, txtBairro.getText());
        prepare.setString(9, txtCEP.getText());
        prepare.setString(10, txtRG.getText());
        prepare.setString(11, txtEmail.getText());
        if (chkAtivo.isSelected()){
            prepare.setString(12, "SIM");
        }
            else
                prepare.setString(12, "NAO"); 
        prepare.setString(13, txtCodigo.getText());
        
        prepare.executeUpdate();        
        
        JOptionPane.showMessageDialog(null, "Alualizado com Sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        cancelaCadastro();
        prepare.close();        
        
        }catch(SQLException e){e.printStackTrace(); JOptionPane.showMessageDialog(null, "Falha ao atualizar cadastro do cliente! Contate o suporte.","Aviso",JOptionPane.WARNING_MESSAGE);}
        finally{
        }        
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaCBO();
        desabilitaTela();        
        
    }     
}
