/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Datos.Historico;
import Entidad.Sensor;
import Entidad.TipoSensor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 *
 * @author crist
 */
public class DatoDAO {
    private static EntityManagerFactory entity_mf
            =Persistence.createEntityManagerFactory("BonoSensoresPU");
    
    public Sensor leerSensor(int id_sensor){
        EntityManager em=entity_mf.createEntityManager();
        Sensor sensor =null;
        
        Query q=em.createQuery("Select s from Sensor s"+
                " where s.id = ?1")
                .setParameter(1, id_sensor);
        try {
            sensor=(Sensor)q.getSingleResult();
        } catch (NonUniqueResultException e) {
            sensor=(Sensor)q.getResultList().get(0);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            em.close();
            return sensor;
        }
    }
        
    
    
    public void crearHistorico(Historico historico){
        EntityManager em=entity_mf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(historico);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        
    }
    public void crearTipoSensor(TipoSensor tipo){
        EntityManager em=entity_mf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(tipo);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
    }
    
    
    public void crearSensor(Sensor sensor){
        EntityManager em=entity_mf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(sensor);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
    }
    
    public ArrayList<Historico> listar(){
        EntityManager em=entity_mf.createEntityManager();
        
        ArrayList<Historico> listaHistorico=new ArrayList<>();
        Historico historico=null;
        
        Query q=em.createQuery("Select h from Historico h ORDER by h.fecha_y_hora");
        try {
            List result=q.getResultList();
            //System.out.println("Aqui: "+result.size()+"  :  "++"  :  "+);
            for(int i=result.size()-1;i>result.size()-6&&i>=0;i--){
                historico=(Historico)result.get(i);
                listaHistorico.add(historico);  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            em.close();
            return listaHistorico;
        }
        
        
    }
    public ArrayList<Historico> listarPorSensor(Sensor sensor){
        EntityManager em=entity_mf.createEntityManager();
        
        ArrayList<Historico> listaHistorico=new ArrayList<>();
        Historico historico=null;
        
        Query q=em.createQuery("Select h from Historico h where h.sensor = :sens Order by h.fecha_y_hora ").setParameter("sens", sensor);
        try {
            List result=q.getResultList();
            //System.out.println("Aqui: "+result.size()+"  :  "++"  :  "+);
            for(int i=result.size()-1;i>result.size()-6&&i>=0;i--){
                historico=(Historico)result.get(i);
                listaHistorico.add(historico);  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            em.close();
            return listaHistorico;
        }
        
        
    }
    
    //Genera un valor random que se encuentra en los limites de valores de un sensor
    
    
    public TipoSensor leerTipo(String tipo){
        EntityManager em=entity_mf.createEntityManager();
        TipoSensor sensor =null;
        
        Query q=em.createQuery("Select s from TipoSensor s where s.Tipo LIKE :tipo")
                .setParameter("tipo", tipo);
        try {
            sensor=(TipoSensor)q.getSingleResult();
        } catch (NonUniqueResultException e) {
            sensor=(TipoSensor)q.getResultList().get(0);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            em.close();
            return sensor;
        }
    }
    
    
    public ArrayList<Integer> ListaSensoresTipo(TipoSensor tipo){
        EntityManager em=entity_mf.createEntityManager();
        TipoSensor sensor =null;
        ArrayList<Integer> datos=new ArrayList<>();
        Query q=em.createQuery("Select s.id from Sensor s where s.tipo = :tipo")
                .setParameter("tipo", tipo);
        try {
            
            datos.add((int)q.getSingleResult());
        } catch (NonUniqueResultException e) {
            for(int i=0;i<q.getResultList().size();i++)
                datos.add((int)q.getResultList().get(i));
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            em.close();
            return datos;
        }
    }
    
    public ArrayList<Historico> procesarTipo(TipoSensor tipo){
        ArrayList<Historico> h=new ArrayList<>();
        try{
            if(tipo.isPromedio()){
                h=procesarTipoPromedio(tipo);
            }else{
                h.add(procesarTipoNoPromedio(tipo));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //em.close();
            return h;
        }
            
        
        //return h;
        
        
        //return historico;
    }
    public Historico procesarTipoNoPromedio(TipoSensor tipo){
        EntityManager em=entity_mf.createEntityManager();
        Historico historico=null;
            Query q=em.createQuery("Select s1 from Historico s1 join Sensor s2 on s1.sensor = s2 where s2.tipo = :tipo ORDER by s1.fecha_y_hora")
                .setParameter("tipo", tipo);
        try {
            historico=(Historico)q.getSingleResult();
        } catch (NonUniqueResultException e) {
            historico=(Historico)q.getResultList().get(q.getResultList().size()-1);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            em.close();
            return historico;
        }
    }
    public ArrayList<Historico> procesarTipoPromedio(TipoSensor tipo){
        EntityManager em=entity_mf.createEntityManager();
        Date fin=new Date();
        Date inicio=sumarRestarHorasFecha(fin, -(tipo.getNumeroHoras()));
        
        Historico historico=null;
        ArrayList<Historico> h=new ArrayList<>();
            Query q=em.createQuery("Select s1 from Historico s1 join Sensor s2 on s1.sensor = s2 where (s2.tipo = :tipo) And s1.fecha_y_hora > :inicio And s1.fecha_y_hora< :fin ORDER by s1.fecha_y_hora")
                .setParameter("tipo", tipo)
                .setParameter("inicio",inicio)
                .setParameter("fin", fin);
        System.out.println(fin+"\n"+inicio  );  
        try {
            historico=(Historico)q.getSingleResult();
            h.add(historico);
        } catch (NonUniqueResultException e) {
            for(int i=q.getResultList().size()-1;i>=0;i--){
                historico=(Historico)q.getResultList().get(i);
                h.add(historico);
            }
            System.out.println(h.size()+"");
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            em.close();
            return h;
        }
    }
    // Suma o resta las horas recibidos a la fecha  
	
    public Date sumarRestarHorasFecha(Date fecha, int horas){
	
 
	
      Calendar calendar = Calendar.getInstance();
	
      calendar.setTime(fecha); // Configuramos la fecha que se recibe
	
      calendar.add(Calendar.HOUR, horas);  // numero de horas a añadir, o restar en caso de horas<0
	
      return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
	
    }
}
