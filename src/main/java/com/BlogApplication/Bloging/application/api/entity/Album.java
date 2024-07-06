package com.BlogApplication.Bloging.application.api.entity;

import com.BlogApplication.Bloging.application.api.entity.audit.UserDataAudit;
import com.BlogApplication.Bloging.application.api.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "albums",uniqueConstraints = {@UniqueConstraint(columnNames = "title")})
public class Album extends UserDataAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String title;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "album",cascade = CascadeType.ALL,orphanRemoval = true)
  private List<Photo> photos;

  @JsonIgnore
  public User getUser() {
    return user;
  }

  public List<Photo> getPhotos() {
    return photos == null ? null : new ArrayList<>(this.photos);
  }

  public void setPhotos(List<Photo> photos) {
    if(photos == null){
      this.photos = null;
    }else{
      this.photos = Collections.unmodifiableList(photos);
    }
  }
}
