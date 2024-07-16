package com.BlogApplication.Bloging.application.api.service.imple;

import com.BlogApplication.Bloging.application.api.DTO.*;
import com.BlogApplication.Bloging.application.api.entity.role.Role;
import com.BlogApplication.Bloging.application.api.entity.role.RoleName;
import com.BlogApplication.Bloging.application.api.entity.user.Address;
import com.BlogApplication.Bloging.application.api.entity.user.Company;
import com.BlogApplication.Bloging.application.api.entity.user.Geo;
import com.BlogApplication.Bloging.application.api.entity.user.User;
import com.BlogApplication.Bloging.application.api.exception.APPException;
import com.BlogApplication.Bloging.application.api.exception.BadRequestException;
import com.BlogApplication.Bloging.application.api.exception.ResourceNotFoundException;
import com.BlogApplication.Bloging.application.api.exception.UnAuthorizedException;
import com.BlogApplication.Bloging.application.api.repository.PostRepository;
import com.BlogApplication.Bloging.application.api.repository.RoleRepository;
import com.BlogApplication.Bloging.application.api.repository.UserRepository;
import com.BlogApplication.Bloging.application.api.security.UserPrincipal;
import com.BlogApplication.Bloging.application.api.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImple implements UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final ModelMapper modelMapper;
  private final PostRepository postRepository;


  @Override
  public UserDTO addUser(UserDTO userDTO) {
    User user = modelMapper.map(userDTO, User.class);
    if (userRepository.existsByUsername(user.getUsername())) {
      APIResponse apiResponse = new APIResponse("User is already exist", Boolean.FALSE);
      throw new BadRequestException(apiResponse);
    }

    if (userRepository.existsByEmail(user.getEmail())) {
      APIResponse apiResponse = new APIResponse("User is already exist", Boolean.FALSE);
      throw new BadRequestException(apiResponse);
    }

    List<Role> roles = new ArrayList<>();
    roles.add(
            roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new APPException("User role is not set"))
    );
    user.setRoles(roles);
    String city = user.getAddress().getCity();
    String street = user.getAddress().getStreet();
    String suite = user.getAddress().getSuite();
    String zipCode = user.getAddress().getZipCode();

    Address address = new Address(city, street, suite, zipCode);
    Geo geo = new Geo(user.getAddress().getGeo().getLng(), user.getAddress().getGeo().getLat());
    address.setGeo(geo);
    user.setAddress(address);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    UserDTO userD = modelMapper.map(user, UserDTO.class);
    AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);
    GeoDTO geoDTO = modelMapper.map(geo, GeoDTO.class);
    addressDTO.setGeoDTO(geoDTO);
    userD.setAddressDTO(addressDTO);
    return userD;
  }

  @Override
  public UserProfile getUserProfile(String username) {
    User user = userRepository.findUserByUserName(username).
            orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    Long countOfPosts = postRepository.countOfPosts(user.getId());
    return new UserProfile(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getCreatedAt(),
            user.getEmail(), user.getAddress(), user.getPhone(), user.getWebsite(), user.getCompany(), countOfPosts);
  }

  @Override
  public UserSummary getLogInUser(UserPrincipal currentUser) {
    return new UserSummary(currentUser.getId(), currentUser.getFirstName(),
            currentUser.getLastName(), currentUser.getUsername());
  }

  @Override
  public UserIdentityAvailability checkUserNameAvailability(String userName) {
    Boolean isAvailable = !userRepository.existsByUsername(userName);
    return new UserIdentityAvailability(isAvailable);
  }

  @Override
  public UserIdentityAvailability checkEmailAvailability(String email) {
    Boolean isAvailable = !userRepository.existsByEmail(email);
    return new UserIdentityAvailability(isAvailable);
  }

  @Override
  public UserDTO updateUser(String username, UserDTO newUser, UserPrincipal currentUser) {
    User oldUser = userRepository.findUserByUserName(username).
            orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    if (oldUser.getId().equals(currentUser.getId()) || currentUser.getAuthorities().
            contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
      oldUser.setFirstName(newUser.getFirstName());
      oldUser.setLastName(newUser.getLastName());
      oldUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
      Address newAddress = modelMapper.map(newUser.getAddressDTO(), Address.class);
      oldUser.setAddress(newAddress);
      oldUser.setCompany(newUser.getCompany());
      oldUser.setWebsite(newUser.getWebsite());
      oldUser.setPhone(newUser.getPhone());
      User savedUser = userRepository.save(oldUser);
      return modelMapper.map(savedUser, UserDTO.class);
    }
    APIResponse apiResponse = new APIResponse("You don't have permission to update user profile of " + username,
            Boolean.FALSE);
    throw new UnAuthorizedException(apiResponse);
  }

  @Override
  public APIResponse deleteUser(String username, UserPrincipal currentUser) {
    User user = userRepository.findUserByUserName(username).
            orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    if (!currentUser.getId().equals(user.getId()) || !currentUser.getAuthorities().
            contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
      APIResponse apiResponse = new APIResponse("You don't have permission to update user profile of " + username,
              Boolean.FALSE);
      throw new UnAuthorizedException(apiResponse);
    }
    userRepository.deleteById(user.getId());
    APIResponse apiResponse = new APIResponse("You successfully delete the User",
            Boolean.TRUE);

    return apiResponse;
  }

  @Override
  public APIResponse giveAdmin(String username) {
    User user = userRepository.findUserByUserName(username).
            orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    List<Role> roles = new ArrayList<>();
    roles.add(
            roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(() -> new APPException("User role is not set"))
    );
    user.setRoles(roles);
    userRepository.save(user);
    return new APIResponse("You give admin role to user: " + username, Boolean.TRUE);
  }

  @Override
  public APIResponse removeAdmin(String username) {
    User user = userRepository.findUserByUserName(username).
            orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    List<Role> roles = new ArrayList<>();
    roles.add(
            roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new APPException("User role is not set"))
    );
    user.setRoles(roles);
    userRepository.save(user);
    return new APIResponse("You took admin role from user: " + username, Boolean.TRUE);
  }

  @Override
  public UserProfile setOrUpdateInfo(UserPrincipal currentUser, InfoRequest infoRequest) {
    User user = userRepository.findUserByUserName(currentUser.getUsername()).
            orElseThrow(() -> new ResourceNotFoundException("User", "username", currentUser.getUsername()));
    Geo geo = new Geo(infoRequest.getLng(),infoRequest.getLat());
    Address address = new Address(infoRequest.getCity(), infoRequest.getStreet(),infoRequest.getSuite(),
            infoRequest.getZipCode());
    address.setGeo(geo);
    Company company = new Company(infoRequest.getCompanyName(), infoRequest.getCatchPhrase(),infoRequest.getBs());
    if(user.getId().equals(currentUser.getId()) || currentUser.getAuthorities().
            contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))){
      user.setAddress(address);
      user.setCompany(company);
      user.setWebsite(infoRequest.getWebsite());
      user.setPhone(infoRequest.getPhone());
      User savedUser = userRepository.save(user);
      Long postCount = postRepository.countOfPosts(user.getId());
      return new UserProfile(user.getId(),user.getUsername(),user.getFirstName(),user.getLastName(),user.getCreatedAt()
      ,user.getEmail(),user.getAddress(),user.getPhone(),user.getWebsite(),user.getCompany(),postCount);
    }

   APIResponse apiResponse = new APIResponse("You don't have permission to update the info of: "+user.getUsername(),
           Boolean.FALSE);
    throw new UnAuthorizedException(apiResponse);
  }


}
