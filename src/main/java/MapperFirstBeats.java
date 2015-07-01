
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Mapper in which the first replacement beats any forthcoming ones.
 *
 * @author Andrea
 */
public class MapperFirstBeats implements IMapper {

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
                    continue;
                }
                if (map.containsKey(key)) {
                } else {
                    if (map.containsKey(value)) {
                        String oldValue = map.get(value);
                        if (key.equals(oldValue)) {
                            map.remove(value);
                        }
                        map.put(key, value);

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
