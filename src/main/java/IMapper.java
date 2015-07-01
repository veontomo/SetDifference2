
import java.util.ArrayList;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrea
 */
public interface IMapper {
    /**
     * Creates replacements from list of lines.
     * <p>
     * Parses each element of list lines and transforms it into a key-value pair
     * that is to then to be addded to the output. 
     * </p>
     * @param lines  list of strings
     * @param separator a string that is used to separate key and value 
     * @return 
     */
    HashMap<String, String> createMap(ArrayList<String> lines, String separator);
    
}
