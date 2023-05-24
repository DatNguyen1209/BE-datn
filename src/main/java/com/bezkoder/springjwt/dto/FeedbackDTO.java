package com.bezkoder.springjwt.dto;

import com.bezkoder.springjwt.models.BaseEntities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDTO extends BaseEntities {
    private float rating;
    private String content;
    private String image;
    private Long hotelId;

}
