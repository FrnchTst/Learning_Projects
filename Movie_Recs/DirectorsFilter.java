
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class DirectorsFilter implements Filter {
    
    private ArrayList<String> myDirectors;
    
    public DirectorsFilter(String directors) {
        myDirectors = new ArrayList<String>(Arrays.asList(directors.split("\\s*,\\s*")));
    }
    
    public boolean satisfies(String id) {
        String movieDirectors = MovieDatabase.getDirector(id);
        for(String director : myDirectors){
            if(movieDirectors.contains(director)) return true;
        }
        
        return false;
    }

}
