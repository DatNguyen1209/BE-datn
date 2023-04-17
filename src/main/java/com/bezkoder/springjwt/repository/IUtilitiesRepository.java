package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUtilitiesRepository extends JpaRepository<Service,Long> {
}
