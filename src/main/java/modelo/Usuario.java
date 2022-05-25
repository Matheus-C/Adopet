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
import java.util.Set;
import javax.persistence.*;
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable, IEntity {

    private Long id;
    
    private String login;
    private String senha;
    private Endereco endereco;
    private Set<Contato> contatos;
    private String nome;
    private String perfil;
    
    public Usuario() {}
    
    public Usuario(
        String login,
        String senha,
        Endereco endereco,
        String nome,
        String perfil
    ) {
        this.login = login;
        this.senha = senha;
        this.endereco = endereco;
        this.nome = nome;
        this.perfil = perfil;
    }
    
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
         this.id = id;
    }
    
    public String getLogin(){
        return login;
    }
    public void setLogin(String login){
        this.login = login;
    }
        
    public String getSenha(){
        return senha;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }
        
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    public Endereco getEndereco(){
        return endereco;
    }
    public void setEndereco(Endereco endereco){
        this.endereco = endereco;
    }

    @OneToMany(mappedBy="usuario")
    public Set<Contato> getContatos() {
        return this.contatos;
    }
    public void setContatos(Set<Contato> contatos) {
        this.contatos = contatos;
    }
            
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
            
    public String getPerfil(){
        return perfil;
    }
    public void setPerfil(String perfil){
        this.perfil = perfil;
    }
    
    public void atualizarInfo() {}
    
    public void adicionarContato(String nome, String tipo, String info) {}
    
    public void removerContato(String nome) {}
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
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
