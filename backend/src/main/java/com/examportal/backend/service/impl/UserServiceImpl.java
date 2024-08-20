package com.examportal.backend.service.impl;

import com.examportal.backend.RoleName;
import com.examportal.backend.dto.UserDto;
import com.examportal.backend.model.Roles;
import com.examportal.backend.model.UserRoles;
import com.examportal.backend.model.Users;
import com.examportal.backend.repo.RoleRepo;
import com.examportal.backend.repo.UserRepo;
import com.examportal.backend.service.UserServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserServiceIntf {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    //creaitng user
    @Override
    public Users createUser(UserDto userDto) throws Exception{

        System.out.println(userDto.toString());
        //check if the user is already present
        Users users = userRepo.findByUsername(userDto.getUsername());

        if(users!=null){
            System.out.println("User is already present");
            throw  new Exception("User is already present!!");
        }
        else{

            //create a new user
            Users user = new Users();
            user.setUsername(userDto.getUsername());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPassword(userDto.getPassword());

            //create a new UserRoles set
            Set<UserRoles> userRolesSet = new HashSet<>();


            if(userDto.getRole() == RoleName.ADMIN){
                Roles adminRole = roleRepo.findByRolename(RoleName.ADMIN);
                Roles userRole = roleRepo.findByRolename(RoleName.USER);

                UserRoles adminUserRoles = new UserRoles();
                adminUserRoles.setRoles(adminRole);
                adminUserRoles.setUsers(user);

                UserRoles userUserRoles = new UserRoles();
                userUserRoles.setUsers(user);
                userUserRoles.setRoles(userRole);

                userRolesSet.add(adminUserRoles);
                userRolesSet.add(userUserRoles);

            }
            else{
                Roles userRole = roleRepo.findByRolename(RoleName.USER);

                UserRoles userRoles  = new UserRoles();
                userRoles.setRoles(userRole);
                userRoles.setUsers(user);

                userRolesSet.add(userRoles);

            }
            user.setUserRoles(userRolesSet);

//            Roles role = roleRepo.findByRolename(userDto.getRole()!=null ? userDto.getRole() : RoleName.USER);
//
//            UserRoles userRoles = new UserRoles();
//            userRoles.setRoles(role);
//            userRoles.setUsers(user);
//
//            //adding the userRoles variable to set
//            userRolesSet.add(userRoles);
//
//            user.setUserRoles(userRolesSet);
//
////            //saving the role in db
////            roleRepo.save(role);
//
            //saving the user in db
            users = userRepo.save(user);


        }

        return users;


    }

    @Override
    public Users addRole(String username, RoleName roleName) throws Exception {

        //find the user with the given username

        Users user = userRepo.findByUsername(username);

        if(user == null){
            throw new Exception("User not found");
        }

        Roles role   = roleRepo.findByRolename(roleName);
        if(role == null){
            throw  new Exception("Role is null");
        }

        UserRoles userRoles = new UserRoles();

        userRoles.setUsers(user);
        userRoles.setRoles(role);

        //add the new role to the user's set of roles
        user.getUserRoles().add(userRoles);



        return userRepo.save(user);


    }

    //fetching the user given username
    @Override
    public Users getUser(String username) {

        return userRepo.findByUsername(username);
    }

    //deleting the user given a username

    public String deleteUsers(String username) throws Exception{

        Users delUser = userRepo.findByUsername(username);
        if(delUser == null){
            throw new Exception("User with given username not present");
        }
        try{
            userRepo.deleteById(delUser.getUser_id());
            return "User deleted successfully";
        }
        catch (Exception e){
            return ("Error occured while deleting the user" + e);

        }


    }

    @Override
    public Users updateUser(String username, UserDto userDto) throws Exception {
        Users updateUser = userRepo.findByUsername(username);

        if (updateUser == null) {
            throw new Exception("User not found");
        }

        if (userDto.getUsername() != null) {
            updateUser.setUsername(userDto.getUsername());
        }

        if (userDto.getFirstName() != null) {
            updateUser.setFirstName(userDto.getFirstName());
        }

        if (userDto.getLastName() != null) {
            updateUser.setLastName(userDto.getLastName());
        }
        if (userDto.getPassword() != null) {
            updateUser.setPassword(userDto.getPassword());
        }

        Set<UserRoles> updateUserRolesSet = updateUser.getUserRoles();
        if (userDto.getRole() == RoleName.ADMIN) {
            Roles adminRole = roleRepo.findByRolename(RoleName.ADMIN);

            UserRoles adminUserRoles = new UserRoles();

            adminUserRoles.setUsers(updateUser);
            adminUserRoles.setRoles(adminRole);

            updateUserRolesSet = updateUser.getUserRoles();

            updateUserRolesSet.add(adminUserRoles);

        }
        updateUser.setUserRoles(updateUserRolesSet);
        System.out.println(updateUser.toString());
        return userRepo.save(updateUser);


    }


}
