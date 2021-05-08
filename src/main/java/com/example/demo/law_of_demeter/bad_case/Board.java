package com.example.demo.law_of_demeter.bad_case;

import java.util.List;

public class Board {
    private List<Post> posts;

    public Board(List<Post> posts) {
        this.posts = posts;
    }

    public void addComment(int postId, String content){
        posts.get(postId).getComments().add(new Comment(content)); // Bad Case
    }
}
