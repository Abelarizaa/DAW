package es.ejercicioclasepruebas;



import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;


public class Principal extends JApplet {
 
 JButton cuadros[][];
 JComboBox comboTamaño;
 String tam[]={"3","5","7","9"};
 JPanel pcentro;
 JButton btncomenzar;
 
 public void init(){
  
  colocarSkin();
  JPanel parriba=new JPanel();
  parriba.add(new JLabel("Tamaño: "));
  comboTamaño=new JComboBox(tam);
  comboTamaño.addItemListener(new ItemListener(){


   @Override
   public void itemStateChanged(ItemEvent e) {
    if(e.getStateChange()==ItemEvent.SELECTED){
     pcentro.removeAll();
     cuadros=new JButton[Integer.parseInt(comboTamaño.getSelectedItem().toString())][Integer.parseInt(comboTamaño.getSelectedItem().toString())];
     pcentro.setLayout(new GridLayout(cuadros.length,cuadros.length,2,2));
     for(int i=0;i<cuadros.length;i++){
      for(int j=0;j<cuadros.length;j++){
       cuadros[i][j]=new JButton();
       pcentro.add(cuadros[i][j]);
      }
     }
     pcentro.updateUI();
     repaint();
    }
   }
  });
  parriba.add(comboTamaño);
  
  btncomenzar=new JButton("Comenzar");
  btncomenzar.addActionListener(new ActionListener(){


   @Override
   public void actionPerformed(ActionEvent e) {
    HiloEscritor he=new HiloEscritor(cuadros,Integer.parseInt(comboTamaño.getSelectedItem().toString()));
    he.start();
   }
   
  });
  parriba.add(btncomenzar);
  
  pcentro=new JPanel();
  
  add(parriba,BorderLayout.NORTH);
  add(pcentro,BorderLayout.CENTER);
  
 }
 
 public void colocarSkin(){
  try {
    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
   } catch (ClassNotFoundException e) {
    e.printStackTrace();
   } catch (InstantiationException e) {
    e.printStackTrace();
   } catch (IllegalAccessException e) {
    e.printStackTrace();
   } catch (UnsupportedLookAndFeelException e) {
    e.printStackTrace();
   }
 }
}

Clase HiloEscritor

package Clases;

import javax.swing.JButton;

public class HiloEscritor extends Thread {
 
 JButton cuadros[][];
 int n;
 
 public HiloEscritor(JButton c[][],int n){
  cuadros=c;
  this.n=n;
 }
 
 public void run(){
  int cont=1;
  int cuad=n*n;
  int posL=0;
  int posC=n/2;
  while ( cont<=cuad ) {
         cuadros[posL][posC].setText(""+cont);
         try {
    Thread.sleep(500);
   } catch (InterruptedException e1) {
    // TODO Auto-generated catch block
    e1.printStackTrace();
   }
         if ( cont%n == 0 ) {
             posL++;
         } else {
             posL--;
             if ( posL < 0 ) posL = n-1;
             posC++;
             if ( posC > n-1 ) posC = 0;
         }
         cont++;
     }
 }
}
