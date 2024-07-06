package com.BlogApplication.Bloging.application.api.entity;

import com.BlogApplication.Bloging.application.api.entity.audit.UserDataAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "photos",uniqueConstraints = {@UniqueConstraint(columnNames = "title")})
public class Photo extends UserDataAudit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String title;

  @NotBlank
  private String url;

  @NotBlank
  private String thumbnailUrl;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "album_id")
  private Album album;

  public Photo(String title, String url, String thumbnailUrl, Album album) {
    this.title = title;
    this.url = url;
    this.thumbnailUrl = thumbnailUrl;
    this.album = album;
  }

  @JsonIgnore
  public Album getAlbum() {
    return album;
  }
}
