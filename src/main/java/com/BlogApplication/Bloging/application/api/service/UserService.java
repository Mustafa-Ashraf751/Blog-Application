package com.BlogApplication.Bloging.application.api.service;


import com.BlogApplication.Bloging.application.api.DTO.*;
import com.BlogApplication.Bloging.application.api.security.UserPrincipal;

public interface UserService {

 UserDTO addUser(UserDTO userDTO);

UserProfile getUserProfile(String username);

UserSummary getLogInUser(UserPrincipal currentUser);

UserIdentityAvailability checkUserNameAvailability(String userName);

UserIdentityAvailability checkEmailAvailability(String email);

UserDTO updateUser(String username,UserDTO userDTO,UserPrincipal userPrincipal);

APIResponse deleteUser(String username, UserPrincipal currentUser);

APIResponse giveAdmin(String username);

APIResponse removeAdmin(String username);

UserProfile setOrUpdateInfo(UserPrincipal currentUser, InfoRequest infoRequest);

}
