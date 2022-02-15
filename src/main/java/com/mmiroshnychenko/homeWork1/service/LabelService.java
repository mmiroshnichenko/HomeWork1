package com.mmiroshnychenko.homeWork1.service;

import com.mmiroshnychenko.homeWork1.model.Label;
import com.mmiroshnychenko.homeWork1.repository.Impl.JsonLabelRepositoryImpl;
import com.mmiroshnychenko.homeWork1.repository.LabelRepository;

import java.io.IOException;
import java.util.List;

public class LabelService {
    private final LabelRepository labelRepository = new JsonLabelRepositoryImpl();

    public List<Label> getList() throws IOException {
        return labelRepository.getAll();
    }

    public List<Label> getListByIds(List<Long> ids) throws IOException {
        return labelRepository.getByIds(ids);
    }

    public Label save(String name) throws IOException {
        Label label = new Label();
        label.setName(name);
        labelRepository.save(label);

        return label;
    }

    public Label getById(Long id) throws IOException {
        return labelRepository.getById(id);
    }

    public Label update(Label label, String name) throws IOException {
        label.setName(name);
        return labelRepository.update(label);
    }

    public void delete(Long id) throws IOException {
        //TODO check label existing in posts
        labelRepository.deleteById(id);
    }
}
