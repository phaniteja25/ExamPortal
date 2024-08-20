package com.examportal.backend;

import com.examportal.backend.model.Roles;
import com.examportal.backend.model.UserRoles;
import com.examportal.backend.model.Users;
import com.examportal.backend.service.UserServiceIntf;
import com.examportal.backend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class BackendApplication {



	public static void main(String[] args) {

		SpringApplication.run(BackendApplication.class, args);

	}




}
