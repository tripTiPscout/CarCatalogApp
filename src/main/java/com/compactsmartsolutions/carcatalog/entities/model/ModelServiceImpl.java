package com.compactsmartsolutions.carcatalog.entities.model;

import com.compactsmartsolutions.carcatalog.entities.brand.Brand;
import com.compactsmartsolutions.carcatalog.entities.brand.BrandRepository;
import com.compactsmartsolutions.carcatalog.exceptions.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    private final BrandRepository brandRepository;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, BrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public Model createModel(Model model) {
        Brand brand = brandRepository.save(model.getBrand());
        model.setBrand(brand);

        return modelRepository.save(model);
    }

    @Override
    public void deleteModel(Long id) {
        modelRepository.deleteById(id);
    }

    @Override
    public Model getModelById(Long id) {
        return modelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Model with id: " + id + " not found"));
    }

    @Override
    public Model getModelByName(String name) {
        Model model;
        try {
            model = modelRepository.findByName(name);
        } catch (EntityNotFoundException exception) {
            throw new EntityNotFoundException("Model with name: " + name + " not found");
        }
        return model;
    }

    @Override
    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

}
