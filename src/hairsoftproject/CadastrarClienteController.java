/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hairsoftproject;

import static hairsoftproject.DAO.ConexaoMySql.ReiniciarConexao;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
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
    private TextField txtEstado;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtAtivo;
    
    public void validaCampos(){
        
    }
    
    public int pegarUltimoID() throws SQLException{
    ReiniciarConexao();
    String sql = "SELECT MAX(CLIENTE_ID) AS ULTIMOID FROM CLIENTE";
    PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
    ResultSet rs = stmt.executeQuery();
    rs.next();
    int lastId = rs.getInt("ULTIMOID");

	rs.close();
	stmt.close();

	return lastId;
}
    
    public void desabilitaTela(){
        txtCodigo.setDisable(true);
        txtCPF.setDisable(true);
        txtRG.setDisable(true);
        txtNome.setDisable(true);
        txtEndereco.setDisable(true);
        txtNumero.setDisable(true);
        txtCEP.setDisable(true);
        txtCompl.setDisable(true);
        txtBairro.setDisable(true);
        txtCidade.setDisable(true);
        txtEstado.setDisable(true);
        txtTelefone.setDisable(true);
        txtEmail.setDisable(true);
        txtAtivo.setDisable(true);
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
        txtEstado.setDisable(false);
        txtTelefone.setDisable(false);
        txtEmail.setDisable(false);
        txtAtivo.setDisable(false);
        btnAlterar.setDisable(false);
        btnSalvar.setDisable(false);
        btnAlterar.setDisable(true);
        btnCancelar.setDisable(false);
        btnNovo.setDisable(true);
        
        try{
        String sql = "SELECT MAX(CLIENTE_ID) AS ULTIMOID FROM CLIENTE";
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int CLIENTE_ID = rs.getInt("ULTIMOID");
        txtCodigo.setText(Integer.toString(CLIENTE_ID +1));
        
        rs.close();
	stmt.close();
        
        }catch(SQLException e){e.printStackTrace();JOptionPane.showMessageDialog(null, "Falha ao recuperar código! Contate o suporte.");}
        finally{
        }
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
        txtEstado.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtAtivo.setText("");
        btnNovo.setDisable(false);
        btnCancelar.setDisable(true);
        txtCodigo.setDisable(true);
        txtCPF.setDisable(true);
        txtRG.setDisable(true);
        txtNome.setDisable(true);
        txtEndereco.setDisable(true);
        txtCEP.setDisable(true);
        txtNumero.setDisable(true);
        txtCompl.setDisable(true);
        txtBairro.setDisable(true);
        txtCidade.setDisable(true);
        txtEstado.setDisable(true);
        txtTelefone.setDisable(true);
        txtEmail.setDisable(true);
        txtAtivo.setDisable(true);
        btnAlterar.setDisable(true);
        btnSalvar.setDisable(true);
        btnAlterar.setDisable(true);
    }

    public void salvaDados() throws SQLException{      
        
        try{                 
        PreparedStatement stmt = connection.prepareStatement
                ("INSERT INTO CLIENTE (CLIENTE_ID, NOME, "
                + "CPF, TELEFONE, RUA, CIDADE, ESTADO, NUMERO"
                + ", BAIRRO, CEP, RG, EMAIL, ATIVO)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        
        stmt.setString(1, txtCodigo.getText());
        stmt.setString(2, txtNome.getText());
        stmt.setString(3, txtCPF.getText());
        stmt.setString(4, txtTelefone.getText());
        stmt.setString(5, txtEndereco.getText());
        stmt.setString(6, txtCidade.getText());
        stmt.setString(7, txtEstado.getText());
        stmt.setString(8, txtNumero.getText());
        stmt.setString(9, txtBairro.getText());
        stmt.setString(10, txtCEP.getText());
        stmt.setString(11, txtRG.getText());
        stmt.setString(12, txtEmail.getText());
        stmt.setString(13, txtAtivo.getText());        
        
        stmt.executeUpdate();        
        
        JOptionPane.showMessageDialog(null, "Cliente Cadastrado!");
        cancelaCadastro();
        stmt.close();
        
        
        }catch(SQLException e){e.printStackTrace(); JOptionPane.showMessageDialog(null, "Falha ao efetuar o cadastro do cliente! Contate o suporte.");}
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        desabilitaTela();
        
    }     
}
