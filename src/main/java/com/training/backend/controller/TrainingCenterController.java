package com.training.backend.controller;

import com.training.backend.dto.TrainingCenterDTO;
import com.training.backend.model.TrainingCenter;
import com.training.backend.service.TrainingCenterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import java.util.Optional;

@RestController
@RequestMapping("api/training-centers")
public class TrainingCenterController {

    private final TrainingCenterService service;

    @Autowired
    public TrainingCenterController(TrainingCenterService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TrainingCenter> createTrainingCenter(
            @Valid
            @RequestBody
            TrainingCenterDTO trainingCenterDTO
    ){
        TrainingCenter newCenter = service.createTrainingCenter(trainingCenterDTO);

        return new ResponseEntity<>( newCenter, HttpStatus.CREATED );
    }

    @GetMapping
    public ResponseEntity<List<TrainingCenter>> getAllTrainingCenters(
//            @RequestParam(required = false) String city,
//            @RequestParam(required = false) String state,
//            @RequestParam(required = false) Integer minCapacity
    ){
        List<TrainingCenter> centers = service.getAllTrainingCenters();
        return new ResponseEntity< >(centers, HttpStatus.OK);
    }

}
