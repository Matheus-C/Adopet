/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Doador implements Serializable, IEntity {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable=false)
    private String cpf;
    
    @Column(nullable=true)
    private Date data_nasc;
    
     public Doador(String cpf, Date data_nasc) {
        this.cpf = cpf;
        this.data_nasc = data_nasc;
    }

    public void atualizarInfo(){
        
    }

    public void anunciarPet(Pet pet){
        
    }
    
    public void retiraAnuncio(Pet pet){
        
    }
    
    public void notificarAdotante(Pet pet, String titulo, String descricao){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

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
