package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.BlogDTO;
import com.bezkoder.springjwt.dto.PageDTO;
import com.bezkoder.springjwt.models.Blog;
import com.bezkoder.springjwt.repository.BlogRepository;
import com.bezkoder.springjwt.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
@CrossOrigin
public class BlogController {
    @Autowired
    BlogRepository blogRepository;
    @Autowired
    IBlogService iBlogService;

    @PostMapping("/create")
    public void  Create(@RequestBody BlogDTO dto){
        iBlogService.save(dto);
    }
    @GetMapping("/getbyid/{id}")
    public Blog getById(@PathVariable Long id){
        return blogRepository.findById(id).orElseThrow(()
        -> new RuntimeException("Not Found!!!"));
    }
    @GetMapping("/getblogwithpage")
    public PageDTO<BlogDTO> GetBlogWithPage(@RequestParam("page") int page, @RequestParam("size") int size){
        return iBlogService.findAllWithPageable(page,size);
    }
    @GetMapping("/getall")
    public List<Blog> getAllPage(){
        return blogRepository.findAll();
    }
    @PutMapping("/update/{id}")
    public BlogDTO update(@RequestBody BlogDTO dto, @PathVariable Long id){
        dto.setId(id);
        return iBlogService.update(dto,id);
    }
    @PutMapping("/delete/{id}")
    public  void deleteById(@PathVariable Long id){
        iBlogService.delete(id);
    }
}
