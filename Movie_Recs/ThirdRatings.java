/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings f1r = new FirstRatings();
        myRaters = f1r.loadRaters(ratingsfile);
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        
        for(String movie: movies){
            double rating = getAverageByID(movie, minimalRaters);
            if(rating != 0){
                avgRatings.add(new Rating(movie, rating));
            }
        }
        
        return avgRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        
        for(String movie: movies){
            double rating = getAverageByID(movie, minimalRaters);
            if(rating > 0){
                avgRatings.add(new Rating(movie, rating));
            }
        }
        
        return avgRatings;
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