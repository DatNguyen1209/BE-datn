package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Feedback extends BaseEntities{
    @Column(name = "rating")
    private float rating;
    @Column(name = "content")
    private String content;

    @Column(name = "images")
    private String image;
    @JsonBackReference(value = "hotel-feedbacks")
    @ManyToOne
    @JoinColumn(name = "hotelId",referencedColumnName = "id")
    private Hotel hotelId;
}
