/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.SolicitudBean;
import util.FuncionesUtiles;

/**
 *
 * @author b586854
 */
public class SolicitudArchivo extends javax.swing.JFrame {

    ArrayList<SolicitudBean> _matrixSolicitud = null;

    /**
     * Creates new form Solicitud
     */
    public SolicitudArchivo() {

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

        jLabelDatosSolicitud = new javax.swing.JLabel();
        jLabelMontoSolicitado = new javax.swing.JLabel();
        jLabelFechaEstiLiqu = new javax.swing.JLabel();
        jLabelVigenciaTasa = new javax.swing.JLabel();
        jLabelDestino = new javax.swing.JLabel();
        jLabelAmortizacion = new javax.swing.JLabel();
        destino = new javax.swing.JComboBox<String>();
        jLabelPlazoMeses = new javax.swing.JLabel();
        jLabelCartera = new javax.swing.JLabel();
        cartera = new javax.swing.JComboBox<String>();
        jLabelTasa = new javax.swing.JLabel();
        jLabelComision = new javax.swing.JLabel();
        jLabelEscenario = new javax.swing.JLabel();
        escenario = new javax.swing.JComboBox<String>();
        jButtonVentas = new javax.swing.JButton();
        jButtonDeudas = new javax.swing.JButton();
        jButtonBeneficio = new javax.swing.JButton();
        jButtonParametros = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();
        jButtonCarga = new javax.swing.JButton();
        montoSolicitado = new javax.swing.JTextField();
        vigenciaTasa = new javax.swing.JTextField();
        plazoMeses = new javax.swing.JTextField();
        tasa = new javax.swing.JTextField();
        comision = new javax.swing.JTextField();
        amort = new javax.swing.JComboBox<String>();
        fechaEstimadaLiq = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Solicitud");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabelDatosSolicitud.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelDatosSolicitud.setText("Datos de Solictud");
        getContentPane().add(jLabelDatosSolicitud);
        jLabelDatosSolicitud.setBounds(31, 11, 196, 29);

        jLabelMontoSolicitado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelMontoSolicitado.setText("Monto Solicitado");
        getContentPane().add(jLabelMontoSolicitado);
        jLabelMontoSolicitado.setBounds(52, 82, 100, 17);

        jLabelFechaEstiLiqu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelFechaEstiLiqu.setText("Fecha Estimada de Liquidación");
        getContentPane().add(jLabelFechaEstiLiqu);
        jLabelFechaEstiLiqu.setBounds(52, 130, 185, 17);

        jLabelVigenciaTasa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelVigenciaTasa.setText("Vigencia de la Tasa");
        getContentPane().add(jLabelVigenciaTasa);
        jLabelVigenciaTasa.setBounds(52, 171, 113, 17);

        jLabelDestino.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelDestino.setText("Destino");
        getContentPane().add(jLabelDestino);
        jLabelDestino.setBounds(52, 262, 46, 17);

        jLabelAmortizacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelAmortizacion.setText("Amortización");
        getContentPane().add(jLabelAmortizacion);
        jLabelAmortizacion.setBounds(52, 220, 78, 17);

        destino.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        destino.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Activo Fijo", "Capital Trabajo" }));
        destino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destinoActionPerformed(evt);
            }
        });
        getContentPane().add(destino);
        destino.setBounds(255, 262, 142, 23);

        jLabelPlazoMeses.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelPlazoMeses.setText("Plazo Meses");
        getContentPane().add(jLabelPlazoMeses);
        jLabelPlazoMeses.setBounds(447, 82, 71, 17);

        jLabelCartera.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelCartera.setText("Cartera");
        getContentPane().add(jLabelCartera);
        jLabelCartera.setBounds(447, 132, 45, 17);

        cartera.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cartera.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Comercial", "Manufactura Pymi", "Manufactura" }));
        cartera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carteraActionPerformed(evt);
            }
        });
        getContentPane().add(cartera);
        cartera.setBounds(528, 130, 142, 23);

        jLabelTasa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTasa.setText("Tasa");
        getContentPane().add(jLabelTasa);
        jLabelTasa.setBounds(447, 171, 28, 17);

        jLabelComision.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelComision.setText("Comisión");
        getContentPane().add(jLabelComision);
        jLabelComision.setBounds(447, 220, 55, 17);

        jLabelEscenario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelEscenario.setText("Escenario");
        getContentPane().add(jLabelEscenario);
        jLabelEscenario.setBounds(447, 264, 58, 17);

        escenario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        escenario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Conservador", "Moderado", "Parametizable" }));
        getContentPane().add(escenario);
        escenario.setBounds(528, 262, 140, 23);

        jButtonVentas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonVentas.setText("Ventas");
        jButtonVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVentasActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonVentas);
        jButtonVentas.setBounds(707, 132, 99, 25);

        jButtonDeudas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonDeudas.setText("Deudas");
        jButtonDeudas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonDeudasMouseClicked(evt);
            }
        });
        jButtonDeudas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeudasActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonDeudas);
        jButtonDeudas.setBounds(707, 89, 99, 25);

        jButtonBeneficio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonBeneficio.setText("Beneficio");
        jButtonBeneficio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBeneficioActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonBeneficio);
        jButtonBeneficio.setBounds(707, 175, 99, 25);

        jButtonParametros.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonParametros.setText("Parametros");
        jButtonParametros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonParametrosActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonParametros);
        jButtonParametros.setBounds(707, 218, 99, 25);

        jButtonSalir.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonSalir.setText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSalir);
        jButtonSalir.setBounds(707, 264, 99, 25);

        jButtonCarga.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonCarga.setText("Cargar");
        jButtonCarga.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCargaMouseClicked(evt);
            }
        });
        jButtonCarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCargaActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCarga);
        jButtonCarga.setBounds(255, 27, 99, 25);
        getContentPane().add(montoSolicitado);
        montoSolicitado.setBounds(255, 82, 142, 20);
        getContentPane().add(vigenciaTasa);
        vigenciaTasa.setBounds(255, 171, 142, 20);
        getContentPane().add(plazoMeses);
        plazoMeses.setBounds(528, 82, 142, 20);
        getContentPane().add(tasa);
        tasa.setBounds(528, 175, 142, 20);
        getContentPane().add(comision);
        comision.setBounds(528, 221, 140, 20);

        amort.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        amort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Mensual", "Bimestral", "Trimestral", "Cuatrimestral", "Semestral" }));
        amort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amortActionPerformed(evt);
            }
        });
        getContentPane().add(amort);
        amort.setBounds(255, 221, 142, 23);
        getContentPane().add(fechaEstimadaLiq);
        fechaEstimadaLiq.setBounds(255, 130, 142, 20);

        setBounds(0, 0, 834, 342);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDeudasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeudasActionPerformed

        if (FuncionesUtiles.isVacio(montoSolicitado.getText())) {
            JOptionPane.showMessageDialog(null, "El monto no puede ser vacia");

        } else if (!FuncionesUtiles.isNumerico(montoSolicitado.getText())) {
            JOptionPane.showMessageDialog(null, "El monto debe ser numerico");

        } else if (FuncionesUtiles.isVacio(fechaEstimadaLiq.getText())) {
            JOptionPane.showMessageDialog(null, "La fecha no puede ser vacia");

        } else if (!FuncionesUtiles.isFormatoFecha(fechaEstimadaLiq.getText())) {
            JOptionPane.showMessageDialog(null, "La fecha debe contener el siguiente formato ##/##/####");

        } else if (FuncionesUtiles.isVacio(vigenciaTasa.getText())) {
            JOptionPane.showMessageDialog(null, "La Vigencia de la tasa no puede ser vacia");

        } else if (!FuncionesUtiles.isNumerico(vigenciaTasa.getText())) {
            JOptionPane.showMessageDialog(null, "La vigencia de la tasa debe ser númerico");

        } else if (FuncionesUtiles.isVacio(plazoMeses.getText())) {
            JOptionPane.showMessageDialog(null, "El plazo no puede ser vacia");

        } else if (!FuncionesUtiles.isNumerico(plazoMeses.getText())) {
            JOptionPane.showMessageDialog(null, "El plazo debe ser númerico");

        } else if (FuncionesUtiles.isVacio(tasa.getText())) {
            JOptionPane.showMessageDialog(null, "La tasa no puede ser vacia");

        } else if (!FuncionesUtiles.isNumerico(tasa.getText())) {
            JOptionPane.showMessageDialog(null, "La tasa debe ser númerico");

        } else if (FuncionesUtiles.isVacio(comision.getText())) {
            JOptionPane.showMessageDialog(null, "La comisión no puede ser vacia");

        } else if (!FuncionesUtiles.isNumerico(comision.getText())) {
            JOptionPane.showMessageDialog(null, "La comisión debe ser númerico");

        } else {
         
            Deudas deudas = new Deudas(_matrixSolicitud);
            deudas.setLocationRelativeTo(null);
            deudas.setVisible(true);
            this.setVisible(false);
        }


    }//GEN-LAST:event_jButtonDeudasActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        // TODO add your handling code here:

        System.exit(0);
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jButtonDeudasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDeudasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonDeudasMouseClicked

    private void jButtonCargaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCargaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCargaMouseClicked

    private void jButtonCargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCargaActionPerformed

        String linea;
        String[] datosSolicitud;

        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader("C:\\PConceptoCapacidad\\archivos\\ArchivoCargaDatosSolicitud.txt"));
            try {
                _matrixSolicitud = new ArrayList<SolicitudBean>();
                while ((linea = bf.readLine()) != null) {

                    datosSolicitud = linea.split(",");

                    _matrixSolicitud.add(new SolicitudBean(datosSolicitud[0], datosSolicitud[1], datosSolicitud[2], datosSolicitud[3], datosSolicitud[4], datosSolicitud[5], datosSolicitud[6], datosSolicitud[7], datosSolicitud[8], datosSolicitud[9]));
                }
                for (int i = 0; i < _matrixSolicitud.size(); i++) {

                    //montoSolicitado.setText(String.valueOf(_matrixSolicitud.get(i).getMontoSolicitado()));
                    montoSolicitado.setText(FuncionesUtiles.formatoDouble(_matrixSolicitud.get(i).getMontoSolicitado()));
                    fechaEstimadaLiq.setText(_matrixSolicitud.get(i).getFechaEstLiq());
                    vigenciaTasa.setText(String.valueOf(_matrixSolicitud.get(i).getVigenciaTasa()));
                    cartera.setSelectedIndex(_matrixSolicitud.get(i).getCartera());
                    plazoMeses.setText(String.valueOf(_matrixSolicitud.get(i).getPlazoMeses()));
                    tasa.setText(String.valueOf(_matrixSolicitud.get(i).getTasa()));
                    comision.setText(String.valueOf(_matrixSolicitud.get(i).getComisionFlat()));
                    amort.setSelectedIndex(_matrixSolicitud.get(i).getAmortizaciones());
                    escenario.setSelectedIndex(_matrixSolicitud.get(i).getEscenario());
                    destino.setSelectedIndex(_matrixSolicitud.get(i).getDestino());

                }

            } catch (IOException ex) {
                Logger.getLogger(Deudas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NumberFormatException nb) {
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Deudas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bf.close();
            } catch (IOException ex) {
                Logger.getLogger(Deudas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jButtonCargaActionPerformed

    private void jButtonVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVentasActionPerformed
        if (FuncionesUtiles.isVacio(montoSolicitado.getText())) {
            JOptionPane.showMessageDialog(null, "El monto no puede ser vacia");

        } else if (!FuncionesUtiles.isNumerico(montoSolicitado.getText())) {
            JOptionPane.showMessageDialog(null, "El monto debe ser numerico");

        } else if (FuncionesUtiles.isVacio(fechaEstimadaLiq.getText())) {
            JOptionPane.showMessageDialog(null, "La fecha no puede ser vacia");

        } else if (!FuncionesUtiles.isFormatoFecha(fechaEstimadaLiq.getText())) {
            JOptionPane.showMessageDialog(null, "La fecha debe contener el siguiente formato ##/##/####");

        } else if (FuncionesUtiles.isVacio(vigenciaTasa.getText())) {
            JOptionPane.showMessageDialog(null, "La Vigencia de la tasa no puede ser vacia");

        } else if (!FuncionesUtiles.isNumerico(vigenciaTasa.getText())) {
            JOptionPane.showMessageDialog(null, "La vigencia de la tasa debe ser númerico");

        } else if (FuncionesUtiles.isVacio(plazoMeses.getText())) {
            JOptionPane.showMessageDialog(null, "El plazo no puede ser vacia");

        } else if (!FuncionesUtiles.isNumerico(plazoMeses.getText())) {
            JOptionPane.showMessageDialog(null, "El plazo debe ser númerico");

        } else if (FuncionesUtiles.isVacio(tasa.getText())) {
            JOptionPane.showMessageDialog(null, "La tasa no puede ser vacia");

        } else if (!FuncionesUtiles.isNumerico(tasa.getText())) {
            JOptionPane.showMessageDialog(null, "La tasa debe ser númerico");

        } else if (FuncionesUtiles.isVacio(comision.getText())) {
            JOptionPane.showMessageDialog(null, "La comisión no puede ser vacia");

        } else if (!FuncionesUtiles.isNumerico(comision.getText())) {
            JOptionPane.showMessageDialog(null, "La comisión debe ser númerico");

        } else {
            Ventas ventas = new Ventas();
            ventas.setLocationRelativeTo(null);
            ventas.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_jButtonVentasActionPerformed

    private void jButtonBeneficioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBeneficioActionPerformed
        if (FuncionesUtiles.isVacio(montoSolicitado.getText())) {
            JOptionPane.showMessageDialog(null, "El monto no puede ser vacia");

        } else if (!FuncionesUtiles.isNumerico(montoSolicitado.getText())) {
            JOptionPane.showMessageDialog(null, "El monto debe ser numerico");

        } else if (FuncionesUtiles.isVacio(fechaEstimadaLiq.getText())) {
            JOptionPane.showMessageDialog(null, "La fecha no puede ser vacia");

        } else if (!FuncionesUtiles.isFormatoFecha(fechaEstimadaLiq.getText())) {
            JOptionPane.showMessageDialog(null, "La fecha debe contener el siguiente formato ##/##/####");

        } else if (FuncionesUtiles.isVacio(vigenciaTasa.getText())) {
            JOptionPane.showMessageDialog(null, "La Vigencia de la tasa no puede ser vacia");

        } else if (!FuncionesUtiles.isNumerico(vigenciaTasa.getText())) {
            JOptionPane.showMessageDialog(null, "La vigencia de la tasa debe ser númerico");

        } else if (FuncionesUtiles.isVacio(plazoMeses.getText())) {
            JOptionPane.showMessageDialog(null, "El plazo no puede ser vacia");

        } else if (!FuncionesUtiles.isNumerico(plazoMeses.getText())) {
            JOptionPane.showMessageDialog(null, "El plazo debe ser númerico");

        } else if (FuncionesUtiles.isVacio(tasa.getText())) {
            JOptionPane.showMessageDialog(null, "La tasa no puede ser vacia");

        } else if (!FuncionesUtiles.isNumerico(tasa.getText())) {
            JOptionPane.showMessageDialog(null, "La tasa debe ser númerico");

        } else if (FuncionesUtiles.isVacio(comision.getText())) {
            JOptionPane.showMessageDialog(null, "La comisión no puede ser vacia");

        } else if (!FuncionesUtiles.isNumerico(comision.getText())) {
            JOptionPane.showMessageDialog(null, "La comisión debe ser númerico");

        } else {
            Beneficio beneficios = new Beneficio();
            beneficios.setLocationRelativeTo(null);
            beneficios.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_jButtonBeneficioActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void carteraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carteraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_carteraActionPerformed

    private void amortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amortActionPerformed

    private void destinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_destinoActionPerformed

    private void jButtonParametrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonParametrosActionPerformed
          /* if (FuncionesUtiles.isVacio(montoSolicitado.getText())) {
            JOptionPane.showMessageDialog(null, "El monto no puede ser vacia");

        } else if (!FuncionesUtiles.isNumerico(montoSolicitado.getText())) {
            JOptionPane.showMessageDialog(null, "El monto debe ser numerico");

        } else if (FuncionesUtiles.isVacio(fechaEstimadaLiq.getText())) {
            JOptionPane.showMessageDialog(null, "La fecha no puede ser vacia");

        } else if (!FuncionesUtiles.isFormatoFecha(fechaEstimadaLiq.getText())) {
            JOptionPane.showMessageDialog(null, "La fecha debe contener el siguiente formato ##/##/####");

        } else if (FuncionesUtiles.isVacio(vigenciaTasa.getText())) {
            JOptionPane.showMessageDialog(null, "La Vigencia de la tasa no puede ser vacia");

        } else if (!FuncionesUtiles.isNumerico(vigenciaTasa.getText())) {
            JOptionPane.showMessageDialog(null, "La vigencia de la tasa debe ser númerico");

        } else if (FuncionesUtiles.isVacio(plazoMeses.getText())) {
            JOptionPane.showMessageDialog(null, "El plazo no puede ser vacia");

        } else if (!FuncionesUtiles.isNumerico(plazoMeses.getText())) {
            JOptionPane.showMessageDialog(null, "El plazo debe ser númerico");

        } else if (FuncionesUtiles.isVacio(tasa.getText())) {
            JOptionPane.showMessageDialog(null, "La tasa no puede ser vacia");

        } else if (!FuncionesUtiles.isNumerico(tasa.getText())) {
            JOptionPane.showMessageDialog(null, "La tasa debe ser númerico");

        } else if (FuncionesUtiles.isVacio(comision.getText())) {
            JOptionPane.showMessageDialog(null, "La comisión no puede ser vacia");

        } else if (!FuncionesUtiles.isNumerico(comision.getText())) {
            JOptionPane.showMessageDialog(null, "La comisión debe ser númerico");

        } else {*/
            Parametros ScrParametros= new Parametros();
            ScrParametros.setLocationRelativeTo(null);
            ScrParametros.setVisible(true);
            this.setVisible(false);
       /* }*/
       
    }//GEN-LAST:event_jButtonParametrosActionPerformed

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
            java.util.logging.Logger.getLogger(SolicitudArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SolicitudArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SolicitudArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SolicitudArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SolicitudArchivo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> amort;
    private javax.swing.JComboBox<String> cartera;
    private javax.swing.JTextField comision;
    public static javax.swing.JComboBox<String> destino;
    private javax.swing.JComboBox<String> escenario;
    private javax.swing.JTextField fechaEstimadaLiq;
    private javax.swing.JButton jButtonBeneficio;
    private javax.swing.JButton jButtonCarga;
    private javax.swing.JButton jButtonDeudas;
    private javax.swing.JButton jButtonParametros;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JButton jButtonVentas;
    private javax.swing.JLabel jLabelAmortizacion;
    private javax.swing.JLabel jLabelCartera;
    private javax.swing.JLabel jLabelComision;
    private javax.swing.JLabel jLabelDatosSolicitud;
    private javax.swing.JLabel jLabelDestino;
    private javax.swing.JLabel jLabelEscenario;
    private javax.swing.JLabel jLabelFechaEstiLiqu;
    private javax.swing.JLabel jLabelMontoSolicitado;
    private javax.swing.JLabel jLabelPlazoMeses;
    private javax.swing.JLabel jLabelTasa;
    private javax.swing.JLabel jLabelVigenciaTasa;
    private javax.swing.JTextField montoSolicitado;
    private javax.swing.JTextField plazoMeses;
    private javax.swing.JTextField tasa;
    private javax.swing.JTextField vigenciaTasa;
    // End of variables declaration//GEN-END:variables
}
