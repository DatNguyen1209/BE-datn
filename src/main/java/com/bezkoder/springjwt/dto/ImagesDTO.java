package com.bezkoder.springjwt.dto;

import com.bezkoder.springjwt.models.BaseEntities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImagesDTO extends BaseEntities {
    private String imageNames;
}
