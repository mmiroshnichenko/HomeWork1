package com.mmiroshnychenko.homeWork1.service;

import com.mmiroshnychenko.homeWork1.model.Post;
import com.mmiroshnychenko.homeWork1.model.Writer;
import com.mmiroshnychenko.homeWork1.repository.Impl.JsonPostRepositoryImpl;
import com.mmiroshnychenko.homeWork1.repository.Impl.JsonWriterRepositoryImpl;
import com.mmiroshnychenko.homeWork1.repository.PostRepository;
import com.mmiroshnychenko.homeWork1.repository.WriterRepository;

import java.io.IOException;
import java.util.List;

public class WriterService {
    private final WriterRepository writerRepository = new JsonWriterRepositoryImpl();
    private final PostRepository postRepository = new JsonPostRepositoryImpl();

    public List<Writer> getList() throws IOException {
        return writerRepository.getAll();
    }

    public Writer save(String firstName, String lastName) throws IOException {
        Writer writer = new Writer();
        writer.setFirstName(firstName);
        writer.setLastName(lastName);
        writerRepository.save(writer);

        return writer;
    }

    public Writer getById(Long id) throws IOException {
        Writer writer = writerRepository.getById(id);
        if (writer != null) {
            List<Post> posts = postRepository.getAllByWriter(writer);
            writer.setPosts(posts);
        }

        return writer;
    }

    public Writer update(Writer writer, String firstName, String lastName) throws IOException {
        writer.setFirstName(firstName);
        writer.setLastName(lastName);

        return writerRepository.update(writer);
    }

    public void delete(Long id) throws IOException {
        writerRepository.deleteById(id);
    }
 }
