package com.mmiroshnychenko.homeWork1.repository;

import com.mmiroshnychenko.homeWork1.model.Label;

import java.io.IOException;
import java.util.List;

public interface LabelRepository extends GenericRepository<Label, Long>{
    List<Label> getByIds(List<Long> ids) throws IOException;
}
