/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import javax.persistence.TypedQuery;
import modelo.Adotante;
import modelo.Doador;
import modelo.FiltroAdotante;
import modelo.Instituicao;
import modelo.Pet;

/**
 *
 * @author Mac
 */
public class InstituicaoDAO extends JpaDAO<Instituicao> {

    private static InstituicaoDAO instance;

    public static InstituicaoDAO getInstance() {
        if (instance == null) {
            instance = new InstituicaoDAO();
        }

        return instance;
    }

    private InstituicaoDAO() {
        super(Instituicao.class);
    }

    public List<Instituicao> findFor(Adotante adotante) {

        String cidade = adotante.getEndereco().getCidade();
        String estado = adotante.getEndereco().getEstado();

        String queryWhere = " WHERE 1=1 ";

        if(cidade != null && estado != null){
            queryWhere += " AND E.cidade=:cidade AND E.estado=:estado";
        }

        TypedQuery<Instituicao> query = this.entityManager
                .createQuery("SELECT I FROM " + this.entity.getName() + " I JOIN I.endereco E " + queryWhere + " ORDER BY I.numeroAnimais DESC", Instituicao.class);
        
        if(cidade != null && estado != null) {
            query.setParameter("cidade", cidade);
            query.setParameter("estado", estado);
        }

        List<Instituicao> instituicoesEncontradas = query.getResultList();

        return instituicoesEncontradas;
    }
    
        public List<Instituicao> findFor(Doador doador) {

        String cidade = doador.getEndereco().getCidade();
        String estado = doador.getEndereco().getEstado();

        String queryWhere = " WHERE 1=1 ";

        if(cidade != null && estado != null){
            queryWhere += " AND E.cidade=:cidade AND E.estado=:estado";
        }

        TypedQuery<Instituicao> query = this.entityManager
                .createQuery("SELECT I FROM " + this.entity.getName() + " I JOIN I.endereco E " + queryWhere + " ORDER BY I.numeroAnimais ASC", Instituicao.class);
        
        if(cidade != null && estado != null) {
            query.setParameter("cidade", cidade);
            query.setParameter("estado", estado);
        }

        List<Instituicao> instituicoesEncontradas = query.getResultList();

        return instituicoesEncontradas;
    }
}
