/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings f1r = new FirstRatings();
        myMovies = f1r.loadMovies(moviefile);
        myRaters = f1r.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        
        for(Movie movie : myMovies){
            String id = movie.getID();
            double rating = getAverageByID(id, minimalRaters);
            if(rating != 0){
                avgRatings.add(new Rating(id, rating));
            }
        }
        
        return avgRatings;
    }
    
    public String getTitle(String id) {
        for(Movie movie : myMovies){
            if(movie.getID().equals(id))return movie.getTitle();
        }
        
        return "NAME NOT FOUND";
    }
    
    public String getID(String title) {
        for(Movie movie : myMovies){
            if(movie.getTitle().equals(title)) return movie.getID();
        }
        
        return "NO SUCH TITLE";
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        double total = 0;
        int count = 0;
        
        for(Rater rater : myRaters){
            double rating = rater.getRating(id);
            if(rating != -1){
                total += rating;
                count++;
            }
        }
        
        if(count < minimalRaters) return 0.0;
        return total / count;
    }
    
}