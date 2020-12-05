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
@Table(name="sensores")

public class Sensor {
    private String ubicacion;
    private TipoSensor tipo;
    
    
    public Sensor(){
        
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public TipoSensor getTipo() {
        return tipo;
    }

    public void setTipo(TipoSensor Tipo) {
        this.tipo = Tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
