package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


@RestController
public class MovieController {

    @Autowired
    MovieService movieService;
    @PostMapping("/movies/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        String message = movieService.addMovie(movie);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        String message = movieService.addDirector(director);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movieName") String movieName, @RequestParam("dirName") String directorName){
        String message = movieService.addMovieDirectorPair(movieName,directorName);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathParam("name") String movieName){
        Movie movie = movieService.getMovieByName(movieName);
        return new ResponseEntity<>(movie,HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathParam("name") String directorName){
        Director director = movieService.getDirectorByName(directorName);
        return new ResponseEntity<>(director,HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathParam("name") String directorName){
        List<String> moviesName = movieService.getMoviesByDirectorName(directorName);
        return new ResponseEntity<>(moviesName,HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity findAllMovies(){
        List<String> moviesName = movieService.findAllMovies();
        return new ResponseEntity<>(moviesName,HttpStatus.FOUND);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("dirName") String directorName){
        String message = movieService.deleteDirectorByName(directorName);
        return new ResponseEntity<>(message,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        String message = movieService.deleteAllDirectors();
        return new ResponseEntity<>(message,HttpStatus.ACCEPTED);
    }

}
