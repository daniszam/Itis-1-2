/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.UI;

import java.io.File;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author danis_zam
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.AbstractTableModel;
//import static ru.itis.kpfu.darZam1_2.UI.MyTabelModel.directorySize;

/**
 *
 * @author danis_zam
 */
public class MyTabelModel extends AbstractTableModel {

    private List<File> tFiles;

    public MyTabelModel(ArrayList<File> files) {
        super();
        this.tFiles = files;
    }

    @Override
    public int getRowCount() {
        return this.tFiles.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    public String getColumnName(int c) {
        String result = "";
        switch (c) {
            case 0:
                result = "Name";
                break;
            case 1:
                result = "Extension";
                break;
            case 2:
                result = "Size";
                break;
            case 3:
                result = "Date";
                break;
            case 4:
                result = "isDirectory";
                break;
        }
        return result;
    }
    
    
 /*public static long directorySize(File directory){
        long length = 0;
        for (File item :directory.listFiles()){
            if(item.isFile()){
                length += item.length();
            } else{
                length += directorySize(item);
            }
        }
        return length;
    }*/
 
    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return tFiles.get(r).getName();
            case 1:
                if (tFiles.get(r).isFile()) {
                    Pattern extension = Pattern.compile("(\\.)[a-zA-Z0-9]+$");
                    Matcher m = extension.matcher(tFiles.get(r).getName());
                    if (m.find()) {
                        return m.group();
                    } else {
                        return "Not Found";
                    }

                } else {
                    return "-";
                }
            case 2:
                if(tFiles.get(r).isFile()){
                    double z =  tFiles.get(r).length()/Math.pow(2, 20);
                    return Math.round(z)/100.0;
                } else{
                    return "-";
                }
            case 3:
                Date date = new Date(tFiles.get(r).lastModified());
                return date;
            case 4:
                if (tFiles.get(r).isDirectory()) {
                    return "Yes";
                } else {
                    return "No";
                }
            default:
                return "";
        }
    }

}
