package com.gokdemiruzunkaya.ui.mvc.impl;

import com.gokdemiruzunkaya.bean.ModelMapperBean;
import com.gokdemiruzunkaya.business.dto.BlogDto;
import com.gokdemiruzunkaya.data.entity.BlogEntity;
import com.gokdemiruzunkaya.data.repository.IBlogRepository;
import com.gokdemiruzunkaya.exception.ResourceNotFoundException;
import com.gokdemiruzunkaya.ui.mvc.IBlogController;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

//lombok
@RequiredArgsConstructor
@Log4j2

//Controller
@Controller
//@RequestMapping("/controller")
public class BlogController implements IBlogController {


    //Inject
    private final IBlogRepository repository;
    private final ModelMapperBean modelMapperBean;

    // SPEED DATA
    // http://localhost:3333/speedData
    @Override
    @GetMapping("/speedData")
    public String createSpeedData(Model model) {
        int counter = 0;
        for (int i = 1; i <= 5; i++) {
            UUID uuid = UUID.randomUUID();
            BlogEntity registerEntity = BlogEntity.builder()
                    .blogHeader("başlık " + i).blogContent("içerik "+i).blogImage("içerik "+i).build();
            repository.save(registerEntity);
            counter++;
        }
        model.addAttribute("key_dataset", counter + " tane blog Entity oluşturuldu");
        return "redirect:/blog/list";
    }

    // SPEED DELETE
    // http://localhost:3333/speedData
    @Override
    @GetMapping("/speedDelete")
    public String deleteSpeedData(Model model) {
        repository.deleteAll();
        return "redirect:/blog/list";
    }



    // CREATE 2497-2588
    // http://localhost:3333/blog/create
    @Override
    @GetMapping("/blog/create")
    public String validationGetBlog(Model model) {
        model.addAttribute("key_blog", new BlogDto());
        return "blog_create";
    }

    //CREATE
    // http://localhost:3333/blog/create
    @Override
    @PostMapping("/blog/create")
    public String validationPostBlog(@Valid @ModelAttribute("key_blog") BlogDto blogDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.error("HATA: " + bindingResult);
            return "blog_create";
        }
        //eğer valiadtion bir hata yoksa
        model.addAttribute("blog_success", "Üye Kaydı Başarılı " + blogDto);
        log.info("Başarılı " + blogDto);


        //model mapper
        BlogEntity registerEntity = modelMapperBean.modelMapperMethod().map(blogDto, BlogEntity.class);
        //model mapper yerine biz yazarsak
        //RegisterEntity registerEntity=new RegisterEntity();
        //registerEntity.setId(registerDto.getId());
        //registerEntity.setName(registerDto.getName());
        //registerEntity.setSurname(registerDto.getSurname());
        //registerEntity.setEmail(registerDto.getEmail());
        //registerEntity.setPassword(registerDto.getPassword());
        try {
            if (registerEntity != null) {
                repository.save(registerEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //File
        return "success";
    }


    // LIST
    // http://localhost:3333/blog/list
    @Override
    @GetMapping("/blog/list")
    public String blogList(Model model) {
        List<BlogEntity> list = repository.findAll();
        model.addAttribute("key_blog", list);
        list.forEach((temp) -> {
            System.out.println(temp);
        });
        return "blog_list";
    }

    // FIND
    // http://localhost:3333/blog/find
    // http://localhost:3333/blog/find/1
    @Override
    @GetMapping( "/blog/find/{id}")
    public String blogFindById(@PathVariable(name = "id") Long id, Model model) {
        //1.YOL
        /*Optional<RegisterEntity> findData = repository.findById(id);
        if (findData.isPresent()) {
            return "Data: " + findData.get();
        } else {
            return id + " numaralı Data: Bulunamadı  ";
        }*/

        //2.YOL
        BlogEntity registerEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " nolu kayıt yoktur"));
        model.addAttribute("blog_find", registerEntity);
        return "blog_detail_page";
    }

    // DELETE
    // http://localhost:3333/blog/delete
    // http://localhost:3333/blog/delete/1
    @Override
    @GetMapping({"/blog/delete", "/blog/delete/{id}"})
    public String blogDeleteById(@PathVariable(name = "id", required = false) Long id, Model model) {
        BlogEntity registerEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " nolu kayıt yoktur"));
        if (registerEntity != null) {
            repository.deleteById(id);
            model.addAttribute("key_delete", registerEntity + " silindi");
        } else
            model.addAttribute("key_delete", id + " numaralı veri yoktur");
        return "redirect:/blog/list";
    }

    //UPDATE
    // http://localhost:3333/update/blog
    @Override
    @GetMapping("/blog/update/{id}")
    public String updateGetBlog(@PathVariable(name = "id") Long id, Model model) {
        BlogEntity registerEntityFind = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " nolu kayıt yoktur"));
        if (registerEntityFind != null) {
            model.addAttribute("key_update", registerEntityFind);
        } else
            model.addAttribute("key_update", id + " numaralı veri yoktur");
        return "blog_update";
    }

    //UPDATE
    // http://localhost:3333/update/blog
    @Override
    @PostMapping("/blog/update/{id}")
    public String updatePostBlog(@PathVariable(name = "id") Long id, @Valid @ModelAttribute("key_update") BlogDto blogDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.error("HATA: " + bindingResult);
            return "blog_update";
        }
        BlogEntity registerEntity = modelMapperBean.modelMapperMethod().map(blogDto, BlogEntity.class);
        try {
            if (registerEntity != null) {
                repository.save(registerEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/blog/list";
    }
}
