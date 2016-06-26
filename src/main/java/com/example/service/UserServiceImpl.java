package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.UserInfo;
import com.example.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	public UserInfo getUserInfo(long userId) {
		UserInfo u = repository.getOne(userId);
		System.out.println("GOT THE USER INFO"+u.getUserId());
		return u;
	}

	public List<UserInfo> getUserList() {
		List<UserInfo> userList = repository.findAll();
		return userList;
	}

	public boolean addUser(UserInfo u) {
		if (u.getUserId() == null) {
			repository.save(u);
			return true;
		}
		return false;

	}

	@Override
	public boolean updateUser(UserInfo u) {
		System.out.println("USer ID found is "+u.getUserId());
		if (u.getUserId() != null) {
			repository.save(u);
			return true;
		}
		return false;

	}

	@Override
	public boolean deleteUserInfo(long userId) {
		repository.delete(userId);
		return true;
	}
}
