package com.neo.model;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author yangwuhai
 * @since 2021-06-22
 */
public class Blog {
    private int id;
    private String title;
    private Author author;
    private String body;
     List<Comment> comments;
    private HashMap<String,String> labels;
    private List<Post> posts;

    public Blog() {
    }

    public Blog(int id, String title, Author author, List<Post> posts) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.posts = posts;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public HashMap<String, String> getLabels() {
        return labels;
    }

    public void setLabels(HashMap<String, String> labels) {
        this.labels = labels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
