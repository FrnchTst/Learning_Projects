
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerAverage {

    public void printAverageRatings() {
        //String moviefile = "data/ratedmovies_short.csv";
        String moviefile = "data/ratedmoviesfull.csv";
        //String ratersfile = "data/ratings_short.csv";
        String ratersfile = "data/ratings.csv";
        int minimum = 12;
        
        SecondRatings sr = new SecondRatings(moviefile, ratersfile);
        
        System.out.println("There are " + sr.getMovieSize() + " movies and " + sr.getRaterSize() + " raters in the files");
        
        ArrayList<Rating> ratings = sr.getAverageRatings(minimum);
        System.out.println("There are " + ratings.size() + " movies with at least " + minimum + " ratings");
        
        while(ratings.size()>0){
            double lowestScore = 0.0;
            Rating lowestRating = null;
            for(Rating rating : ratings){
                double score = rating.getValue();
                if(score < lowestScore || lowestRating == null){
                    lowestScore = score;
                    lowestRating = rating;
                }
            }
            System.out.println(lowestRating.getValue() + "\t" + sr.getTitle(lowestRating.getItem()));
            ratings.remove(lowestRating);
        }
    }
    
    public void getAverageRatingOneMovie() {
        //String moviefile = "data/ratedmovies_short.csv";
        String moviefile = "data/ratedmoviesfull.csv";
        //String ratersfile = "data/ratings_short.csv";
        String ratersfile = "data/ratings.csv";
        String movie = "Vacation";
        
        SecondRatings sr = new SecondRatings(moviefile, ratersfile);
        ArrayList<Rating> ratings = sr.getAverageRatings(3);
        
        for(Rating rating : ratings){
            if(sr.getTitle(rating.getItem()).equals(movie)){
                System.out.println("The average rating for " + movie + " is " + rating.getValue());
            }
        }
    }
    
}
