package com.example.springwebservice.domain.category;



import lombok.*;


import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Category{
    @Id
    private Integer CATEGORY_IDX;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String CATEGORY_NAME;

    @Builder
    public Category(Integer CATEGORY_IDX, String CATEGORY_NAME) {
        this.CATEGORY_IDX=CATEGORY_IDX;
        this.CATEGORY_NAME = CATEGORY_NAME;
    }

}
