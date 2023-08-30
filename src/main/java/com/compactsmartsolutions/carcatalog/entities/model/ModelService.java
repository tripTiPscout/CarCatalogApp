package com.compactsmartsolutions.carcatalog.entities.model;

import java.util.List;

public interface ModelService {

    Model createModel(Model model);

    void deleteModel(Long id);

    Model getModelById(Long id);

    Model getModelByName(String name);

    List<Model> getAllModels();

}
