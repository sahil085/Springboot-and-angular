package com.ttn.Controller;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl  implements UserDao  {

	@Autowired
	UserRepo userRepo;

	public User findById(int id) {
		return null;
	}

	public User findBySSO(String email) {

		return userRepo.findUserByEmail(email);
	}


}
