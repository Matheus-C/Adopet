/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Mac
 */
import java.util.List;
import javax.persistence.TypedQuery;
import modelo.*;

public class UsuarioDAO extends JpaDAO<Usuario> {

    private static UsuarioDAO instance;

    public static UsuarioDAO getInstance() {
        if (instance == null) {
            instance = new UsuarioDAO();
        }

        return instance;
    }

    private UsuarioDAO() {
        super(Usuario.class);
    }

    public Usuario findByLogin(String login) {
        TypedQuery<Usuario> query = this.entityManager
                .createQuery("SELECT A FROM " + this.entity.getName() + " A WHERE login=:login", Usuario.class);

        List<Usuario> usuariosEncontrados = query.setParameter("login", login).getResultList();

        if (usuariosEncontrados.size() > 0) {
            return usuariosEncontrados.get(0);
        }

        return null;

    }
}
