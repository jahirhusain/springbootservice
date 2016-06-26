package com.example.api;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.UserInfo;
import com.example.service.UserService;

@RestController
public class UserManagementController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/api/userInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserInfo>> getUserList() {
		List<UserInfo> userList = userService.getUserList();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/userInfo/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserInfo> getUserInfo(@PathVariable("userId") long id) {
		try {
			UserInfo u = userService.getUserInfo(id);
			if (u != null) {
				return new ResponseEntity<UserInfo>(u, HttpStatus.OK);
			}
		} catch(EntityNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/api/userInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserInfo> addUserInfo(@RequestBody UserInfo u) {
		try {
			boolean response = userService.addUser(u);
			if (response) {
				return new ResponseEntity<UserInfo>(u, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			// log.info("Exception while adding info");
			 e.printStackTrace();
		}
		return new ResponseEntity<UserInfo>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/api/userInfo", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserInfo> updateUserInfo(@RequestBody UserInfo u) {
		try {
			boolean response = userService.updateUser(u);
			if (response) {
				return new ResponseEntity<UserInfo>(u, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<UserInfo>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/api/userInfo/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateUserInfo(@PathVariable("userId") long id) {
		try {
			boolean response = userService.deleteUserInfo(id);
			if (response) {
				return new ResponseEntity<String>("Data deleted Successfully", HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
