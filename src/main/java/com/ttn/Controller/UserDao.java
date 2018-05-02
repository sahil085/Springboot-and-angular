package com.ttn.Controller;


public interface UserDao {

	User findById(int id);
	
	User findBySSO(String ssoz);
	
}

