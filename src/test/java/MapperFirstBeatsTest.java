/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrea
 */
public class MapperFirstBeatsTest {

    public MapperFirstBeatsTest() {
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

    @Test
    public void testCreateMapNoOverlap() {
        ArrayList<String> lines = new ArrayList<String>(Arrays.asList(new String[]{"a1, a2", "b1, b2", "c1, c2"}));
        String separator = ",";
        MapperFirstBeats instance = new MapperFirstBeats();
        HashMap<String, String> result = instance.createMap(lines, separator);
        assertEquals("there should be three rules", 3, result.size());
        assertEquals("a2", result.get("a1"));
        assertEquals("b2", result.get("b1"));
        assertEquals("c2", result.get("c1"));
    }

    @Test
    public void testCreateMapLeavesFirstRuleOfTheLoop() {
        ArrayList<String> lines = new ArrayList<String>(Arrays.asList(new String[]{"a1, a2", "a2, a1"}));
        String separator = ",";
        MapperFirstBeats instance = new MapperFirstBeats();
        HashMap<String, String> result = instance.createMap(lines, separator);
        assertEquals("there should be one rule", 1, result.size());
        assertEquals("a2", result.get("a1"));
    }
    
    @Test
    public void testCreateMapLeavesFirstRuleIfOverride() {
        ArrayList<String> lines = new ArrayList<String>(Arrays.asList(new String[]{"a1, a2", "a1, a3"}));
        String separator = ",";
        MapperFirstBeats instance = new MapperFirstBeats();
        HashMap<String, String> result = instance.createMap(lines, separator);
        assertEquals("there should be one rule", 1, result.size());
        assertEquals("a2", result.get("a1"));
    }

    // a1 -> a2, b1 -> b2, b2 -> a1 results in a1 -> a2, b1 -> a1, b2 -> a1
    @Test
    public void testCreateMapCross1() {
        ArrayList<String> lines = new ArrayList<String>(Arrays.asList(new String[]{"a1, a2", "b1, b2", "b2, a1"}));
        String separator = ",";
        MapperFirstBeats instance = new MapperFirstBeats();
        HashMap<String, String> result = instance.createMap(lines, separator);
        assertEquals("there should be three rules", 3, result.size());
        assertEquals("a2", result.get("a1"));
        assertEquals("b1 should be mapped into a1", "a1", result.get("b1"));
        assertEquals("a1", result.get("b2"));
    }

    // a1 -> a2, a2 -> a3, a3 -> a1 results in  a2 -> a1, a3 -> a1
    @Test
    public void testCreateThreeElementLoop() {
        ArrayList<String> lines = new ArrayList<String>(Arrays.asList(new String[]{"a1, a2", "a2, a3", "a3, a1"}));
        String separator = ",";
        MapperFirstBeats instance = new MapperFirstBeats();
        HashMap<String, String> result = instance.createMap(lines, separator);
        assertEquals("there should be two rules", 2, result.size());
        assertEquals("a1", result.get("a2"));
        assertEquals("a1", result.get("a3"));
    }

}
