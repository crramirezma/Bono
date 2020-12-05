/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author crist
 */

@Entity
@Table(name="Tipo")
public class TipoSensor {
    private String Nombre;
    private int minimoPermitido;
    private int maximoPermitido;
    private boolean promedio;
    private int numeroHoras;
    
    
    @Id
    private String Tipo;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getMinimoPermitido() {
        return minimoPermitido;
    }

    public void setMinimoPermitido(int minimoPermitido) {
        this.minimoPermitido = minimoPermitido;
    }

    public int getMaximoPermitido() {
        return maximoPermitido;
    }

    public void setMaximoPermitido(int maximoPermitido) {
        this.maximoPermitido = maximoPermitido;
    }

    public boolean isPromedio() {
        return promedio;
    }

    public void setPromedio(boolean promedio) {
        this.promedio = promedio;
    }

    public int getNumeroHoras() {
        return numeroHoras;
    }

    public void setNumeroHoras(int numeroHoras) {
        this.numeroHoras = numeroHoras;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public TipoSensor() {
    }
    
    
    
}
