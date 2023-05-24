package com.bezkoder.springjwt.dto;

import com.bezkoder.springjwt.models.BaseEntities;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BlogDTO extends BaseEntities {
    private String title;
    private String content;
    private  String image;
    private Boolean status = true;
}
