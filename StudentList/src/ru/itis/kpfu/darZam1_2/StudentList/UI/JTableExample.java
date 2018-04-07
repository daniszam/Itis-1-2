/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.StudentList.UI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import ru.itis.kpfu.darZam1_2.StudentList.ListOfStudent;
import ru.itis.kpfu.darZam1_2.StudentList.Student;

/**
 *
 * @author danis_zam
 */
public class JTableExample {
    private JButton back;
    private ListOfStudent list;
    private JTable studentTab;
            
    public JTableExample(){
        back = new JButton();
        back.setText("back");
        
         JFrame jfrm = new JFrame("Students");
         jfrm.add(back);
         
        jfrm.getContentPane().setLayout(new FlowLayout());
        jfrm.setSize(500, 300);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        list = new ListOfStudent();
        MyTableModel table = new MyTableModel(list.readListOfStudent());
        studentTab = new JTable(table);
        JScrollPane jscrlp = new JScrollPane(studentTab);
        studentTab.setPreferredScrollableViewportSize(new Dimension(350, 300));
        jfrm.getContentPane().add(jscrlp);
        jfrm.setVisible(true);
        
        
        
         back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //    jfrm.setVisible(false);
                jfrm.dispose();
                JframeWindow jfw = new JframeWindow();
                jfw.setVisible(true);
            }

        });
    }
    
     private void setVisible(boolean r) {
        this.setVisible(r);
    }
     
     public static void main(String[] args) {
        //Создаем фрейм в потоке обработки событий
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JTableExample();
            }
        });
}
}
