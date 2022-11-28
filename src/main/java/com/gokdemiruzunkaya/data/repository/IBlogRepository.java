package com.gokdemiruzunkaya.data.repository;

import com.gokdemiruzunkaya.data.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlogRepository extends JpaRepository<BlogEntity,Long> {

    //Kendi Sorgumuzu yazdık

}
