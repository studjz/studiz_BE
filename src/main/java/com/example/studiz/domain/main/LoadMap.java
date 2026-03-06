package com.example.studiz.domain.main;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
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

    @Column(name = "map_link")
    private String link;

    @Column(name = "map_major")
    private String major;


    @Builder
    public  LoadMap( String mapSubject, String mapText,String major,String link) {
        this.mapSubject = mapSubject;
        this.mapText = mapText;
        this.major= major;
        this.link = link;
    }
}
