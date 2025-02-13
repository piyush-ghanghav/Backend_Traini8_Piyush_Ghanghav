package com.training.backend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;
@Data
public class TrainingCenterDTO {

    @NotBlank(message = "Center name is required")

    @Size(max = 40, message = "Center name must be less than 40 characters")
    private String centerName;

    @NotBlank(message = "Center code is required")
    @Pattern(regexp = "^[A-Za-z0-9]{12}$", message = "Center code must be exactly 12 alphanumeric characters")
    private String centerCode;

    @NotNull(message = "Address is required")
    private AddressDTO address;

    @NotNull(message = "Student capacity is required")
    @Min(value = 1, message = "Student capacity must be at least 1")
    private Integer studentCapacity;

    private List<String> coursesOffered;

    @Email(message = "Invalid email format")
    private String contactEmail;

    @NotBlank(message = "Contact phone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String contactPhone;
}
