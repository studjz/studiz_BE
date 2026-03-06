package com.example.studiz.domain.userloadmap;

import com.example.studiz.domain.problem.Problem;
import com.example.studiz.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tbl_user_map")
public class UserLoadMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    private List<User> user = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<Problem> problem = new ArrayList<>();

}
