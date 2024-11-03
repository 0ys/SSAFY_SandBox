package com.ssafy.sandbox.paging.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@Getter @Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Article {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    private int userId;

    private String title;
//    private Boolean completed;
    private Date createdAt;

}
