/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package it.unibo.sampleapp;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import com.diffplug.common.base.Errors;

import com.omertron.omdbapi.OMDBException;
import com.omertron.omdbapi.OmdbApi;
import com.omertron.omdbapi.tools.OmdbBuilder;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.v1.Books;
import com.google.api.services.books.v1.BooksRequestInitializer;

public class App {
    private static final String DEFAULT_AUTHOR = "Dostoevskij";
    private static final String GOOGLE_BOOKS_API_KEY = System.getenv("GOOGLE_BOOKS_API_KEY");
    private static final String OMBD_API_KEY = System.getenv("OMDB_API_KEY");
    
    public static void main(String[] args) throws IOException, OMDBException, GeneralSecurityException {
        final OmdbApi omdb = new OmdbApi(OMBD_API_KEY);
        final String author = Arrays.stream(args).findFirst().orElseGet(()->DEFAULT_AUTHOR);
        
        new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), null)
            .setApplicationName("sample-gradle-app")
            .setGoogleClientRequestInitializer(new BooksRequestInitializer(GOOGLE_BOOKS_API_KEY))
            .build() 
            // 0. Here we get a Books object (API façade)
            .volumes().list("inauthor: " + author)
            .execute().getItems().stream()
            .filter(v -> v.getVolumeInfo().getAuthors().stream().anyMatch(a -> a.toLowerCase().contains(author.toLowerCase())))
            .map(v -> v.getVolumeInfo().getTitle())
            // 1. Here we get a stream of book titles
            .flatMap(Errors.rethrow().<String,Stream<Pair<String,String>>>wrap(title -> Optional.ofNullable(
                    omdb.search(new OmdbBuilder().setSearchTerm(title).build()).getResults())
                    .map(r -> r.stream().map(m -> Pair.of(m.getTitle(), m.getYear()))
            ).orElseGet(() -> Stream.empty())))
            // 2. Here we get a stream of movies as pairs (title, year)
            .distinct().sorted(Comparator.comparing(Pair::getRight))
            // 3. Here we process results by omitting duplicates and sorting them before printing them
            .forEach(System.out::println);
    }
}
