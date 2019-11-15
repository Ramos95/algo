/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Pelicula;

/**
 *
 * @author estuardo
 */
@Stateless
public class PeliculaFacade extends AbstractFacade<Pelicula> {
    @PersistenceContext(unitName = "cinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeliculaFacade() {
        super(Pelicula.class);
    }
    
     public List<Pelicula> peliculaPorGenero(String nombre){
        if (em != null && !nombre.equals("")) {
            Query query = em.createQuery("SELECT p FROM Pelicula p JOIN p.generoList g WHERE g.nombre = :nombre");
            query.setParameter("nombre", nombre);
            return query.getResultList();
        }
        return Collections.EMPTY_LIST;
    }
}
