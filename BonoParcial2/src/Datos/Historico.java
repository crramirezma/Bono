/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;

import java.util.Date;

import Entidad.Sensor;
import javax.persistence.TemporalType;
//insert into ADMIN_APP.HISTORICO(FECHA_Y_HORA,PROCESAMIENTO,VALOR,SENSOR_ID) values ('2020-12-07 23:55:13.348','Error',4500,4)
//insert into ADMIN_APP.HISTORICO(FECHA_Y_HORA,PROCESAMIENTO,VALOR,SENSOR_ID) values ('2020-12-09 19:47:50.023','Error',10000001,4)
/**
 *
 * @author crist
 */
@Entity
@Table(name="Historico")

public class Historico {
    public static final String NORMAL="Normal";
    public static final String ERROR="ERROR";
    
    
    private double valor;
    private String procesamiento;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_y_hora;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    
    private Sensor sensor;

    
    public Historico(){
        
    }
    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getProcesamiento() {
        return procesamiento;
    }

    public void setProcesamiento(String procesamiento) {
        this.procesamiento = procesamiento;
    }

    public Date getFecha_y_hora() {
        return fecha_y_hora;
    }

    public void setFecha_y_hora(Date fecha_y_hora) {
        this.fecha_y_hora = fecha_y_hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
    
    
    
}
