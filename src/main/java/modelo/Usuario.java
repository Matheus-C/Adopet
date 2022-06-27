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
import DAO.ContatoDAO;
import HttpErrors.BadRequest;
import HttpErrors.HttpError;
import HttpErrors.NotFound;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuarios")
public abstract class Usuario implements Serializable, IEntity {

    protected Long id;

    private String login;
    private String senha;
    private Endereco endereco;

    private List<Contato> contatos = new ArrayList<>();
    private Set<Pet> petsCadastrados;

    private String nome;
    private String perfil;

    public Usuario() {
    }

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
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(nullable = false)
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_endereco", nullable = false)
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Contato> getContatos() {
        return this.contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    @OneToMany(mappedBy = "dono")
    public Set<Pet> getPetsCadastrados() {
        return this.petsCadastrados;
    }

    public void setPetsCadastrados(Set<Pet> pets) {
        this.petsCadastrados = pets;
    }

    @Column(nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public void atualizarInfo(String nome, String senha, String perfil) {
        if (nome != null) {
            this.setNome(nome);
        }
        if (senha != null) {
            this.setSenha(senha);
        }
        if (perfil != null) {
            this.setPerfil(perfil);
        }
    }

    public void adicionarContato(String nome, String tipo, String info) throws HttpError {
        for (Contato contato : this.contatos) {
            if (contato.getNome().equals(nome)) {
                throw new HttpErrors.BadRequest("O usuário já tem um contato com este nome");
            }
        }

        Contato novoContato = new Contato(nome, tipo, info);

        this.contatos.add(novoContato);
    }

    public void removerContato(String nome) throws HttpError{
        for (Contato contato : this.contatos) {
            if (contato.getNome().equals(nome)) {
                this.contatos.remove(contato);
                return;
            }
        }

        throw new HttpErrors.NotFound("Usuário não possui contato com este nome");
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
        return "modelo.Usuario[ id=" + id + " ]";
    }
}
