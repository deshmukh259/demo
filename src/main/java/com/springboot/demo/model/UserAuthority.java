package com.springboot.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_authority")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String name;

    @ManyToOne
    @JoinColumn(name = "userName", referencedColumnName = "userName",
            insertable = false, updatable = false)
    private UserDetails userDetails;
}
