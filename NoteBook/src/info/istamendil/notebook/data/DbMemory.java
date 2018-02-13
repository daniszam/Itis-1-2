/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.istamendil.notebook.data;

import java.util.ArrayList;

/**
 *
 * @author danis_zam
 */
public class DbMemory implements Db{
    public ArrayList db = new ArrayList();
    
    @Override
    public void save(Object obj) throws DbException {
        db.add(obj);
    }

    @Override
    public Object[] findAll() throws DbException {
       Object[] object = new Object[db.size()];
       for (int i = 0; i < db.size(); i++){
           if (db. get(i) != null ){
               object[i]=db.get(i);
           }
       }
       return object;
    }
    
}
