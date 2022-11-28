package com.gokdemiruzunkaya.business.services;

import com.gokdemiruzunkaya.business.dto.BlogDto;
import com.gokdemiruzunkaya.data.entity.BlogEntity;

import java.util.List;
import java.util.Map;

public interface IBlogServices {

    BlogDto entityToDto(BlogEntity registerEntity);

    BlogEntity dtoToEntity(BlogDto registerDto);

    //CREATE
    BlogDto createBlog(BlogDto registerDto);

    //LIST
    List<BlogDto> listBlog();

    //FIND
    BlogDto findBlog(Long id);

    //DELETE
    Map<String,Boolean> deleteBlog( Long id);

    //UPDATE
    BlogDto updateBlog(Long id, BlogDto registerDto);
}
