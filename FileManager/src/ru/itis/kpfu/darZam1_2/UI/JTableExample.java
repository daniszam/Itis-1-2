/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.UI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.Paths;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import ru.kpfu.itis.darZam1_2.FileManager;

/**
 *
 * @author danis_zam
 */
public class JTableExample {

    private JTable fileTab;
    private JButton back;
    private MyTabelModel table;
    private FileManager arr;
    private JScrollPane jscrlp;

    public JTableExample() {

        back = new JButton();
        back.setText("back");

        JFrame jfrm = new JFrame("FileManager");
        jfrm.add(back);
        jfrm.getContentPane().setLayout(new FlowLayout());
        jfrm.setSize(820, 500);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        arr = new FileManager();
        table = new MyTabelModel(arr.getFiles());
        fileTab = new JTable(table);
        jscrlp = new JScrollPane(fileTab);
        fileTab.setPreferredScrollableViewportSize(new Dimension(800, 410));
        jfrm.getContentPane().add(jscrlp);
        jfrm.setTitle("danisZamFileManager");
        jfrm.setVisible(true);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 
                if (!arr.getDirectory().toString().equals(System.getProperty("file.separator"))) {
                    arr.setDirectory(arr.getDirectory().resolve("..").normalize());
                    table = new MyTabelModel(arr.setFiles());
                    fileTab.setModel(table);
                } else {
                    JOptionPane.showMessageDialog(new JOptionPane(), "It's the root folder");
                }
            }

        });
        fileTab.getTableHeader().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    int colum = fileTab.columnAtPoint(e.getPoint());
                    switch (colum) {
                        case 0:
                            arr.sortByName();
                            table = new MyTabelModel(arr.getFiles());
                            fileTab.setModel(table);
                            break;
                        case 1:
                            arr.sortByExtension();
                            table = new MyTabelModel(arr.getFiles());
                            fileTab.setModel(table);
                             break;
                        case 2:
                            arr.sortBySize();
                            table = new MyTabelModel(arr.getFiles());
                            fileTab.setModel(table);
                             break;
                        case 3:
                            arr.sortByDate();
                            table = new MyTabelModel(arr.getFiles());
                            fileTab.setModel(table);
                             break;
                        case 4:
                            arr.sortByDirectory();
                            table = new MyTabelModel(arr.getFiles());
                            fileTab.setModel(table);
                             break;
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });

        fileTab.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON3) {
                    int row = fileTab.rowAtPoint(event.getPoint());
                    arr.setDirectory(arr.getFiles().get(row).toPath());
                    table = new MyTabelModel(arr.setFiles());
                    fileTab.setModel(table);
                }
            }

            @Override
            public void mouseClicked(MouseEvent event) {
               
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

    }

    private void setVisible(boolean r) {
        this.setVisible(r);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JTableExample();
            }
        });

    }
}
