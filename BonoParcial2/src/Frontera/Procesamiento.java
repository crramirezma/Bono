/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontera;

import DAO.DatoDAO;
import Datos.Historico;
import Datos.ValidarDatos;
import Entidad.TipoSensor;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author crist
 */
public class Procesamiento extends javax.swing.JPanel {
    DatoDAO dao=new DatoDAO();
    ValidarDatos validar=new ValidarDatos();
    
    /**
     * Creates new form Procesamiento
     */
    public Procesamiento() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TipoField = new javax.swing.JTextField();
        procesarBt = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        procesarBt.setText("Procesar");
        procesarBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                procesarBtActionPerformed(evt);
            }
        });

        jLabel1.setText("Tipo de sensor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(TipoField)
                    .addComponent(procesarBt, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                .addContainerGap(176, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TipoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(procesarBt)
                .addContainerGap(150, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void procesarBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_procesarBtActionPerformed
        String tipo;
        
        TipoSensor tip;
        try{
            tipo=TipoField.getText().toString();
            if(tipo.length()==0)
                throw new Exception();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ingrese valores porfavor","Corrija sus errores",JOptionPane.ERROR_MESSAGE);
            return;
        }
        System.out.println(tipo);
        tip=dao.leerTipo(tipo);
        ArrayList<Historico> h=dao.procesarTipo(tip);
        switch(validar.verificarProcesamiento(h)){
            case 1:
                JOptionPane.showMessageDialog(null, "Su sensor es inferior a los limites","Ponga atencion",JOptionPane.ERROR_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "Su sensor esta trabajando correctamente","",JOptionPane.INFORMATION_MESSAGE);
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Su sensor es superior a los limites","Ponga atencion",JOptionPane.ERROR_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Error","Error",JOptionPane.ERROR_MESSAGE);
              
                break;
        }
        
        //System.out.println(h.get(0).getValor());
            
    }//GEN-LAST:event_procesarBtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TipoField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton procesarBt;
    // End of variables declaration//GEN-END:variables
}
