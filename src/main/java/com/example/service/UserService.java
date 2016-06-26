package com.example.service;

import java.util.List;

import com.example.models.UserInfo;

public interface UserService {

	public UserInfo getUserInfo(long userId);
	
	public List<UserInfo> getUserList();
	
	public boolean addUser(UserInfo u);
	
	public boolean updateUser(UserInfo u);
	
	public boolean deleteUserInfo(long userId);
	
}
