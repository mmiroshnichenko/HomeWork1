package com.mmiroshnychenko.homeWork1.repository.Impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.mmiroshnychenko.homeWork1.model.Label;
import com.mmiroshnychenko.homeWork1.repository.LabelRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JsonLabelRepositoryImpl implements LabelRepository {
    private final String filePath = "./src/main/resources/labels.json";

    @Override
    public Label getById(Long id) throws IOException {
        return getAll().stream().filter(v -> v.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Label> getByIds(List<Long> ids) throws IOException {
        return getAll().stream().filter(v -> ids.contains(v.getId())).collect(Collectors.toList());
    }
    @Override
    public List<Label> getAll() throws IOException {
        List<Label> labels = deserialize();
        return labels != null ? labels : new ArrayList<>();
    }

    @Override
    public Label save(Label label) throws IOException {
        List<Label> labels = getAll();
        label.setId(getMaxId());
        labels.add(label);
        serialize(labels);

        return label;
    }

    @Override
    public Label update(Label label) throws IOException {
        List<Label> labels = getAll();
        Label updatedLabel = labels.stream().filter(v -> v.getId().equals(label.getId())).findFirst().orElse(null);
        updatedLabel.setName(label.getName());
        serialize(labels);

        return label;
    }

    @Override
    public void deleteById(Long id) throws IOException {
        List<Label> labels = getAll();
        labels.removeIf(v -> v.getId().equals(id));
        serialize(labels);
    }

    private List<Label> deserialize() throws IOException {
        try (JsonReader jsonReader = new JsonReader(new FileReader(filePath))) {
            return new Gson().fromJson(jsonReader, new TypeToken<List<Label>>() {}.getType());
        }
    }

    private void serialize(List<Label> labels) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(labels, fileWriter);
        }
    }

    private Long getMaxId() throws IOException {
        Label labelWithMaxId =  getAll().stream().max(Comparator.comparing(Label::getId)).orElse(null);
        return labelWithMaxId != null ? labelWithMaxId.getId() + 1 : 1;
    }
}
