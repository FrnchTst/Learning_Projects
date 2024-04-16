
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {

    public ArrayList<Movie> loadMovies(String filename){
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Movie> moviesInfo = new ArrayList<Movie>();
        
        for(CSVRecord record : parser){
            moviesInfo.add(new Movie(record.get("id"), record.get("title"), record.get("year"), record.get("genre"), record.get("director"), record.get("country"), 
                record.get("poster"), Integer.parseInt(record.get("minutes"))));
        }
        
        return moviesInfo;
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Rater> ratersInfo = new ArrayList<Rater>();
        
        for(CSVRecord record : parser){
            String id = record.get("rater_id");
            boolean contains = false;
            int index = ratersInfo.size();
            for(Rater rater : ratersInfo){
                if(rater.getID().equals(id)){
                    contains = true;
                    index = ratersInfo.indexOf(rater);
                    break;
                }
            }
            if(!contains) ratersInfo.add(new EfficientRater(id));
            ratersInfo.get(index).addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
        }
        
        return ratersInfo;        
    }
    
    public void testLoadMovies(){
        //String filename = "data/ratedmovies_short.csv";
        String filename = "data/ratedmoviesfull.csv";
        ArrayList<Movie> movies = loadMovies(filename);
        int comedyCount = 0;
        int longTime = 150;
        int longRunTime = 0;
        HashMap<String, Integer> directors = new HashMap<String, Integer>();
        
        for(Movie movie : movies){
            //System.out.println(movie.getTitle());
            if(movie.getGenres().contains("Comedy")) comedyCount++;
            if(movie.getMinutes() > longTime) longRunTime++;
            
            String[] thisDirectors = movie.getDirector().split("\\s*,\\s*");
            for(int i = 0; i < thisDirectors.length; i++){
                if(directors.containsKey(thisDirectors[i])) directors.put(thisDirectors[i], directors.get(thisDirectors[i]) + 1);
                else directors.put(thisDirectors[i], 1);
            }
        }
        
        int maxMovieCount = 0;
        ArrayList<String> maxDirectors = new ArrayList<String>();
        
        for(String director : directors.keySet()){
            int movieCount = directors.get(director);
            if(movieCount > maxMovieCount){
                maxMovieCount = movieCount;
                maxDirectors.clear();
                maxDirectors.add(director);
            } else if(movieCount == maxMovieCount) maxDirectors.add(director);
        }
        
        System.out.println("There are " + movies.size() + " movies in the file");
        System.out.println("There are " + comedyCount + " movies in the comedy genre");
        System.out.println("There are " + longRunTime + " movies longer than " + longTime + " minutes");
        System.out.println("The highest number of movies directed by one director is " + maxMovieCount + " by " + maxDirectors.size() + " director(s)");
        System.out.println("The director(s) who produced that many movies is(are): ");
        for(String director : maxDirectors){
            System.out.print(director);
            if(maxDirectors.indexOf(director) < maxDirectors.size() - 1) System.out.print(", ");
            else System.out.println("");
        }
    }
    
    public void testLoadRaters(){
        //String filename = "data/ratings_short.csv";
        String filename = "data/ratings.csv";
        ArrayList<Rater> raters = loadRaters(filename);
        int maxRatings = 0;
        ArrayList<Rater> maxRaters = new ArrayList<Rater>();
        HashMap<String, Integer> movieRatingCount = new HashMap<String, Integer>();
        
        System.out.println("There are " + raters.size() + " raters in the file");
        /*System.out.println("Rater ID : number of ratings");
        System.out.println("\tMovie ID : rating given");*/
        for(Rater rater : raters){
            /*ArrayList<String> ratings = rater.getItemsRated();
            System.out.println(rater.getID() + " : " + rater.numRatings());
            for(String movie : ratings){
                System.out.println("\t" + movie + " : " + rater.getRating(movie));
            }*/
            int ratingCount = raterRatingCount(raters, rater.getID());
            if(ratingCount > maxRatings){
                maxRatings = ratingCount;
                maxRaters.clear();
                maxRaters.add(rater);
            } else if(ratingCount == maxRatings) maxRaters.add(rater);
            
            for(String movie : rater.getItemsRated()){
                if(!movieRatingCount.containsKey(movie)) movieRatingCount.put(movie, 1);
                else movieRatingCount.put(movie, movieRatingCount.get(movie) + 1);
            }
        }
        
        String raterID = "193";
        int ratersCount = raterRatingCount(raters, raterID);
        System.out.println("Rater " + raterID + " has " + ratersCount + " ratings");
        
        System.out.println("The highest number of ratings made by any rater is " + maxRatings + " by " + maxRaters.size() + " rater(s)");
        System.out.println("The rater(s) with that number of ratings is(are): ");
        for(Rater rater : maxRaters){
            System.out.print(rater.getID());
            if(maxRaters.indexOf(rater) < maxRaters.size() - 1) System.out.print(", ");
            else System.out.println("");
        }        
        
        String movieID = "1798709";
        int moviesRatings = movieRatings(raters, movieID);
        System.out.println("Movie " + movieID + " has " + moviesRatings + " rating(s)");
        
        System.out.println("There are ratings for " + movieRatingCount.size() + " different movies");
    }
    
    private int raterRatingCount(ArrayList<Rater> raters, String id){
        for(Rater rater : raters){
            if(rater.getID().equals(id)) return rater.numRatings();
        }
        return -1;
    }
    
    private int movieRatings(ArrayList<Rater> raters, String id){
        int ratings = 0;
        for(Rater rater : raters){
            if(rater.hasRating(id)) ratings++;
        }
        
        return ratings;
    }
}
