/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZamaliev1_2;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author danis_zam
 */
public class MyTableModel extends AbstractTableModel {
    private ArrayList<Track> track;

    public MyTableModel(ArrayList<Track> arr){
        super();
        this.track = arr;
    }
    
    @Override
    public int getRowCount() {
        return this.track.size();
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
                result = "Musician";
                break;
            case 2:
                result = "Duration";
                break;
                 case 3:
                result = "Year";
                break;
                 case 4:
                    result = "Genre";
                    break;
        }
        return result;
    }
    
    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return track.get(r).getName();
            case 1:
                return track.get(r).getMusician();
            case 2:
                return track.get(r).getDuration();
            case 3:
                return track.get(r).getYear();
            case 4:
                return track.get(r).getGenre();
            default:
                return "";
        }
    }

}

