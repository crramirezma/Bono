/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import DAO.DatoDAO;
import Entidad.Sensor;
import Entidad.TipoSensor;

/**
 *
 * @author crist
 */
public class ValidarDatos {
    DatoDAO dao=new DatoDAO();
    
    public Sensor verificarSensor(int id_sensor){
        return dao.leerSensor(id_sensor);
    }
    
    public boolean verficarTipoSensor(){
        
        return false;
    }
    
    public boolean verificarDatoHistorico(Historico historico){
        TipoSensor tipo=historico.getSensor().getTipo();
        //TipoSensor tipo=dao.leerTipo(historico.getSensor().getTipo());
        return !(tipo.getMaximoPermitido()<historico.getValor()||tipo.getMinimoPermitido()>historico.getValor());
    }
    
    
    
    
}
