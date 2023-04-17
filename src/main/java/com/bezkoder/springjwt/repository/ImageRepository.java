package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Images,Long> {


}
