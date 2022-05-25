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
public class Instituicao implements Serializable, IEntity {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable=false)
    private int num_animais;
    
    @Column(nullable=false)
    private int capacidade;
    
    @Column(nullable=false)
    private String num_registro;
    
    @Column(nullable=false)
    private String certificacoes;
    
    @Column(nullable=false)
    private String nome;
    
    @Column(nullable=true)
    private Date data_fundacao;

    public Instituicao(int num_animais, int capacidade, String num_registro, String certificacoes, String nome, Date data_fundacao) {
        this.num_animais = num_animais;
        this.capacidade = capacidade;
        this.num_registro = num_registro;
        this.certificacoes = certificacoes;
        this.nome = nome;
        this.data_fundacao = data_fundacao;
    }
     
    public void atualizarInfo(){
        
    }

    public void anunciarPet(Pet pet){
        
    }
    
    public void retiraAnuncio(Pet pet){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Instituicao other = (Instituicao) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "Instituicao{" + "id=" + id + '}';
    }

}
