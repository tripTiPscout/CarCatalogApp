package com.compactsmartsolutions.carcatalog.entities.brand;

import com.compactsmartsolutions.carcatalog.exceptions.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public Brand getBrandById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Brand with id: " + id + " not found"));
    }

    @Override
    public Brand getBrandByName(String name) {
        Brand brand;
        try {
            brand = brandRepository.findByName(name);
        } catch (EntityNotFoundException exception) {
            throw new EntityNotFoundException("Brand with name: " + name + " not found");
        }
        return brand;
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

}
