/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import javax.persistence.TypedQuery;
import modelo.Variacao;

/**
 *
 * @author Mac
 */
public class VariacaoDAO extends JpaDAO<Variacao> {

    private static VariacaoDAO instance;

    public static VariacaoDAO getInstance() {
        if (instance == null) {
            instance = new VariacaoDAO();
        }

        return instance;
    }

    private VariacaoDAO() {
        super(Variacao.class);
    }

    public Variacao findExistingVariacao(String porte, String especie, String raca) {
        TypedQuery<Variacao> query = this.entityManager
                .createQuery("SELECT A FROM " + this.entity.getName() + " A WHERE especie=:especie AND raca=:raca AND porte=:porte ", Variacao.class);

        List<Variacao> variacoes = query
                .setParameter("especie", especie)
                .setParameter("raca", raca)
                .setParameter("porte", porte)
                .getResultList();
        
        if (variacoes.size() > 0) {
            return variacoes.get(0);
        }

        return null;
    }

    public List<Variacao> getAllVariacoes() {
        TypedQuery<Variacao> query = this.entityManager
                .createQuery("SELECT A FROM " + this.entity.getName() + " A", Variacao.class);

        List<Variacao> variacoes = query.getResultList();

        return variacoes;
    }

    public long getVariacaoFilter(String especie, String raca, String porte) {
        TypedQuery<Variacao> query = this.entityManager
                .createQuery("SELECT A FROM " + this.entity.getName() + " A WHERE especie=:especie AND raca=:raca AND porte=:porte", Variacao.class);

        long variacaoEncontrada = query.setParameter("especie", especie)
                .setParameter("raca", raca)
                .setParameter("porte", porte)
                .getResultList().get(0).getId();

        return variacaoEncontrada;
    }
}
