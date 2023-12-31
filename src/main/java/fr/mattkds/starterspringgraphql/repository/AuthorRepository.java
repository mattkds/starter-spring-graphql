package fr.mattkds.starterspringgraphql.repository;

import fr.mattkds.starterspringgraphql.Author;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Author repository
 * To keep this starter simple, we just store simple data on a collection
 * To Persist this data, a more complex implementation could be done by using Spring Data JPA and a real Database.
 */
@Repository
public class AuthorRepository {

    /**
     * This is the default list of authors build on the initialisation, this list is filled in with the init method
     */
    private List<Author> authors = new ArrayList<>();

    public List<Author> findAll() {
        return authors;
    }

    public Author findById(int id) {
        return authors.stream()
                .filter(author -> author.id() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    public Author findByName(String name) {
        return authors.stream()
                .filter(author -> author.fullName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    /**
     * Method to initialize a simple Database
     * PostConstruct annotation is used on a method that needs to be executed after dependency injection is done to perform any initialization.
     */
    @PostConstruct
    private void init() {
        authors.add(new Author(1,"Josh","Long"));
        authors.add(new Author(2,"Mark","Heckler"));
        authors.add(new Author(3,"Greg","Turnquist"));
    }
}