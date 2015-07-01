/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author Andrea
 */
public class StringHelper {

    private static final int MAX_ITERATIONS  = 100;
    
    final static float SENSIBILITY = 0.01f;
    
    final static char PROGRESS_SYMBOL = '.';

    public HashSet<String> generateRandomStrings(int n, int minLen, int maxLen) throws Exception {
        System.out.println("Generating list of size " + n);
        final int delta = maxLen - minLen + 1;
        
        final int step = (int) (n * SENSIBILITY);
        
        Random randomGenerator = new Random();
        HashSet<String> result = new HashSet<String>();
        int progress = 0;
        for (int i = 0; i < n; i++) {
            String foo;
            if ((i - progress) > step ){
                progress = i;
                System.out.print(PROGRESS_SYMBOL);
            }
            int counter = 0;
            do {
                if (counter > 0){
                    System.out.println("Loop #" + counter + " to generate word #" + i);
                }
                foo = randomString(minLen + randomGenerator.nextInt(delta));
                counter++;
                if (counter>MAX_ITERATIONS){
                       throw new Exception("Can not find unique string");
                }

            } while (result.contains(foo));
            result.add(foo);
        }
        System.out.println("Done.");
        return result;
    }

    /**
     * Generates random string of length len
     *
     * @param len
     * @return String
     */
    public String randomString(int len) {
        String result = "";
        int MAX = 122;
        int MIN = 97;
        int delta = MAX - MIN;
        Random randomGenerator = new Random();
        for (int i = 0; i < len; i++) {
            result += (char) (MIN + randomGenerator.nextInt(delta));
        }
        return result;
    }

    public String toString(HashSet<String> list) {
        String result = "";
        for (String word : list) {
            result += word + ", ";
        }
        return result;
    }
}
