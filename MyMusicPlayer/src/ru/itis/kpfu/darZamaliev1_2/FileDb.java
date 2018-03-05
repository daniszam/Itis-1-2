/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZamaliev1_2;

import com.mpatric.mp3agic.Mp3File;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


/**
 *
 * @author danis_zam
 */
public class FileDb {

    private final String path;
    

    public FileDb(String path) {
        this.path = path;
        
    }

    public void save(Mp3File mp3) throws DbException, IOException{
        Object[] mp3Set = this.findAll();
        try (FileOutputStream stream = new FileOutputStream(this.path)) {
            Object[] newData = new Object[mp3Set.length + 1];
            System.arraycopy(mp3Set, 0, newData, 0, mp3Set.length);
            newData[newData.length - 1] = mp3;
            stream.write(this.convertToBytes(newData));
        }catch (IOException ex){
            throw new DbException("error");
        }
    }

    public Object[] findAll() throws DbException, IOException {
        try {
            Path path = Paths.get(this.path);
            byte[] data = Files.readAllBytes(path);
            if (data.length > 0){
                return (Object[]) this.convertFromBytes(data);
            }
            else{
                return new Object[0];
            }
        } catch (IOException ex) {
            throw new DbException("error");
        } catch (ClassNotFoundException ex) {
            throw new DbException("error");
        }
    }

    private byte[] convertToBytes(Object object) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutput out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            return bos.toByteArray();
        }
    }
    
      private Object convertFromBytes(byte[] data) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
                ObjectInput in = new ObjectInputStream(bis)) {
            return in.readObject();
        }
      }
}
