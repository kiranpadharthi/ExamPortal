package com.zoho.examportal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email(message = "Please provide a valid email")
    @NotBlank(message = "Please fill the email")
    private String emailId;

    @NotBlank(message = "Please fill the name")
    private String name;

    @Size(min = 10,max = 10, message ="Phone number must be of 10 digits")
    private String phoneNumber;

    public String getName() {
        return name;
    }
}
