package com.mmiroshnychenko.homeWork1.service;

import com.mmiroshnychenko.homeWork1.model.Label;
import com.mmiroshnychenko.homeWork1.model.Post;
import com.mmiroshnychenko.homeWork1.model.PostStatus;
import com.mmiroshnychenko.homeWork1.model.Writer;
import com.mmiroshnychenko.homeWork1.repository.Impl.JsonPostRepositoryImpl;
import com.mmiroshnychenko.homeWork1.repository.PostRepository;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class PostService {
    private final PostRepository postRepository = new JsonPostRepositoryImpl();

    public List<Post> getList() throws IOException {
        return postRepository.getAll();
    }

    public Post save(String content, Writer writer, List<Label> labels) throws IOException {
        Post post = new Post();
        post.setWriter(writer);
        post.setContent(content);
        post.setLabels(labels);
        post.setStatus(PostStatus.UNDER_REVIEW);
        post.setCreated(new Date());
        post.setUpdated(new Date());
        postRepository.save(post);

        return post;
    }

    public Post getById(Long id) throws IOException {
        return postRepository.getById(id);
    }

    public Post updateStatus(Post post, PostStatus postStatus) throws IOException {
        post.setStatus(postStatus);
        post.setUpdated(new Date());
        return postRepository.update(post);
    }

    public void delete(Long id) throws IOException {
        postRepository.deleteById(id);
    }
}
