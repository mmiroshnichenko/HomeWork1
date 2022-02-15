package com.mmiroshnychenko.homeWork1.repository.Impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.mmiroshnychenko.homeWork1.model.Writer;
import com.mmiroshnychenko.homeWork1.repository.WriterRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class JsonWriterRepositoryImpl implements WriterRepository {
    private final String filePath = "./src/main/resources/writes.json";

    @Override
    public Writer getById(Long id) throws IOException {
        return getAll().stream().filter(v -> v.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Writer> getAll() throws IOException {
        List<Writer> writers = deserialize();
        return writers != null ? writers : new ArrayList<>();
    }

    @Override
    public Writer save(Writer writer) throws IOException {
        List<Writer> writers = getAll();
        writer.setId(getMaxId());
        writers.add(writer);
        serialize(writers);

        return writer;
    }

    @Override
    public Writer update(Writer writer) throws IOException {
        List<Writer> writers = getAll();
        Writer updatedWriter = writers.stream().filter(v -> v.getId().equals(writer.getId())).findFirst().orElse(null);
        updatedWriter.setFirstName(writer.getFirstName());
        updatedWriter.setLastName(writer.getLastName());
        updatedWriter.setPosts(null);
        serialize(writers);

        return writer;
    }

    @Override
    public void deleteById(Long id) throws IOException {
        List<Writer> writers = getAll();
        writers.removeIf(v -> v.getId().equals(id));
        serialize(writers);
    }

    private List<Writer> deserialize() throws IOException {
        try (JsonReader jsonReader = new JsonReader(new FileReader(filePath))) {
            return new Gson().fromJson(jsonReader, new TypeToken<List<Writer>>() {}.getType());
        }
    }

    private void serialize(List<Writer> writers) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(writers, fileWriter);
        }
    }

    private Long getMaxId() throws IOException {
        Writer writerWithMaxId =  getAll().stream().max(Comparator.comparing(Writer::getId)).orElse(null);
        return writerWithMaxId != null ? writerWithMaxId.getId() + 1 : 1;
    }
}
