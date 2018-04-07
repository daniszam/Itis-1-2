/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.StudentList.UI;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import ru.itis.kpfu.darZam1_2.StudentList.Student;

/**
 *
 * @author danis_zam
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author danis_zam
 */
public class MyTableModel extends AbstractTableModel {
    private ArrayList<Student> students;

    public MyTableModel(ArrayList<Student> arr){
        super();
        this.students = arr;
    }
    
    @Override
    public int getRowCount() {
        return this.students.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    
    public String getColumnName(int c) {
        String result = "";
        switch (c) {
            case 0:
                result = "Name";
                break;
            case 1:
                result = "Gender";
                break;
            case 2:
                result = "Age";
                break;
                 case 3:
                result = "AverageBall";
                break;  
        }
        return result;
    }
    
    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return students.get(r).getName();
            case 1:
                return students.get(r).getGender();
            case 2:
                return students.get(r).getBirthday();
            case 3:
                return students.get(r).getAverageBall();
            default:
                return "";
        }
    }

}


