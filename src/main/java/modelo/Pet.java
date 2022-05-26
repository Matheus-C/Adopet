/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author Matheus
 */
@Entity
public class Pet implements Serializable, IEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable=false)
    private String nome;
    
    @Column(nullable=true)
    private Date dataNascimento;
    
    @Column(nullable=false)
    private Double peso;
    
    @Column(nullable=false)
    private Double altura;
    
    @Column(nullable=false)
    private Boolean adotado;
    
    @ManyToOne
    @JoinColumn(name="id_dono", nullable=false)    
    private Usuario dono;
    
    @ManyToOne
    @JoinColumn(name="id_variacao", nullable = false)
    private Variacao variacao;
    
    @ManyToMany
    private Set<Adotante> desejadoPor;
    
    @Column(nullable=false)
    private Double gastoMensal;
    
    @Column(nullable=true)
    private String observacoes;
    
    @Column(nullable=false)
    private String fotos;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Double getPeso() {
        return peso;
    }
    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }
    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Boolean getAdotado() {
        return adotado;
    }
    public void setAdotado(Boolean adotado) {
        this.adotado = adotado;
    }

    public Usuario getDono() {
        return dono;
    }
    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public Variacao getVariacao() {
        return variacao;
    }
    public void setVariacao(Variacao variacao) {
        this.variacao = variacao;
    }

    public Set<Adotante> getDesejadoPor() {
        return desejadoPor;
    }
    public void setDesejadoPor(Set<Adotante> desejadoPor) {
        this.desejadoPor = desejadoPor;
    }

    public Double getGastoMensal() {
        return gastoMensal;
    }
    public void setGastoMensal(Double gastoMensal) {
        this.gastoMensal = gastoMensal;
    }

    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getFotos() {
        return fotos;
    }
    public void setFotos(String fotos) {
        this.fotos = fotos;
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
        return "modelo.Pet[ id=" + id + " ]";
    }
    
}
