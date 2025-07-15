package com.springboot.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Table(name="user_details")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;

    @OneToMany(mappedBy = "userDetails", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL )

    List<UserAuthority> userAuthorities;

}
