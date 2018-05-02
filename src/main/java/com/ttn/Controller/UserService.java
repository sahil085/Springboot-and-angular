package com.ttn.Controller;


public interface UserService {

	User findById(int id);
	
	User findBySso(String sso);
	
}