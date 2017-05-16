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
import java.sql.Types;
import java.util.ResourceBundle;
import javafx.animation.KeyValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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
    private ComboBox cboEstado;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtEmail;
    @FXML
    private CheckBox chkAtivo;
    
    public void carregaCampos(){
        
    ObservableList<String> list = FXCollections.observableArrayList("RO","AC","AM","RR","PA","AP","TO","MA","PI","CE","RN","PB","PE","AL","SE","BA","MG","ES","RJ","SP","PR","SC","RS","MS","MT","GO","DF");
    cboEstado.setItems(list);
     
      
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
        
        try{
        String sql = "SELECT MAX(CLIENTE_ID) AS ULTIMOID FROM CLIENTE";
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int CLIENTE_ID = rs.getInt("ULTIMOID");
        txtCodigo.setText(Integer.toString(CLIENTE_ID +1));
        
        rs.close();
	stmt.close();
        
        }catch(SQLException e){e.printStackTrace();JOptionPane.showMessageDialog(null,"Aviso","Falha ao recuperar código! Contate o suporte.",JOptionPane.WARNING_MESSAGE);}
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
        //cboEstado.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        chkAtivo.setSelected(false);
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
        cboEstado.setDisable(true);
        txtTelefone.setDisable(true);
        txtEmail.setDisable(true);
        chkAtivo.setDisable(true);
        btnAlterar.setDisable(true);
        btnSalvar.setDisable(true);
        btnAlterar.setDisable(true);
    }

    public void salvaDados() throws SQLException{        
        
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
        stmt.setString(7, (String) cboEstado.getValue());  
        if (txtNumero.getText().isEmpty()){
            stmt.setNull(8, Types.INTEGER);
        }
            else{
                stmt.setInt(8, Integer.parseInt(txtNumero.getText()));
            }        
        stmt.setString(9, txtBairro.getText());
        stmt.setString(10, txtCEP.getText());
        stmt.setString(11, txtRG.getText());
        stmt.setString(12, txtEmail.getText());
        if (chkAtivo.isSelected()){
            stmt.setString(13, "SIM");
        }
            else
                stmt.setString(13, "NAO");        
        
        stmt.executeUpdate();        
        
        JOptionPane.showMessageDialog(null, "Cliente Cadastrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        cancelaCadastro();
        stmt.close();        
        
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaCampos();
        desabilitaTela();        
        
    }     
}
