package com.mmiroshnychenko.homeWork1.repository;

import com.mmiroshnychenko.homeWork1.model.Post;
import com.mmiroshnychenko.homeWork1.model.Writer;

import java.io.IOException;
import java.util.List;

public interface PostRepository extends GenericRepository<Post, Long> {
    List<Post> getAllByWriter(Writer writer) throws IOException;
}
