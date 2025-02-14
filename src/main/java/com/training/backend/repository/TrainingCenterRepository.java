package com.training.backend.repository;

import com.training.backend.model.TrainingCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface TrainingCenterRepository extends JpaRepository<TrainingCenter, Long> {
    Optional<TrainingCenter> findByCenterCode(String centerCode);
    boolean existsByCenterCode(String centerCode);
    
    List<TrainingCenter> findByAddressCityIgnoreCase(String city);
    List<TrainingCenter> findByAddressStateIgnoreCase(String state);
    List<TrainingCenter> findByStudentCapacityGreaterThanEqual(Integer minCapacity);
}
