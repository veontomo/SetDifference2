/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Andrea
 */
@RunWith(MockitoJUnitRunner.class)
public class LoadHashMapTest {

    public LoadHashMapTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    
    /**
     * Test of loadFromFile method, of class Loader.
     */
    @Test
    public void testFeedFromBufferAllDifferent() {
//        BufferedReader bufferedReader = new BufferedReader(new StringReader("a1, a2\nb1, b2\nc1, c2"));
//        Loader loader = new Loader();
//        loader.feedFromBuffer(bufferedReader);
//        HashMap<String, String> map = loader.getMap();
//        assertEquals(map.size(), 3);
//        assertEquals(map.get("a1"), "a2");
//        assertEquals(map.get("b1"), "b2");
//        assertEquals(map.get("c1"), "c2");
    }
    
    /**
     * Test of loadFromFile method, of class Loader.
     */
    @Test
    public void testFeedFromBufferTrivialLoop() {
//        BufferedReader bufferedReader = new BufferedReader(new StringReader("a1, a2\na2, a1"));
//        Loader loader = new Loader();
//        loader.feedFromBuffer(bufferedReader);
//        HashMap<String, String> map = loader.getMap();
//        assertEquals(map.size(), 1);
//        assertEquals(map.get("a2"), "a1");
    }
    
    /**
     * Test of loadFromFile method, of class Loader.
     */
    @Test
    public void testFeedFromBufferOverride() {
//        BufferedReader bufferedReader = new BufferedReader(new StringReader("a1, a2\na1, b2"));
//        Loader loader = new Loader();
//        loader.feedFromBuffer(bufferedReader);
//        HashMap<String, String> map = loader.getMap();
//        assertEquals(map.size(), 1);
//        assertEquals(map.get("a1"), "b2");
    }
}
