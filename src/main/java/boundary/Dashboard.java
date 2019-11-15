/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import control.GeneroFacade;
import control.PeliculaFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Genero;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Pelicula;

/**
 *
 * @author estuardo
 */
@Named(value = "dashboardBean")
@ViewScoped
public class Dashboard implements Serializable {
    @Inject
    GeneroFacade generoFacade;
    @Inject
    PeliculaFacade peliculaFacade;
    
    private Pelicula pelicula;
    private Genero genero;
    
    
    private Date desde;
    private Date hasta;
    private String nombre;
    
     public List<Genero> complete(String nombre){
        return generoFacade.complete(nombre);
    }
    
     
     public List<Pelicula> getPeliculaPorGenero(){
        return peliculaFacade.peliculaPorGenero(nombre);
    }
     
     public void gnerarReporteDirectores(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Jasper?reporte=asiento");
        } catch (IOException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void generarReportePeliculas(){
      try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Jasper?reporte=pelicula"+"&desde=" + desde + "&hasta=" + hasta);
        } catch (IOException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
