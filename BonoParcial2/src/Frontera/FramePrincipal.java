/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontera;

import Entidad.TipoSensor;
import DAO.DatoDAO;
import Datos.Historico;
import Entidad.Sensor;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author crist
 */
public class FramePrincipal extends javax.swing.JFrame {
    //Referenciando los paneles de enviar y mostrar
    
    
    private Enviar enviar=new Enviar();
    private Mostrar mostrar=new Mostrar();
    private Procesamiento procesar=new Procesamiento();
    
    /**
     * Creates new form FramePrincipal
     */
    public FramePrincipal() {
        initComponents();
        inicializar();
        this.setTitle("Fotoresistor");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        enviarBt = new javax.swing.JButton();
        mostrarBt = new javax.swing.JButton();
        procesarBt = new javax.swing.JButton();
        panelPrincipal = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        enviarBt.setText("Enviar dato");
        enviarBt.setActionCommand("EnviarDato");
        enviarBt.setFocusable(false);
        enviarBt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        enviarBt.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        enviarBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarBtActionPerformed(evt);
            }
        });
        jToolBar1.add(enviarBt);

        mostrarBt.setText("Mostrar datos");
        mostrarBt.setFocusable(false);
        mostrarBt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mostrarBt.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mostrarBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarBtActionPerformed(evt);
            }
        });
        jToolBar1.add(mostrarBt);

        procesarBt.setText("Procesar datos");
        procesarBt.setFocusable(false);
        procesarBt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        procesarBt.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        procesarBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                procesarBtActionPerformed(evt);
            }
        });
        jToolBar1.add(procesarBt);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        panelPrincipal.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enviarBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarBtActionPerformed
        panelPrincipal.setVisible(false);
        panelPrincipal.removeAll();
        panelPrincipal.add(enviar);
        
        panelPrincipal.setVisible(true);
    }//GEN-LAST:event_enviarBtActionPerformed

    private void mostrarBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarBtActionPerformed
        panelPrincipal.setVisible(false);
        panelPrincipal.removeAll();
        panelPrincipal.add(mostrar);
        
        
        
        //comprobando el funcionamiento de la tabla y la base, solo son casos de prueba
        String [] Titulos={"ID SENSOR","VALOR TOMADO","FECHA Y HORA DE TOMA"};
        DatoDAO dao=new DatoDAO();
        
        ArrayList<Historico> objects=dao.listar();
        Object [][]object;
        System.out.println(objects.size()+"");
        if(objects.size()>0){
            object=new Object[5][3];
            
            for(int i=0;i<objects.size();i++){
                Date date=objects.get(i).getFecha_y_hora();
                String fecha=date.getDay()+"/"+date.getMonth()+"/"+date.getYear()+"    "+date.getHours()+":"+date.getMinutes();
                object[i][0]=objects.get(i).getSensor().getId();
                object[i][1]=objects.get(i).getValor();
                object[i][2]=objects.get(i).getFecha_y_hora().toString();
                
                //object[i][2]=fecha;
            }
            
        }else{
            object=new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            };
        }
        mostrar.getTabla().setModel(new javax.swing.table.DefaultTableModel(object,Titulos));
        panelPrincipal.setVisible(true);
    }//GEN-LAST:event_mostrarBtActionPerformed

    private void procesarBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_procesarBtActionPerformed
        panelPrincipal.setVisible(false);
        panelPrincipal.removeAll();
        panelPrincipal.add(procesar);
        panelPrincipal.setVisible(true);
    }//GEN-LAST:event_procesarBtActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePrincipal().setVisible(true);
            }
        });
    }
    
    
    public void inicializar(){
        //Lista de datos para el sensor
        DatoDAO dao=new DatoDAO();
        
        // Creando el tipo sensor
         
        
        /*
        TipoSensor tipo=new TipoSensor();
        tipo.setTipo("FTR");
        tipo.setNombre("Fotoresistor");
        tipo.setMinimoPermitido(5000);//5 kilo ohmios
        tipo.setMaximoPermitido(10000000);// 10 mega ohmios
        tipo.setPromedio(false);
        tipo.setNumeroHoras(0);
        
        
        dao.crearTipoSensor(tipo); 
        
        
        //creando datos de sensores
        
         
         
        Sensor sensor=new Sensor();
        sensor.setTipo(tipo);
        
        String []ubicaciones={"Mi casa","Tu casa","la otra casa"};
        
        for(int i=0;i<10;i++){
            sensor.setUbicacion(ubicaciones[i%ubicaciones.length]);
            
            dao.crearSensor(sensor);
        }
         
        
        */
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton enviarBt;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton mostrarBt;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JButton procesarBt;
    // End of variables declaration//GEN-END:variables
}
