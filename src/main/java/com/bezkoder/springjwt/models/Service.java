package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "service")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Service extends BaseEntities {
//    private String serviceName;
    @Column(name = "url")
    private String url;
    @Column(name = "is_status")
    private boolean isStatus = true;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "hotel_service", referencedColumnName = "id")
    private Hotel hotel;
}
