/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZamaliev;

/**
 *
 * @author danis_zam
 */
public interface ISequence {
    void push(Object o);
    Object pick();
    Object pop();
}
