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
@Table(name = "filtros_adotante")
public class FiltroAdotante implements Serializable, IEntity {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String especie;
    private String raca;
    private String porte;
    private Double alturaMenor;
    private Double pesoMenor;
    private Double gastoMensalMenor;

    public FiltroAdotante() {
        super();
    }
    
    public FiltroAdotante(
    String especie,
    String raca,
    String porte,
    Double alturaMenor,
    Double pesoMenor,
    Double gastoMensalMenor
    ) {
        super();

        this.especie = especie;
        this.raca = raca;
        this.porte = porte;
        this.alturaMenor = alturaMenor;
        this.pesoMenor = pesoMenor;
        this.gastoMensalMenor = gastoMensalMenor;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEspecie(){
        return especie;
    }
    public void setEspecie(String especie){
        this.especie = especie;
    }

    public String getRaca(){
        return raca;
    }
    public void setRaca(String raca){
        this.raca = raca;
    }

    public String getPorte(){
        return porte;
    }
    public void setPorte(String porte){
        this.porte = porte;
    }

    public Double getAlturaMenor(){
        return alturaMenor;
    }
    public void setAlturaMenor(Double alturaMenor){
        this.alturaMenor = alturaMenor;
    }

    public Double getPesoMenor(){
        return pesoMenor;
    }
    public void setPesoMenor(Double pesoMenor){
        this.pesoMenor = pesoMenor;
    }

    public Double getGastoMensalMenor(){
        return gastoMensalMenor;
    }
    public void setGastoMensalMenor(Double gastoMensalMenor){
        this.gastoMensalMenor = gastoMensalMenor;
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
        if (!(object instanceof FiltroAdotante)) {
            return false;
        }
        FiltroAdotante other = (FiltroAdotante) object;
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
