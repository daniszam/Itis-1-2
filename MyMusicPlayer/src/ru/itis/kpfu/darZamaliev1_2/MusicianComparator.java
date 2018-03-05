/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZamaliev1_2;

import java.util.Comparator;

/**
 *
 * @author danis_zam
 */
public class MusicianComparator implements Comparator<Track>{

    @Override
    public int compare(Track o1, Track o2) {
        return o1.getMusician().compareTo(o2.getMusician());
    }
    
}
