
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class MinutesFilter implements Filter {
    
    private int myMin;
    private int myMax;
    
    public MinutesFilter(int min, int max) {
        myMin = min;
        myMax = max;
    }
    
    public boolean satisfies(String id) {
        int runtime = MovieDatabase.getMinutes(id);
        return runtime >= myMin && runtime <= myMax;
    }

}
