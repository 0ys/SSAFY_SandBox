package com.ssafy.sandbox.paging.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter @Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Article {

    @Id
    private int id;
    private int userId;

    private String title;
    private Boolean completed;

}
