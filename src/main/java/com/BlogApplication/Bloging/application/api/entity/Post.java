package com.BlogApplication.Bloging.application.api.entity;

import com.BlogApplication.Bloging.application.api.entity.audit.UserDataAudit;
import com.BlogApplication.Bloging.application.api.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
@Table(name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = "title")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Post extends UserDataAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = 20, message = "Your post title must not exceed 20 characters")
  private String title;

  @Size(max = 500, message = "Your post must not exceed 500 characters")
  private String body;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @JsonIgnore
  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
  private List<Tag> tags;

  @JsonIgnore
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<Comment> getComments() {
    return comments == null ? null : new ArrayList<>(comments);
  }

  public void setComments(List<Comment> comments) {
    if(comments == null){
      this.comments = null;
    }else{
      this.comments = Collections.unmodifiableList(comments);
    }
  }

  public List<Tag> getTags() {
    return tags == null ? null : new ArrayList<>(tags);
  }

  public void setTags(List<Tag> tags) {
    if(tags == null){
      this.tags = null;
    }else{
      this.tags = Collections.unmodifiableList(tags);
    }
  }
}
