package com.neo.mapper;

import com.google.common.collect.Lists;
import com.neo.model.Blog;
import com.neo.model.Comment;
import java.util.ArrayList;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author yangwuhai
 * @since 2021-06-22
 */
public class Mock {
    public static Blog newBlog() {
        Blog blog = new Blog();
        Comment comment = new Comment();
        comment.setName("111");
        ArrayList<Comment> comments = Lists.newArrayList(comment);
        blog.setComments(comments);
        return blog;
    }
}
