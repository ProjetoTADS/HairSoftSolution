package hairsoftproject;

import java.io.Serializable;

public class Cliente implements Serializable {

//CRIANDO VARIAVEIS
    private int cdCliente;
    private String nome;
    private String cpf;
    private String endereco;
    private String celular;
   

//METODO CONSTRUTOR DA CLASSE
    public Cliente(){
    }

 //METODO COM ASSINATURA DA CLASSE - PASSANDO PARAMETROS   
    public Cliente(int cdCliente, String nome, String cpf, String celular, String rua) {

        this.cdCliente = cdCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.celular = celular;
        this.endereco = rua;
    }
//GETERS E SETERS
    public int getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(int cdCliente) {
        this.cdCliente = cdCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getCelular() {
        return celular;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    

    @Override
    public String toString() {
        return this.nome;
    }
    
}
