/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZamaliev1_2;

/**
 *
 * @author danis_zam
 */
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.JButton;

public class JTableExample {

    private JButton back;
    private JButton clear;
    private JButton sortByName;
    private JButton sortByYear;
    private JButton sortByMusician;
    private JButton sortByGenre;

    JTable jTabPeople;

    JTableExample() {
        sortByGenre = new JButton();
        sortByGenre.setText("sortByGenre");
        back = new JButton();
        back.setText("back");
        clear = new JButton();
        clear.setText("clear");
        sortByName = new JButton();
        sortByName.setText("sortByName");
        sortByYear = new JButton();
        sortByYear.setText("sortByYear");
        sortByMusician = new JButton();
        sortByMusician.setText("sortByMusician");

        JFrame jfrm = new JFrame("ListOfTrack");
        jfrm.add(back);
        jfrm.add(sortByGenre);
        jfrm.add(clear);
        jfrm.add(sortByYear);
        jfrm.add(sortByName);
        jfrm.add(sortByMusician);
        jfrm.getContentPane().setLayout(new FlowLayout());
        jfrm.setSize(500, 300);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ListOfTrack arr = new ListOfTrack();
        MyTableModel table = new MyTableModel(arr.readAll());
        jTabPeople = new JTable(table);
        JScrollPane jscrlp = new JScrollPane(jTabPeople);
        jTabPeople.setPreferredScrollableViewportSize(new Dimension(350, 300));
        jfrm.getContentPane().add(jscrlp);
        jfrm.setVisible(true);
        
           sortByGenre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jfrm.setVisible(false);
                ListOfTrack list = new ListOfTrack();
                list.sortByGenre();
                JTableExample add = new JTableExample();

            }

        });

        sortByMusician.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jfrm.setVisible(false);
                ListOfTrack list = new ListOfTrack();
                list.sortByMusician();
                JTableExample add = new JTableExample();

            }

        });

        sortByYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jfrm.setVisible(false);
                ListOfTrack list = new ListOfTrack();
                list.sortByYear();
                JTableExample add = new JTableExample();

            }

        });

        sortByName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jfrm.setVisible(false);
                ListOfTrack list = new ListOfTrack();
                list.sortByName();
                JTableExample add = new JTableExample();

            }

        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jfrm.setVisible(false);
                new MyMusicPlayerUI().setVisible(true);
            }

        });
    }

    private void setVisible(boolean r) {
        this.setVisible(r);
    }

    //Функция main, запускающаяся при старте приложения
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
