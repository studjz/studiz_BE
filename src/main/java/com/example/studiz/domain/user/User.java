package com.example.studiz.domain.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,  nullable = false, name = "school_id")
    private String schoolId;

    @Column(nullable = false, length = 16, name = "user_name")
    private String username;

    @Column(nullable = false , length = 100,name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String schoolId, String username, String password, Role role) {
        this.schoolId = schoolId;
        this.username = username;
        this.password = password;
        this.role = (role!=null)? role: Role.USER;
    }

    @Column(nullable = false, name = "correct-rate")
    private Long correctRate;

    @Column(nullable = false, name="progress-rate")
    private Long progressRate;



}
