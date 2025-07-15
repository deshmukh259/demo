package com.springboot.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "user_details")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetails {

    @OneToMany(mappedBy = "userDetails", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)

    List<UserAuthority> userAuthorities;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;

}
