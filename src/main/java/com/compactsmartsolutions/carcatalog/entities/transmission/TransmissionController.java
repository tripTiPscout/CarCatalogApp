package com.compactsmartsolutions.carcatalog.entities.transmission;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/transmissions")
public class TransmissionController {

    private final TransmissionService transmissionService;

    @Autowired
    public TransmissionController(TransmissionService transmissionService) {
        this.transmissionService = transmissionService;
    }

    @PostMapping("/transmission")
    public ResponseEntity<Transmission> createTransmission(@RequestBody Transmission transmission) {
        Transmission createdTransmission = transmissionService.createTransmission(transmission);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTransmission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransmission(@PathVariable("id") Long id) {
        transmissionService.deleteTransmission(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Transmission> getTransmissionById(@PathVariable("id") Long id) {
        Transmission transmission = transmissionService.getTransmissionById(id);
        return ResponseEntity.ok(transmission);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Transmission> getTransmissionByName(@PathVariable("name") String name) {
        Transmission transmission = transmissionService.getTransmissionByName(name);
        return ResponseEntity.ok(transmission);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Transmission>> getAllTransmissions() {
        List<Transmission> transmissions = transmissionService.getAllTransmissions();
        return ResponseEntity.ok(transmissions);
    }

}
