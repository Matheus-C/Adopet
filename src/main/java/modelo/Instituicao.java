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
import javax.persistence.Transient;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "instituicoes")
public class Instituicao extends Usuario {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private int numeroAnimais;

    @Column(nullable = false)
    private int capacidade;

    @Column(nullable = false)
    private String numeroRegistro;

    @Column(nullable = false)
    private String certificacoes;

    @Column(nullable = false)
    private String razaoSocial;

    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date dataFundacao;
    
    public Instituicao() {
        super();
    }

    public Instituicao(String login, String senha, Endereco endereco, String nome, String perfil, int num_animais, int capacidade, String num_registro, String certificacoes, String razaoSocial, Date data_fundacao) {
        super(login, senha, endereco, nome, perfil);

        this.numeroAnimais = num_animais;
        this.capacidade = capacidade;
        this.numeroRegistro = num_registro;
        this.certificacoes = certificacoes;
        this.razaoSocial = razaoSocial;
        this.dataFundacao = data_fundacao;
    }

    public int getNumeroAnimais() {
        return numeroAnimais;
    }

    public void setNumeroAnimais(int numeroAnimais) {
        this.numeroAnimais = numeroAnimais;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public String getCertificacoes() {
        return certificacoes;
    }

    public void setCertificacoes(String certificacoes) {
        this.certificacoes = certificacoes;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public void atualizarInfo() {

    }

    public void anunciarPet(Pet pet) {

    }

    public void retiraAnuncio(Pet pet) {

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Instituicao other = (Instituicao) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Instituicao{" + "id=" + id + '}';
    }

}
