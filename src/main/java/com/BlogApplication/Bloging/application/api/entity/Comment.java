package com.BlogApplication.Bloging.application.api.entity;

import com.BlogApplication.Bloging.application.api.entity.audit.UserDataAudit;
import com.BlogApplication.Bloging.application.api.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table
public class Comment extends UserDataAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(min = 4,max = 50,message = "comment name has to be between 4 and 50 characters")
  private String name;

  @NotBlank
  @Size(min = 10,message = "The comment body must be 10 characters at least")
  private String body;

  @Email
  @NaturalId
  private String email;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id")
  private Post post;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  public Comment(@NotBlank @Size(min = 10,message = "Comment must be 10 characters length at least")  String body) {
    this.body = body;
  }

  @JsonIgnore
  public User getUser() {
    return user;
  }

  @JsonIgnore
  public Post getPost() {
    return post;
  }
}
