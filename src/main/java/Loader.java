
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Loads data form files.
 * @author Andrea
 */
class Loader {

    /**
     * An instance of a class that can transform list of strings into list of
     * replacements.
     */
    private IMapper mapper = new MapperSequential();

    /**
     * Prepares buffered file reader for futher reading from a file.
     *
     * @param filePath complete path to the file
     * @return
     */
    private BufferedReader bufferedReader(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fileReader);
            return br;
        } catch (FileNotFoundException ex) {
            System.out.println("File " + filePath + " is not found");
            return null;
        }
    }

    /**
     * Returns a list of strings each element of which is a line in the file.
     *
     * @param filePath complet epath to the file
     * @return
     */
    public ArrayList<String> loadStrings(String filePath) {
        BufferedReader br = this.bufferedReader(filePath);
        if (br == null) {
            return null;
        }
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lines;
    }

    /**
     * Returns a replacement map obtained from list of strings
     *
     * @param lines list of strings
     * @param separator a string to separate key and value
     * @return
     */
    public HashMap<String, String> createMap(ArrayList<String> lines, String separator) {
        return mapper.createMap(lines, separator);
    }

    /**
     * Returns a replacement map obtained from file content
     *
     * @param filePath complete path to the file
     * @param separator a string to separate key and value in the file
     * @return
     */
    public HashMap<String, String> loadMap(String filePath, String separator) {
        ArrayList<String> lines = loadStrings(filePath);
        return mapper.createMap(lines, separator);
    }

    /**
     * Returns a replacement map obtained from file content.
     * <p> Default string is used to separate between key and value</p>
     * @param filePath complete path to the file
     * @return 
     */
    public HashMap<String, String> loadMap(String filePath) {
        ArrayList<String> lines = loadStrings(filePath);
        return mapper.createMap(lines, ",");
    }

    /**
     * Returns a set created from a file.
     * @param filePath complete path to the file
     * @return 
     */
    public HashSet<String> loadSet(String filePath) {
        BufferedReader br = this.bufferedReader(filePath);
        if (br == null) {
            return null;
        }
        HashSet<String> hash = new HashSet<>();
        String line, record;
        try {
            while ((line = br.readLine()) != null) {
                record = line.trim();
                if (hash.contains(record)) {
                    System.out.println(record + " is already present");
                } else {
                    hash.add(record);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hash;
    }

    /**
     * Returns a set of elements that belong to listA but do not belong to listB.
     * @param listA
     * @param listB
     * @return 
     */
    public <T> HashSet<T> difference(HashSet<T> listA, HashSet<T> listB) {
        HashSet<T> diff = new HashSet<>();
        listA.stream().filter((T term) -> {
            return !listB.contains(term);
        }).forEach((term) -> {
            diff.add(term);
        });
        return diff;
    }

    /**
     * Applies replacement rules the the set and returns the result as a new set.
     * @param set
     * @param replace
     * @return 
     */
    public <T> HashSet<T> replace(final HashSet<T> set, final HashMap<T, T> replace) {
        HashSet<T> result = new HashSet<>();
        for (T word : set) {
            if (replace.containsKey(word)) {
                result.add(replace.get(word));
            } else {
                result.add(word);
            }
        }
        return result;
    }

    /**
     * {@link #mapper mapper} setter.
     * @param mapper 
     */
    public void setMapper(IMapper mapper) {
        this.mapper = mapper;
    }

}
