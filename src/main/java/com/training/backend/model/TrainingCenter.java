package com.training.backend.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="training_centers")
public class TrainingCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank(message = "Center name is mandatory")
    @Size(max = 40, message = "Center name must be less than 40 characters.")
    private String centerName;

    @NotBlank(message = "Center code is mandatory")
    @Pattern(regexp = "^[A-Za-z0-9]{12}$", message = "Center code must be exactly 12 alphanumeric characters")
    private String centerCode;

//    @NotBlank(message = "Address is mandatory")
    @Valid
    @Embedded
    private Address address;

    private Integer studentCapacity;

    @ElementCollection
    private List<@NotBlank(message="Course Name cannot be blank") String> courseOffered;

    private Long createdOn;

    @Email(message = "Invalid email format")
    private String contactEmail;

    @NotBlank(message = "Contact phone is mandatory")
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact phone must be 10 digits")
    private String contactPhone;





}
