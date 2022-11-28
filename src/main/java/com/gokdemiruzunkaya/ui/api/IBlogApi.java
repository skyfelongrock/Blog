package com.gokdemiruzunkaya.ui.api;
import com.gokdemiruzunkaya.business.dto.BlogDto;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface IBlogApi {
    //CREATE
    ResponseEntity<?> createBlog(BlogDto registerDto);

    //LIST
    ResponseEntity<List<BlogDto>>  listBlog();

    //FIND
    ResponseEntity<BlogDto> findBlog(Long id);


    //UPDATE
    ResponseEntity<BlogDto>  updateBlog(Long id, BlogDto registerDto);

    //DELETE
    ResponseEntity <Map<String, Boolean>> deleteBlog( Long id);
}
