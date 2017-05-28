
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

public class AjudaController implements Initializable {

    @FXML
    private TextField lbNome;
    @FXML
    private TextField lbEmail;
    @FXML
    private TextField lbTelef;
    @FXML
    private TextField lbAssunto;
    @FXML
    private TextArea lbMensag;
    
    Connection connection = hairsoftproject.DAO.ConexaoMySql.getConexaoMySql();
        
      public void limpar(){
        lbNome.setText("");
        lbEmail.setText("");
        lbTelef.setText("");
        lbAssunto.setText("");
        lbMensag.setText("");
    }
    
    @FXML
    public void SolicitarAjuda(){
        
      
            if(lbNome.getText().equals("") || lbEmail.getText().equals("") || lbTelef.getText().equals("") || lbAssunto.getText().equals("") || lbMensag.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Existe campo vazio. Preencha todos os campos antes de enviar!","Atenção",JOptionPane.OK_OPTION);
            }else{
     
            try{                 
        PreparedStatement stateVar = connection.prepareStatement                 
        ("INSERT INTO SUPORTE (NOME, EMAIL, TELEFONE, ASSUNTO, MENSAGEM, DATA_HORA) VALUES(? ,? ,? ,? ,? ,now())");
        
        stateVar.setString(1, lbNome.getText());
        stateVar.setString(2, lbEmail.getText());
        stateVar.setString(3, lbTelef.getText());
        stateVar.setString(4, lbAssunto.getText());
        stateVar.setString(5, lbMensag.getText());
  
        stateVar.executeUpdate();
        
        limpar();
       
       JOptionPane.showMessageDialog(null, "Enviado com sucesso!","Envio realizado!",JOptionPane.WARNING_MESSAGE); 
       stateVar.close();
       fecharConexao();
       }catch(SQLException e){e.printStackTrace(); JOptionPane.showMessageDialog(null, "Problema ao enviar sua solicitação! Tente novamente! ","Erro no Envio",JOptionPane.WARNING_MESSAGE);}      
       finally{
       }
    
            }       
     
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    
    
    
}
