package com.gokdemiruzunkaya.ui.mvc;

import com.gokdemiruzunkaya.business.dto.BlogDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

public interface IBlogController {
    public String createSpeedData(Model model);
    public String deleteSpeedData(Model model);
    public String validationGetBlog(Model model);
    public String validationPostBlog(BlogDto blogDto, BindingResult bindingResult, Model model);
    public String blogList(Model model);
    public String blogFindById(Long id, Model model);
    public String blogDeleteById( Long id, Model model);
    public String updateGetBlog(Long id, Model model);
    public String updatePostBlog(Long id, BlogDto blogDto, BindingResult bindingResult, Model model);
}

