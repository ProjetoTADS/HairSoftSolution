
package hairsoftproject;

import static hairsoftproject.DAO.ConexaoMySql.fecharConexao;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Eduardo Dias
 */
public class AjudaController implements Initializable {

    /*INSTANCIANDO COMPONENTES DA TELA*/
       
    
    @FXML
    private TextField txtNome;
    
    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtCelular;

    @FXML
    private TextField txtAssunto;

    @FXML
    private TextArea txtMensagem;
    
    Connection conexao = hairsoftproject.DAO.ConexaoMySql.getConexaoMySql();
    /*MÉTODO VERIFICA CAMPO VAZIO*/
    @FXML
    public boolean verificaVazio(){
        
        if(txtMensagem.getText().equals("") || txtNome.getText().equals("") || txtEmail.getText().equals("") || txtCelular.getText().equals("") || txtAssunto.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente!", "ATENÇÃO!",JOptionPane.WARNING_MESSAGE);
           return false;
        }
        return true;
    }
    
    public void limpaCampos(){
        txtNome.setText("");
        txtEmail.setText("");
        txtCelular.setText("");
        txtAssunto.setText("");
        txtMensagem.setText("");
    }
    
    
    @FXML
    public void solicitaSuporteEnviar(){
        
        
        if(verificaVazio() == false){
            System.out.println("Mensagem não enviada! Campo vazio encontrado!");
        }else{
        
            try{                 
        PreparedStatement stateVar = conexao.prepareStatement
                ("INSERT INTO SUPORTE (NOME, EMAIL, TELEFONE, ASSUNTO, MENSAGEM, DATA_HORA) VALUES(?,?,?,?,?,now())");
        
        stateVar.setString(1, txtNome.getText());
        stateVar.setString(2, txtEmail.getText());
        stateVar.setString(3, txtCelular.getText());
        stateVar.setString(4, txtAssunto.getText());
        stateVar.setString(5, txtMensagem.getText());
  
        stateVar.executeUpdate();/*EXECUTA A QUERY*/
        
        limpaCampos();/*LIMPA OS CAMPOS PREENCHIDOS*/
       
       JOptionPane.showMessageDialog(null, "MENSAGEM ENVIADA! AGUARDE NOSSO RETORNO ATRAVÉS DO SEU E-MAIL","ENVIO REALIZADO!",JOptionPane.WARNING_MESSAGE); 
       stateVar.close();
       fecharConexao();
       }catch(SQLException e){e.printStackTrace(); JOptionPane.showMessageDialog(null, "Houve um problema ao enviar sua solicitação! Contate o suporte por telefone!","Erro no Envio",JOptionPane.WARNING_MESSAGE);}      
       finally{
       }
    
       }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    
    
    
}
