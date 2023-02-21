package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {
    
    List<Movie> mov = new ArrayList<>();
    List<Director> dir = new ArrayList<>();

    Map<Director,List<Movie>> dirMov = new HashMap<>();
    public String addMovie(Movie movie) {
        for(int i=0;i<mov.size();i++){
            Movie m = mov.get(i);
            if(movie.getName().equals(m.getName()) && movie.getDurationInMinutes()==m.getDurationInMinutes() && movie.getImdbRating()==m.getImdbRating()) {
                return "Movie already added";
            }
        }
        mov.add(movie);
        return "Movie added successfully";
    }

    public String addDirector(Director director) {
        for(int i=0;i<dir.size();i++){
            Director d = dir.get(i);
            if(director.getName().equals(d.getName()) && director.getNumberOfMovies()==d.getNumberOfMovies() && director.getImdbRating()==d.getImdbRating()) {
                return "Director already added";
            }
        }
        dir.add(director);
        return "Director added successfully";
    }

    public String addMovieDirectorPair(String movieName, String directorName){
        Movie m = null;
        Director d = null;
        for(int i=0;i<mov.size();i++){
            if(mov.get(i).getName().equals(movieName)){
                m = mov.get(i);
                break;
            }
        }
        for(int i=0;i<dir.size();i++){
            if(dir.get(i).getName().equals(directorName)){
                d = dir.get(i);
                break;
            }
        }
        if(!dirMov.containsKey(d)){
            List<Movie> movieList = new ArrayList<>();
            movieList.add(m);
            dirMov.put(d,movieList);
            return "Pair added successfully";
        }
        List<Movie> movieList = dirMov.get(d);
        movieList.add(m);
        dirMov.put(d,movieList);
        return "Pair added successfully";
    }

    public Movie getMovieByName(String movieName){
        for(int i=0;i<mov.size();i++){
            if(mov.get(i).getName().equals(movieName)){
                return mov.get(i);
            }
        }
        return null;
    }

    public Director getDirectorByName(String directorName){
        for(int i=0;i<dir.size();i++){
            if(dir.get(i).getName().equals(directorName)){
                return dir.get(i);
            }
        }
        return null;
    }

    public List<String> getMoviesByDirectorName(String directorName){
        Director d = null;
        List<String> movieNames = new ArrayList<>();
        for(int i=0;i<dir.size();i++){
            if(dir.get(i).getName().equals(directorName)){
                d = dir.get(i);
                break;
            }
        }
        if(dirMov.containsKey(d)){
            List<Movie> mObjList  = dirMov.get(d);
            for(int i=0;i<mObjList.size();i++){
                movieNames.add(mObjList.get(i).getName());
            }
        }
        return movieNames;
    }

    public List<String> findAllMovies(){
        List<String> movieNames = new ArrayList<>();
        for(int i=0;i<mov.size();i++){
            movieNames.add(mov.get(i).getName());
        }
        return movieNames;
    }

    public String deleteDirectorByName(String directorName){
        Director d = null;
        for(int i=0;i<dir.size();i++) {
            if (dir.get(i).getName().equals(directorName)) {
                d = dir.get(i);
                break;
            }
        }
        List<Movie> dirMovList = dirMov.get(d);
        for(int i=0;i<dirMovList.size();i++){
            mov.remove(dirMovList.get(i));
        }
        if(dirMov.containsKey(d)){
            dirMov.remove(d);
        }
        dir.remove(d);
        return "Director and movies removed successfully";
    }

    public String deleteAllDirectors(){
        for(int i=0;i<dir.size();i++) {
            List<Movie> dirMovList = dirMov.get(dir.get(i));
            for(int j=0;j<dirMovList.size();j++){
                mov.remove(dirMovList.get(j));
            }
        }
        dirMov.clear();
        dir.clear();
        return "All directors and movies removed successfully";
    }


}
