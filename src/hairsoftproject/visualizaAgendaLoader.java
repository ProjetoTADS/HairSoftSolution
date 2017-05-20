
//stLmpp

package hairsoftproject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class visualizaAgendaLoader {
    private final StringProperty agen;
    private final StringProperty cliente;
    private final StringProperty data;
    private final StringProperty servicos;
    private final StringProperty preco;
    
    public visualizaAgendaLoader(String agen, String cliente, String data, String servicos, String preco){
        this.cliente = new SimpleStringProperty(cliente);
        this.data = new SimpleStringProperty(data);
        this.servicos = new SimpleStringProperty(servicos);
        this.preco = new SimpleStringProperty(preco);
        this.agen = new SimpleStringProperty(agen);
    }
    //Getters
    public String getAgen(){
        return agen.get();
    }
    public String getCliente(){
        return cliente.get();
    }
    public String getData(){
        return data.get();
    }
    public String getServicos(){
        return servicos.get();
    }
    public String getPreco(){
        return preco.get();
    }
    //Setters
    public void setAgen(String value){
        agen.set(value);
    }
    public void setCliente(String value){
        cliente.set(value);
    }
    public void setData(String value){
        data.set(value);
    }
    public void setServicos(String value){
        servicos.set(value);
    }
    public void setPreco(String value){
        preco.set(value);
    }
    //Property values
    public StringProperty agenProperty(){
        return agen;
    }
    public StringProperty clienteProperty(){
        return cliente;
    }
    public StringProperty dataProperty(){
        return data;
    }
     public StringProperty servicosProperty(){
        return servicos;
    } 
    public StringProperty precoProperty(){
        return preco;
    }
}
