package com.neo.model;

import java.sql.Date;
import java.util.List;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author yangwuhai
 * @since 2021-06-22
 */
public class Post {
    private int id;
    private Author author;
    private Blog blog;
    private Date createdOn;
    private Section section;
    private String subject;
    private String body;
    private List<Comment> comments;
    private List<Tag> tags;
}
