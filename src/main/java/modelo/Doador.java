/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "doadores")
public class Doador extends Usuario implements PessoaFisica {

    private static final long serialVersionUID = 1L;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable=false, unique = true)
    private String CPF;
    
    @Column(nullable=true)
    private Date dataNascimento;
    
    public Doador() {
        super();
    }
    
    public Doador(String login, String senha, Endereco endereco, String nome, String perfil, String cpf, Date dataNascimento) {
        super(login, senha, endereco, nome, perfil);

        this.CPF = cpf;
        this.dataNascimento = dataNascimento;
    }

    public String getCPF() {
        return CPF;
    }
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
    
    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    public void atualizarInfo(){
        
    }

    public void anunciarPet(Pet pet){
        
    }

    public void retiraAnuncio(Pet pet){

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Doador other = (Doador) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "Doador{" + "id=" + id + '}';
    } 

}
