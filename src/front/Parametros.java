/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package front;

import javax.swing.JLabel;

/**
 *
 * @author b586854
 */
public class Parametros extends javax.swing.JFrame {

    /**
     * Creates new form Parametros
     */
    public Parametros() {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        _2013Inf = new javax.swing.JTextField();
        _2014Inf = new javax.swing.JTextField();
        _2015Inf = new javax.swing.JTextField();
        _2016Inf = new javax.swing.JTextField();
        _2017Inf = new javax.swing.JTextField();
        _2018Inf = new javax.swing.JTextField();
        _2019Inf = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        _VentasMaxPerm = new javax.swing.JTextField();
        _VentasMaxMod = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Tabla de Parametros");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 162, 22);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel3.setText("2013");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(140, 60, 20, 13);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel4.setText("2014");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(190, 60, 20, 13);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel5.setText("2015");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(240, 60, 20, 13);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel6.setText("2016");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(290, 60, 20, 13);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel7.setText("2017");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(340, 60, 20, 13);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel8.setText("2018");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(390, 60, 34, 13);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel9.setText("2019");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(440, 60, 34, 13);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel10.setText("Inflación Promedio");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(10, 90, 85, 13);

        _2013Inf.setText("38,50");
        _2013Inf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _2013InfActionPerformed(evt);
            }
        });
        getContentPane().add(_2013Inf);
        _2013Inf.setBounds(130, 90, 40, 20);

        _2014Inf.setText("57,30");
        _2014Inf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _2014InfActionPerformed(evt);
            }
        });
        getContentPane().add(_2014Inf);
        _2014Inf.setBounds(180, 90, 40, 20);

        _2015Inf.setText("113,40");
        getContentPane().add(_2015Inf);
        _2015Inf.setBounds(230, 90, 40, 20);

        _2016Inf.setText("201,1");
        getContentPane().add(_2016Inf);
        _2016Inf.setBounds(290, 90, 34, 20);

        _2017Inf.setText("172,8");
        getContentPane().add(_2017Inf);
        _2017Inf.setBounds(340, 90, 34, 20);

        _2018Inf.setText("98,2");
        getContentPane().add(_2018Inf);
        _2018Inf.setBounds(390, 90, 28, 20);

        _2019Inf.setText("98,3");
        getContentPane().add(_2019Inf);
        _2019Inf.setBounds(440, 90, 30, 20);

        jLabel2.setText("Ventas Maximas Conservador");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 160, 190, 14);

        jLabel11.setText("Ventas Maximas Moderado");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(10, 190, 160, 14);

        _VentasMaxPerm.setText("30");
        getContentPane().add(_VentasMaxPerm);
        _VentasMaxPerm.setBounds(220, 160, 30, 20);

        _VentasMaxMod.setText("50");
        getContentPane().add(_VentasMaxMod);
        _VentasMaxMod.setBounds(220, 190, 30, 20);

        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(460, 290, 75, 23);

        setBounds(0, 0, 559, 371);
    }// </editor-fold>//GEN-END:initComponents

    private void _2013InfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__2013InfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event__2013InfActionPerformed

    private void _2014InfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__2014InfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event__2014InfActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
   
      SolicitudArchivo solicitudArchivo = new SolicitudArchivo();
      solicitudArchivo.setLocationRelativeTo(null);
      solicitudArchivo.setVisible(true);
      this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Parametros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Parametros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Parametros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Parametros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            new Parametros().setVisible(true);
            }
        });
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {  
    
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField _2013Inf;
    public static javax.swing.JTextField _2014Inf;
    public static javax.swing.JTextField _2015Inf;
    public static javax.swing.JTextField _2016Inf;
    public static javax.swing.JTextField _2017Inf;
    public static javax.swing.JTextField _2018Inf;
    public static javax.swing.JTextField _2019Inf;
    public static javax.swing.JTextField _VentasMaxMod;
    public static javax.swing.JTextField _VentasMaxPerm;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}