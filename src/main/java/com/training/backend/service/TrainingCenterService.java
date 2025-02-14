package com.training.backend.service;


import com.training.backend.model.Address;
import com.training.backend.model.TrainingCenter;
import com.training.backend.dto.TrainingCenterDTO;
import com.training.backend.repository.TrainingCenterRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;

import jakarta.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingCenterService {
    private final TrainingCenterRepository repository;

    @Autowired
    public TrainingCenterService( TrainingCenterRepository repository){
        this.repository = repository;
    }

    @Transactional
    public TrainingCenter createTrainingCenter(TrainingCenterDTO dto){
        if(repository.existsByCenterCode(dto.getCenterCode())){
            throw new ValidationException("Training center with this code already exists");
        }

    TrainingCenter center = new TrainingCenter();

        center.setCenterName(dto.getCenterName());
        center.setCenterCode(dto.getCenterCode());
        center.setStudentCapacity(dto.getStudentCapacity());
        center.setCourseOffered(dto.getCoursesOffered());
        center.setContactEmail(dto.getContactEmail());
        center.setContactPhone(dto.getContactPhone());

        center.setCreatedOn(System.currentTimeMillis());

    Address address = new Address();

        address.setDetailedAddress(dto.getAddress().getDetailedAddress());
        address.setCity(dto.getAddress().getCity());
        address.setState(dto.getAddress().getState());
        address.setPincode(dto.getAddress().getPincode());


        center.setAddress(address);

    return repository.save(center);
    }
    public List<TrainingCenter>getAllTrainingCenters(){
        return repository.findAll();
    }

    public List<TrainingCenter> getAllTrainingCenters(String city, String state, Integer minCapacity) {
        List<TrainingCenter> centers = repository.findAll();
        
        return centers.stream()
            .filter(center -> (city == null || center.getAddress().getCity().equalsIgnoreCase(city)))
            .filter(center -> (state == null || center.getAddress().getState().equalsIgnoreCase(state)))
            .filter(center -> (minCapacity == null || center.getStudentCapacity() >= minCapacity))
            .collect(Collectors.toList());
    }
}
