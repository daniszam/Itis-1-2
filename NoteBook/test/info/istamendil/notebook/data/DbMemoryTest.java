/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import info.istamendil.notebook.data.Db;
import info.istamendil.notebook.data.DbException;
import info.istamendil.notebook.data.DbMemory;
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

/**
 *
 * @author danis_zam
 */
public class DbMemoryTest {
    private Db db;
    public DbMemoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        
    }
    @Test
    public void testSave() throws DbException{
        System.out.println("save");
        db.save("fffff");
        assertEquals("[fffff]",Arrays.toString(db.findAll()));
    }
    
    @Test
    public void testFindAll() throws DbException  {
        System.out.println("findAll");
        db.save("fffff");
        Object[] xxx = new Object[1];
        xxx[0]= "fffff";
        assertEquals(Arrays.toString(xxx), Arrays.toString(db.findAll()));
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
        db = new DbMemory();
        
    }
    
    @After
    public void tearDown() {
        db = null;
    } 


}
