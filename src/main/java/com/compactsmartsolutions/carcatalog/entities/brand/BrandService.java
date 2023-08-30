package com.compactsmartsolutions.carcatalog.entities.brand;

import java.util.List;

public interface BrandService {

    Brand createBrand(Brand brand);

    void deleteBrand(Long id);

    Brand getBrandById(Long id);

    Brand getBrandByName(String name);

    List<Brand> getAllBrands();

}
