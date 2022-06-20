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
import java.io.Serializable;
import javax.persistence.*;
@Entity
@Table(name = "enderecos")
public class Endereco implements Serializable, IEntity {

    private Long id;
    
    private String cidade;
    private String bairro;    
    private String estado;
    private String rua;
    private Integer numero;
    private String complemento;
    
    public Endereco() {}
    
    public Endereco(
        String cidade,
        String bairro,
        String estado,
        String rua,
        Integer numero,
        String complemento
    ) {
        this.cidade = cidade;
        this.bairro = bairro;
        this.estado = estado;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }
    
    @Id
    @GeneratedValue
    @Override
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
         this.id = id;
    }
    
    @Column(nullable = true)
    public String getCidade(){
        return cidade;
    }
    public void setCidade(String cidade){
        this.cidade = cidade;
    }

    @Column(nullable = true)
    public String getBairro(){
        return bairro;
    }
    public void setBairro(String bairro){
        this.bairro = bairro;
    }
    
    @Column(nullable = true)
    public String getRua(){
        return rua;
    }
    public void setRua(String rua){
        this.rua = rua;
    }

    @Column(nullable = true)
    public Integer getNumero(){
        return numero;
    }
    public void setNumero(Integer numero){
        this.numero = numero;
    }
    
    @Column(nullable = true)
    public String getEstado(){
        return estado;
    }
    public void setEstado(String estado){
        this.estado = estado;
    }
    
    @Column(nullable = true)
    public String getComplemento(){
        return complemento;
    }
    public void setComplemento(String complemento){
        this.complemento = complemento;
    }

    public void atualizarInfo(String cidade,
        String bairro,
        String estado,
        String rua,
        int numero,
        String complemento
    ) {
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Endereco)) {
            return false;
        }
        Endereco other = (Endereco) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Endereco[ id=" + id + " ]";
    }
}
