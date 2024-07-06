package com.BlogApplication.Bloging.application.api.entity;

import com.BlogApplication.Bloging.application.api.entity.audit.UserDataAudit;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "categories")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "Id")
public class Category extends UserDataAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,orphanRemoval = true)
  private List<Post> posts;

  public Category(String name) {
    this.name = name;
  }

  public List<Post> getPosts() {
    return posts == null ? null : new ArrayList<>(this.posts);
  }

  public void setPosts(List<Post> posts) {
    if(posts == null){
      this.posts = null;
    }else{
      this.posts = Collections.unmodifiableList(posts);
    }
  }
}
