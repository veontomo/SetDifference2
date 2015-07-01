
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Mapper that treats replacement rules in sequential manner.
 * @author Andrea
 */
public class MapperSequential implements IMapper {

    @Override
    public HashMap<String, String> createMap(ArrayList<String> lines, String separator) {
        HashMap<String, String> map = new HashMap<>();
        String[] parts;
        for (String line : lines) {
            try {
                parts = split(line, ",");
                String key = parts[0];
                String value = parts[1];
                if (key.equals(value)) {
                    System.out.println("Trivial replacement: " + key + " -> " + value);
                    continue;
                }
                if (map.containsKey(key)) {
                    String oldValue = map.get(key);
                    if (oldValue.equals(value)) {
                        // System.out.println("Duplicate pair: " + key + " -> " + value);
                    } else {
                        System.out.println("Update: " + key + " -> " + oldValue + " becomes " + key + " -> " + value);
                        map.put(key, value);
                    }
                } else {
                    if (map.containsKey(value)) {
                        String oldValue = map.get(value);
                        System.out.println("Loop is detected:\n" + value + " -> " + oldValue
                                + " (exists)\n" + key + " -> " + value + " (new)");
                        if (key.equals(oldValue)) {
                            System.out.println("The loop is trivial. Drop the record " + value + " -> " + oldValue
                                    + " and insert " + key + " -> " + value);
                            map.remove(value);
                            map.put(key, value);
                        } else {
                            System.out.println("The loop is NOT trivial. Insert record " + key + " -> " + oldValue);
                            map.put(key, oldValue);
                        }
                    } else {
                        map.put(key, value);
                    }

                }
            } catch (Exception ex) {
                System.out.println("Failed to process line " + line + ": " + ex.getMessage());
            }

        }

        return map;
    }

    private String[] split(final String line, final String separator) throws Exception {
        String[] parts = line.split(separator);
        if (parts.length != 2) {
            throw new Exception("Line " + line + " does not contain exactly two entries.");
        }
        parts[0] = parts[0].trim();
        parts[1] = parts[1].trim();
        return parts;
    }

}
