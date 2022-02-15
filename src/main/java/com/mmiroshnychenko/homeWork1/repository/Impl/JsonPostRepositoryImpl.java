package com.mmiroshnychenko.homeWork1.repository.Impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.mmiroshnychenko.homeWork1.model.Post;
import com.mmiroshnychenko.homeWork1.model.Writer;
import com.mmiroshnychenko.homeWork1.repository.PostRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JsonPostRepositoryImpl implements PostRepository {
    private final String filePath = "./src/main/resources/posts.json";

    @Override
    public Post getById(Long id) throws IOException {
        return getAll().stream().filter(v -> v.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Post> getAll() throws IOException {
        List<Post> posts = deserialize();
        return posts != null ? posts : new ArrayList<>();
    }

    public List<Post> getAllByWriter(Writer writer) throws IOException {
        return getAll().stream().filter(v -> v.getWriter().equals(writer)).collect(Collectors.toList());
    }

    @Override
    public Post save(Post post) throws IOException {
        List<Post> posts = getAll();
        post.setId(getMaxId());
        posts.add(post);
        serialize(posts);

        return post;
    }

    @Override
    public Post update(Post post) throws IOException {
        List<Post> posts = getAll();
        Post updatedPost = posts.stream().filter(v -> v.getId().equals(post.getId())).findFirst().orElse(null);
        updatedPost.setContent(post.getContent());
        updatedPost.setLabels(post.getLabels());
        updatedPost.setWriter(post.getWriter());
        updatedPost.setStatus(post.getStatus());
        updatedPost.setUpdated(new Date());
        serialize(posts);

        return post;
    }

    @Override
    public void deleteById(Long id) throws IOException {
        List<Post> labels = getAll();
        labels.removeIf(v -> v.getId().equals(id));
        serialize(labels);
    }

    private List<Post> deserialize() throws IOException {
        try (JsonReader jsonReader = new JsonReader(new FileReader(filePath))) {
            return new Gson().fromJson(jsonReader, new TypeToken<List<Post>>() {}.getType());
        }
    }

    private void serialize(List<Post> labels) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(labels, fileWriter);
        }
    }

    private Long getMaxId() throws IOException {
        Post labelWithMaxId =  getAll().stream().max(Comparator.comparing(Post::getId)).orElse(null);
        return labelWithMaxId != null ? labelWithMaxId.getId() + 1 : 1;
    }
}
