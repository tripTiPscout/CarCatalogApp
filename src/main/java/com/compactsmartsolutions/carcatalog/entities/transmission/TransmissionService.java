package com.compactsmartsolutions.carcatalog.entities.transmission;

import java.util.List;

public interface TransmissionService {

    Transmission createTransmission(Transmission transmission);

    void deleteTransmission(Long id);

    Transmission getTransmissionById(Long id);

    Transmission getTransmissionByName(String name);

    List<Transmission> getAllTransmissions();

}
