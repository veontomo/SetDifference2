
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Andrea
 */
public class SetDifference2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String fileAPath = "c:\\Users\\Andrea\\Documents\\projects\\Test_Liste\\inziali_18564.txt";
        String fileBPath = "c:\\Users\\Andrea\\Documents\\projects\\Test_Liste\\BlackList.txt";
        String fileCPath = "c:\\Users\\Andrea\\Documents\\projects\\Test_Liste\\05 Puliti_16659.txt";
        String fileReplacementPath = "c:\\Users\\Andrea\\Documents\\projects\\Test_Liste\\diventa.txt";
//        try {
//            SetGenerator gen = new SetGenerator(1000000, 1000000, 12390);
//            System.out.println(gen.info());
//            gen.generate(20, 35);
//            HashSet<String> diff = new HashSet<String>();
//            HashSet<String> first = gen.getFirst();
//            HashSet<String> second = gen.getSecond();
//            System.out.println("Taking difference");
//            for (String term : first) {
//                if (!second.contains(term)) {
//                    diff.add(term);
//                }
//            }
//            System.out.println("Difference contains " + (diff.size()) + " elements.");
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            return;
//        }
        Loader loader = new Loader();

        loader.setMapper(new MapperFirstBeats());
        HashMap<String, String> map = loader.loadMap(fileReplacementPath, ",");

        HashSet<String> listA = loader.loadSet(fileAPath);
        System.out.println("loaded " + listA.size() + " elements.");

        HashSet<String> listA1 = loader.replace(listA, map);
        System.out.println("new size " + listA1.size() + ".");

        HashSet<String> listB = loader.loadSet(fileBPath);
        System.out.println("loaded " + listB.size() + " elements.");

        HashSet<String> listB1 = loader.replace(listB, map);
        System.out.println("new size " + listB1.size() + ".");

        HashSet<String> listC = loader.loadSet(fileCPath);
        System.out.println("loaded " + listC.size() + " elements.");

        HashSet<String> diff = loader.difference(listA1, listB1);
        System.out.println("Difference contains " + diff.size() + " elements.");

        HashSet<String> diff0 = loader.difference(diff, listC);
        HashSet<String> listC0 = loader.difference(listC, diff);
        System.out.println("Difference contains " + diff0.size() + " elements that are not in listC.");
        System.out.println("ListC contains " + listC0.size() + " elements that are not in diff.");
    }

}
