/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trabappcorp.Adopet;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Matheus
 */
@Entity
public class Pet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable=false)
    private String nome;
    
    @Column(nullable=true)
    private Date data_nasc;
    
    @Column(nullable=false)
    private Double peso;
    
    @Column(nullable=false)
    private Double altura;
    
    @Column(nullable=false)
    private Boolean adotado;
    
    @Column(nullable=false)
    private Usuario dono;
    
    @JoinColumn(name="id_pet")
    private Variacao variacao;
    
    @Column(nullable=true)
    private List<Adotante> desejadoPor;
    
    @Column(nullable=false)
    private Double gastoMensal;
    
    @Column(nullable=true)
    private String observacoes;
    
    @Column(nullable=false)
    private List<String> fotos;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Pet)) {
            return false;
        }
        Pet other = (Pet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.trabappcorp.Adopet.Pet[ id=" + id + " ]";
    }
    
}
