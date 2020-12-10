/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import DAO.DatoDAO;
import Entidad.Sensor;
import Entidad.TipoSensor;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
    
    
    public int verificarProcesamiento(ArrayList<Historico> historicos){
        int val=0;
        if(historicos.size()==1){
            Historico h=historicos.get(0);
            TipoSensor tipo=h.getSensor().getTipo();
            

            if(h.getValor()<=tipo.getMinimoPermitido())
                val=1;
            else if(h.getValor()>=tipo.getMaximoPermitido())
                val=3;
            else
                val=2;

            return val;
        }else if(historicos.size()>0){
            int prom=0;
            TipoSensor tipo=historicos.get(0).getSensor().getTipo();
            for(int i=0;i<historicos.size();i++){
                prom+=historicos.get(i).getValor();
            }
            prom/=historicos.size();
            JOptionPane.showMessageDialog(null,"Promedio de valores del sensor: "+ prom,"Promedio",JOptionPane.INFORMATION_MESSAGE);
            if(prom<=tipo.getMinimoPermitido())
                val=1;
            else if(prom>=tipo.getMaximoPermitido())
                val=3;
            else
                val=2;

            return val;
        }else{
            return 0;
        }
        
    }
    
}
