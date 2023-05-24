package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog,Long> {
}
