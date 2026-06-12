package com.example.blog_app;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {
    private final JdbcClient jdbcClient;

    public BlogRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Blog> findAll() {
        return jdbcClient.sql("SELECT id, title, mainText FROM blogs")
                .query(Blog.class)
                .list();
    }

    public List<Blog> findSearch(String search) {
        return jdbcClient.sql("SELECT id, title, mainText FROM blogs WHERE title LIKE CONCAT('%', :search, '%')")
                .param("search",search)
                .query(Blog.class)
                .list();
    }

    public void save(BlogForm blog) {
        jdbcClient.sql("INSERT INTO blogs (title, mainText) VALUES (:title, :mainText)")
                .param("title", blog.getTitle())
                .param("mainText", blog.getMainText())
                .update();
    }

    public Optional<Blog> findById(Long id) {
        return jdbcClient.sql("SELECT id, title, mainText FROM blogs WHERE id = :id")
                .param("id", id)
                .query(Blog.class)
                .optional();
    }

    public void deleteById(Long id) {
        jdbcClient.sql("DELETE FROM blogs WHERE id = :id")
                .param("id", id)
                .update();
    }
}
