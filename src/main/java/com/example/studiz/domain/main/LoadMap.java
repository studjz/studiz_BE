package com.example.studiz.domain.main;

import com.example.studiz.domain.problem.Problem;
import jakarta.persistence.*;

@Table(name = "tbl_LoadMap")
public class LoadMap
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "map_subject", nullable = false)
    private String mapSubject;

    @Column(name = "map_text", nullable = false)
    private String mapText;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Problem problem;



}
