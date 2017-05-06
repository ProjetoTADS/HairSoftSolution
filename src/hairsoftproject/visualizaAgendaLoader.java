/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hairsoftproject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author guist
 */
public class visualizaAgendaLoader {
    private final StringProperty cliente;
    private final StringProperty data;
    private final StringProperty servicos;
    private final StringProperty preco;
    
    public visualizaAgendaLoader(String cliente, String data, String servicos, String preco){
        this.cliente = new SimpleStringProperty(cliente);
        this.data = new SimpleStringProperty(data);
        this.servicos = new SimpleStringProperty(servicos);
        this.preco = new SimpleStringProperty(preco);
    }
    //Getters
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
    public void setCliente(String value){
        cliente.set(value);
    }
    public void setData(String value){
        data.set(value);
    }
    //Property values
    public StringProperty cienteProperty(){
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
