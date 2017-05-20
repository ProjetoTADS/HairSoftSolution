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
public class agendaOpL {
    private final StringProperty cpf;
    private final StringProperty nome;
    private final StringProperty desc;
    private final StringProperty dataagen;
    
    
    public agendaOpL(String cpf, String nome, String desc, String dataagen){
        this.cpf = new SimpleStringProperty(cpf);
        this.nome = new SimpleStringProperty(nome);
        this.desc = new SimpleStringProperty(desc);
        this.dataagen = new SimpleStringProperty(dataagen);
    }
    
    //Getters
    public String getCpf(){
        return cpf.get();
    }
    public String getNome(){
        return nome.get();
    }
    public String getDesc(){
        return desc.get();
    }
    public String getDataagen(){
        return dataagen.get();
    }
    //Setters
    public void setCpf(String value){
        cpf.set(value);
    }
    public void setNome(String value){
        nome.set(value);
    }
    public void setDesc(String value){
        desc.set(value);
    }
    public void setDataagen(String value){
        dataagen.set(value);
    }
    //Property values
    public StringProperty cpfProperty(){
        return cpf;
    }
    public StringProperty nomeProperty(){
        return nome;
    }
    public StringProperty descProperty(){
        return desc;
    }
    public StringProperty dataagenProperty(){
        return dataagen;
    }
}
