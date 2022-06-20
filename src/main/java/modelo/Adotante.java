/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Mac
 */
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
@Entity
@Table(name = "adotantes")
public class Adotante extends Usuario {

    private Set<Pet> petsDesejados;

    private String CPF;
    private boolean adotando;
    private Date dataNascimento;

    public Adotante() {
        super();
    }
    
    public Adotante(
        String login, String senha, Endereco endereco, String nome, String perfil,
        String CPF,
        Date dataNascimento
    ) {

        super(login, senha, endereco, nome, perfil);
        
        this.adotando = false;
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
    }
    
    @Column(nullable = false)
    public boolean getAdotando(){
        return adotando;
    }
    public void setAdotando(boolean adotando){
        this.adotando = adotando;
    }
    
    @Column(nullable = false, unique = true)
    public String getCPF(){
        return CPF;
    }
    public void setCPF(String CPF){
        this.CPF = CPF;
    }

    @Temporal(TemporalType.DATE)
    public Date getDataNascimento(){
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento){
        this.dataNascimento = dataNascimento;
    }

    @ManyToMany(mappedBy="desejadoPor")
    public Set<Pet> getPetsDesejados() {
        return this.petsDesejados;
    }
    public void setPetsDesejados(Set<Pet> petsDesejados) {
        this.petsDesejados = petsDesejados;
    }
    
    public void atualizarInfo() {}
    
    public void desejaAdotar(Pet pet) {}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adotante)) {
            return false;
        }
        Adotante other = (Adotante) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Adotante[ id=" + id + " ]";
    }
}
