package com.gokdemiruzunkaya.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

//lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

//Entity
@Entity
@Table(name = "blog")
public class BlogEntity extends BaseEntity implements Serializable {
    public static final long serialVersionUID=1L;

    //field
    private String blogHeader;
    private String blogContent;
    private String blogImage;

    //javada olsun ancak database bu attribute eklemesin
    //@Transient
    //private String justJava;

    //büyük datalar icin kullanalım.
    //@Lob
    //private Object bigData;
}
