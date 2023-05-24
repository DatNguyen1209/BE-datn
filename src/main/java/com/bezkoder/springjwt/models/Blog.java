package com.bezkoder.springjwt.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Setter
@Getter
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Blog extends BaseEntities{
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private  String content;
    @Column(name = "image")
    private String image;
    @Column(name = "isStatus")
    private Boolean status = true;
}
