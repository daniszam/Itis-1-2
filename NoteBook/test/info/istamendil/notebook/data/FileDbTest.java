/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.istamendil.notebook.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author danis_zam
 */
//@FixMethodOrder(MethodSorters.JVM)
public class FileDbTest {

    private FileDb db;
    private StringBuilder test ;
    private ArrayList<String>  test1;

    public FileDbTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        db = new FileDb("/Users/danis_zam/Desktop/NetBeansProjects/Notebook/FileDb.txt");
        test = new StringBuilder();
        
       
        
    }

    @After
    public void tearDown() throws DbException {
        db = null;
        test = null;
    }

    @Test
    public void testSave() throws Exception {   // можно сделать как и в testFindAll через массив
        if (Arrays.toString(db.findAll()).length() != 0) {
            test.append(Arrays.toString(db.findAll()));
            test.deleteCharAt(test.length() - 1);
            test.append(", 12345]");
        } else {
            test.append("[12345]");
        }
        System.out.println("save");
        db.save("12345");
        assertEquals(test.toString(), Arrays.toString(db.findAll()));

    }

      @Test
    public void testFindAll() throws Exception {
       System.out.println("findAll");
        test1 = new ArrayList<String>();
        for (int i = 0 ; i<db.findAll().length; i++){
            test1.add("12345");
        }
        assertEquals(Arrays.toString(test1.toArray()), Arrays.toString(db.findAll()));
    } 
}
