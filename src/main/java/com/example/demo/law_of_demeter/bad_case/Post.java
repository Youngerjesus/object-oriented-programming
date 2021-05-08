package com.example.demo.law_of_demeter.bad_case;

import java.util.List;

public class Post {
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }
}
