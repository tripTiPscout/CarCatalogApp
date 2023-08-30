package com.compactsmartsolutions.carcatalog.entities.transmission;

import com.compactsmartsolutions.carcatalog.exceptions.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransmissionServiceImpl implements TransmissionService {

    private final TransmissionRepository transmissionRepository;

    @Autowired
    public TransmissionServiceImpl(TransmissionRepository transmissionRepository) {
        this.transmissionRepository = transmissionRepository;
    }

    @Override
    public Transmission createTransmission(Transmission transmission) {
        return transmissionRepository.save(transmission);
    }

    @Override
    public void deleteTransmission(Long id) {
        transmissionRepository.deleteById(id);
    }

    @Override
    public Transmission getTransmissionById(Long id) {
        return transmissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transmission with id: " + id + " not found"));
    }

    @Override
    public Transmission getTransmissionByName(String name) {
        Transmission transmission;
        try {
            transmission = transmissionRepository.findByName(name);
        } catch (EntityNotFoundException exception) {
            throw new EntityNotFoundException("Transmission with name: " + name + " not found");
        }
        return transmission;
    }

    @Override
    public List<Transmission> getAllTransmissions() {
        return transmissionRepository.findAll();
    }

}
