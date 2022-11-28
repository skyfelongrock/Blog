package com.gokdemiruzunkaya.ui.api.impl;

import com.gokdemiruzunkaya.business.dto.BlogDto;
import com.gokdemiruzunkaya.business.services.IBlogServices;
import com.gokdemiruzunkaya.ui.api.IBlogApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//lombok
@Log4j2
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class BlogApiImpl implements IBlogApi {

    //injection services
    private final IBlogServices services;

    //http://localhost:3333/api/v1/blog/create
    //CREATE
    @Override
    @PostMapping("blog/create")
    public ResponseEntity<?>  createBlog(@Valid @RequestBody BlogDto blogDto) {
        services.createBlog(blogDto);
        return ResponseEntity.ok(blogDto);
    }

    //http://localhost:3333/api/v1/blog/list
    //LIST
    @Override
    @GetMapping("blog/list")
    public ResponseEntity<List<BlogDto>>  listBlog() {
        return ResponseEntity.ok(services.listBlog());
    }


    //http://localhost:3333/api/v1/blog/find/1
    //FIND
    @Override
    @GetMapping("blog/find/{id}")
    public ResponseEntity<BlogDto> findBlog(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(services.findBlog(id));
    }

    //http://localhost:3333/api/v1/blog/update/1
    //UPDATE
    @Override
    @PutMapping("blog/update/{id}")
    public ResponseEntity<BlogDto>  updateBlog(@PathVariable(name = "id") Long id, @Valid @RequestBody BlogDto blogDto) {
        services.updateBlog(id,blogDto);
        return ResponseEntity.ok(blogDto);
    }


    //http://localhost:3333/api/v1/blog/delete/1
    //DELETE
    @Override
    @DeleteMapping("blog/delete/{id}")
    public ResponseEntity <Map<String, Boolean>> deleteBlog(@PathVariable(name = "id") Long id) {
        services.deleteBlog(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok( response);
    }

}
