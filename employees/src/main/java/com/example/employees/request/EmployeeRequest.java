package com.example.employees.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmployeeRequest {


    @NotBlank(message = "cannot be empty")
    @Size(min = 1, max = 100, message = "max 50 min 10")
    private String firstName;

    @NotBlank(message = "cannot be empty")
    @Size(min = 1, max = 100, message = "max 50 min 10")
    private String lastName;

    @NotBlank(message = "cannot be empty")
    @Email(message = "please provide valid email")
    private String email;

    public EmployeeRequest(String firstName, String email, String lastName) {
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
