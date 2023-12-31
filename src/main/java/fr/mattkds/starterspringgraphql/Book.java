package fr.mattkds.starterspringgraphql;
public record Book(Integer id, String title, Integer pages, Rating rating, Author author) {}