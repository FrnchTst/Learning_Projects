
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerWithFilters {

    public void printAverageRatings() {
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        //String ratersfile = "data/ratings_short.csv";
        String ratersfile = "data/ratings.csv";
        int minimum = 35;
        
        ThirdRatings tr = new ThirdRatings(ratersfile);
        MovieDatabase.initialize(moviefile);
        
        System.out.println("There are " + tr.getRaterSize() + " raters and " + MovieDatabase.size() + " movies in the files");
        
        ArrayList<Rating> ratings = tr.getAverageRatings(minimum);
        System.out.println("There are " + ratings.size() + " movies with at least " + minimum + " ratings");
        
        while(ratings.size() > 0){
            double lowestScore = 0.0;
            Rating lowestRating = null;
            for(Rating rating : ratings){
                double score = rating.getValue();
                if(score < lowestScore || lowestRating == null){
                    lowestScore = score;
                    lowestRating = rating;
                }
            }
            System.out.println(lowestRating.getValue() + "\t" + MovieDatabase.getTitle(lowestRating.getItem()));
            ratings.remove(lowestRating);
        }
    }
    
    public void printAverageRatingsByYear() {
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        //String ratersfile = "data/ratings_short.csv";
        String ratersfile = "data/ratings.csv";
        int minimum = 20;
        
        ThirdRatings tr = new ThirdRatings(ratersfile);
        MovieDatabase.initialize(moviefile);
        int year = 2000;
        Filter yearf = new YearAfterFilter(year);
        
        System.out.println("There are " + tr.getRaterSize() + " raters and " + MovieDatabase.size() + " movies in the files");
        
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimum, yearf);
        System.out.println("There are " + ratings.size() + " movies with at least " + minimum + " ratings");
        
        while(ratings.size() > 0){
            double lowestScore = 0.0;
            Rating lowestRating = null;
            for(Rating rating : ratings){
                double score = rating.getValue();
                if(score < lowestScore || lowestRating == null){
                    lowestScore = score;
                    lowestRating = rating;
                }
            }
            System.out.println(lowestRating.getValue() + "\t" + MovieDatabase.getYear(lowestRating.getItem()) + "\t" + MovieDatabase.getTitle(lowestRating.getItem()));
            ratings.remove(lowestRating);
        }
    }
    
    public void printAverageRatingsByGenre() {
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        //String ratersfile = "data/ratings_short.csv";
        String ratersfile = "data/ratings.csv";
        int minimum = 20;
        
        ThirdRatings tr = new ThirdRatings(ratersfile);
        MovieDatabase.initialize(moviefile);
        String genre = "Comedy";
        Filter genref = new GenreFilter(genre);
        
        System.out.println("There are " + tr.getRaterSize() + " raters and " + MovieDatabase.size() + " movies in the files");
        
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimum, genref);
        System.out.println("There are " + ratings.size() + " movies with at least " + minimum + " ratings");
        
        while(ratings.size() > 0){
            double lowestScore = 0.0;
            Rating lowestRating = null;
            for(Rating rating : ratings){
                double score = rating.getValue();
                if(score < lowestScore || lowestRating == null){
                    lowestScore = score;
                    lowestRating = rating;
                }
            }
            System.out.println(lowestRating.getValue() + "\t" + MovieDatabase.getTitle(lowestRating.getItem()) + "\n\t" + MovieDatabase.getGenres(lowestRating.getItem()));
            ratings.remove(lowestRating);
        }
    }
    
    public void printAverageRatingsByMinutes() {
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        //String ratersfile = "data/ratings_short.csv";
        String ratersfile = "data/ratings.csv";
        int minimum = 5;
        
        ThirdRatings tr = new ThirdRatings(ratersfile);
        MovieDatabase.initialize(moviefile);
        int minTime = 105;
        int maxTime = 135;
        Filter minutef = new MinutesFilter(minTime, maxTime);
        
        System.out.println("There are " + tr.getRaterSize() + " raters and " + MovieDatabase.size() + " movies in the files");
        
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimum, minutef);
        System.out.println("There are " + ratings.size() + " movies with at least " + minimum + " ratings");
        /*
        while(ratings.size() > 0){
            double lowestScore = 0.0;
            Rating lowestRating = null;
            for(Rating rating : ratings){
                double score = rating.getValue();
                if(score < lowestScore || lowestRating == null){
                    lowestScore = score;
                    lowestRating = rating;
                }
            }
            System.out.println(lowestRating.getValue() + "\tTime: " + MovieDatabase.getMinutes(lowestRating.getItem()) + "\t" + MovieDatabase.getTitle(lowestRating.getItem()));
            ratings.remove(lowestRating);
        }*/
    }
    
    public void printAverageRatingsByDirectors() {
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        //String ratersfile = "data/ratings_short.csv";
        String ratersfile = "data/ratings.csv";
        int minimum = 4;
        
        ThirdRatings tr = new ThirdRatings(ratersfile);
        MovieDatabase.initialize(moviefile);
        String dirs = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        Filter diref = new DirectorsFilter(dirs);
        
        System.out.println("There are " + tr.getRaterSize() + " raters and " + MovieDatabase.size() + " movies in the files");
        
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimum, diref);
        System.out.println("There are " + ratings.size() + " movies with at least " + minimum + " ratings");
        
        while(ratings.size() > 0){
            double lowestScore = 0.0;
            Rating lowestRating = null;
            for(Rating rating : ratings){
                double score = rating.getValue();
                if(score < lowestScore || lowestRating == null){
                    lowestScore = score;
                    lowestRating = rating;
                }
            }
            System.out.println(lowestRating.getValue() + "\t" + MovieDatabase.getTitle(lowestRating.getItem()) + "\n\t" + MovieDatabase.getDirector(lowestRating.getItem()));
            ratings.remove(lowestRating);
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        //String ratersfile = "data/ratings_short.csv";
        String ratersfile = "data/ratings.csv";
        int minimum = 3;
        
        ThirdRatings tr = new ThirdRatings(ratersfile);
        MovieDatabase.initialize(moviefile);
        int min = 90;
        int max = 180;
        String dirs = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        Filter minf = new MinutesFilter(min, max);
        Filter diref = new DirectorsFilter(dirs);
        AllFilters af = new AllFilters();
        af.addFilter(minf);
        af.addFilter(diref);
        
        System.out.println("There are " + tr.getRaterSize() + " raters and " + MovieDatabase.size() + " movies in the files");
        
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimum, af);
        System.out.println("There are " + ratings.size() + " movies with at least " + minimum + " ratings");
        /*
        while(ratings.size() > 0){
            double lowestScore = 0.0;
            Rating lowestRating = null;
            for(Rating rating : ratings){
                double score = rating.getValue();
                if(score < lowestScore || lowestRating == null){
                    lowestScore = score;
                    lowestRating = rating;
                }
            }
            System.out.println(lowestRating.getValue() + "\tTime: " + MovieDatabase.getMinutes(lowestRating.getItem()) + "\t" + MovieDatabase.getTitle(lowestRating.getItem()) + "\n\t" + MovieDatabase.getDirector(lowestRating.getItem()));
            ratings.remove(lowestRating);
        }*/
    }
    
    public void printAverageRatingsByYearAndGenre() {
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        //String ratersfile = "data/ratings_short.csv";
        String ratersfile = "data/ratings.csv";
        int minimum = 8;
        
        ThirdRatings tr = new ThirdRatings(ratersfile);
        MovieDatabase.initialize(moviefile);
        int year = 1990;
        String genre = "Drama";
        Filter yearf = new YearAfterFilter(year);
        Filter genref = new GenreFilter(genre);
        AllFilters af = new AllFilters();
        af.addFilter(yearf);
        af.addFilter(genref);
        
        System.out.println("There are " + tr.getRaterSize() + " raters and " + MovieDatabase.size() + " movies in the files");
        
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimum, af);
        System.out.println("There are " + ratings.size() + " movies with at least " + minimum + " ratings");
        /*
        while(ratings.size() > 0){
            double lowestScore = 0.0;
            Rating lowestRating = null;
            for(Rating rating : ratings){
                double score = rating.getValue();
                if(score < lowestScore || lowestRating == null){
                    lowestScore = score;
                    lowestRating = rating;
                }
            }
            System.out.println(lowestRating.getValue() + "\tTime: " + MovieDatabase.getMinutes(lowestRating.getItem()) + "\t" + MovieDatabase.getTitle(lowestRating.getItem()) + "\n\t" + MovieDatabase.getDirector(lowestRating.getItem()));
            ratings.remove(lowestRating);
        }*/
    }
    
}
