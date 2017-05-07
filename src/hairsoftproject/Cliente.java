package hairsoftproject;

import java.io.Serializable;

public class Cliente implements Serializable {

    private int cdCliente;
    private String nome;
    private String cpf;
    private String endereco;
    private int celular;

    public Cliente(){
    }
    
    public Cliente(int cdCliente, String nome, String cpf, int celular, String rua) {
        this.cdCliente = cdCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.celular = celular;
        this.endereco = rua;
    }

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
    
    public int getCelular() {
        return celular;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public void setCelular(int telefone) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return this.nome;
    }
    
}
