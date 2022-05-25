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
@Table(name = "contatos")
public class Contato implements Serializable, IEntity {

    private Long id;
    
    private String nome;
    private String tipo;    
    private String info;
    
    private Usuario usuario;

    
    public Contato() {}
    
    public Contato(
        String nome,
        String tipo,
        String info
    ) {
        this.nome = nome;
        this.tipo = tipo;
        this.info = info;
    }
    
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
         this.id = id;
    }

    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getTipo(){
        return tipo;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public String getInfo(){
        return info;
    }
    public void setInfo(String info){
        this.info = info;
    }

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = true)
    public Usuario getUsuario(){
        return usuario;
    }
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
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
        if (!(object instanceof Contato)) {
            return false;
        }
        Contato other = (Contato) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.NewEntity[ id=" + id + " ]";
    }
    
}
