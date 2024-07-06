package com.BlogApplication.Bloging.application.api.entity.user;

import com.BlogApplication.Bloging.application.api.entity.Album;
import com.BlogApplication.Bloging.application.api.entity.Comment;
import com.BlogApplication.Bloging.application.api.entity.Post;
import com.BlogApplication.Bloging.application.api.entity.Todo;
import com.BlogApplication.Bloging.application.api.entity.audit.UserDataAudit;
import com.BlogApplication.Bloging.application.api.entity.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "users",uniqueConstraints = {@UniqueConstraint(columnNames ={"username"} ),
        @UniqueConstraint(columnNames = "email")})
public class User extends UserDataAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank
  @Size(max = 40,message = "The first name must be 40 characters at max")
  private String firstName;

  @NotBlank
  @Size(max = 40,message = "The last name must be 40 characters at max")
  private String lastName;

  @NotBlank
  @Size(max = 15,message = "The username max size must be 15")
  @Pattern(regexp = "^[a-zA-Z]{5,15}$",message = "The username must contains characters only")
  private String username;

  @NotBlank
  @Size(max = 50)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  @Email
  @NotBlank
  @NaturalId
  @Size(max = 40)
  private String email;

  private String phone;
  private String website;

  @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
  @JoinColumn(name = "address_id")
  private Address address;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
  inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
  private List<Role> roles;

  @JsonIgnore
  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
  private List<Post> posts;

  @JsonIgnore
  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
  private List<Comment> comments;

  @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
  @JoinColumn(name = "company_id")
  private Company company;

  @JsonIgnore
  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
  private List<Album> albums;

  @JsonIgnore
  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
  private List<Todo> todos;

  public User(String firstName, String lastName, String username, String password, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
    this.email = email;
  }

  public List<Role> getRoles() {
    return roles == null ? null : new ArrayList<>(this.roles);
  }

  public void setRoles(List<Role> roles) {
    if(roles == null){
      this.roles = null;
    }else{
      this.roles = Collections.unmodifiableList(roles);
    }
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

  public List<Comment> getComments() {
    return comments== null ? null : new ArrayList<>(this.comments);
  }

  public void setComments(List<Comment> comments) {
    if(comments == null){
      this.comments = null;
    }else{
      this.comments= Collections.unmodifiableList(comments);
    }
  }

  public List<Album> getAlbums() {
    return albums== null ? null : new ArrayList<>(this.albums);
  }

  public void setAlbums(List<Album> albums) {
    if(albums == null){
      this.albums = null;
    }else{
      this.albums= Collections.unmodifiableList(albums);
    }
  }

  public List<Todo> getTodos() {
    return todos== null ? null : new ArrayList<>(this.todos);
  }

  public void setTodos(List<Todo> todos) {
    if(todos == null){
      this.todos= null;
    }else{
      this.todos= Collections.unmodifiableList(todos);
    }
  }
}
