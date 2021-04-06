package com.itm.legbook.com.itm.legbook.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userId;
    @NotBlank(message="Firstname is required")
    private String firstName;
    @NotBlank(message="Lastname is required")
    private String lastName;
    @NotBlank(message="Password is required")
    private String password;
    @Email
    @NotEmpty(message="Email is required")
    private String email;
    private Instant created;
    private boolean activated;
}
