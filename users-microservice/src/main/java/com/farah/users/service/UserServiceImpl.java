package com.farah.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farah.users.entities.Role;
import com.farah.users.entities.User;
import com.farah.users.repos.RoleRepository;
import com.farah.users.repos.UserRepository;

@Transactional
@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRep;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;


	
	
	@Override
	public User saveUser(User user) {
	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	

	@Override
	public User findUserByUsername(String username) {
		
		return  userRepository.findByUsername(username);
	}

	@Override
	public Role addRole(Role role) {
		
		return roleRep.save(role);
	}

	@Override
	public User addRoleToUser(String username, String rolename) {
	User u = userRepository.findByUsername(username);
	Role rol = roleRep.findByRole(rolename);
	
     u.getRoles().add(rol);
	 //userRepository.save(u);
		return u;
	}


	@Override
	public List<User> findAllUsers() {
		
		return  userRepository.findAll();
	}

}
