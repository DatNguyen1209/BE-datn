package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
}
