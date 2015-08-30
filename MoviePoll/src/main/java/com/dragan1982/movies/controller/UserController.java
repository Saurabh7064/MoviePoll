package com.dragan1982.movies.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;

import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dragan1982.movies.model.Role;
import com.dragan1982.movies.model.User;
import com.dragan1982.movies.service.RoleService;
import com.dragan1982.movies.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	@Qualifier("sessionRegistry")
	private SessionRegistry sessionRegistry;
	
	@InitBinder
    protected void initBinder(ServletRequestDataBinder binder) throws Exception {

        binder.registerCustomEditor(List.class, "roles",new CustomCollectionEditor(List.class){

            @Override
            protected Object convertElement(Object element) {
                
                if (element != null) {
                    Long id = Long.valueOf(element.toString());
                    Role role = roleService.getRole(id);
                    return role;
                }
                return null;
            } 
        });
    }
	
	@RequestMapping("/editUsers")
	public String userList(Map<String, Object> map, Principal principal, User user) {
		
		map.put("user", new User());
		map.put("userList", userService.listUsers());
		map.put("hasVoted", user.isHasVoted());
		map.put("roleList", roleService.listRoles());
		
		if(principal != null){
			String name = principal.getName();
			map.put("username", name);
			}
	    
		return "/user/editUsers";
	}

	@RequestMapping("/addUser")
	public String addUser(Map<String, Object> map) {

		map.put("user", new User());
		map.put("roleList", roleService.listRoles());
		
		return "/user/userForm";
	}
	
	@RequestMapping("/getUser/{userId}")
	public String getUser(@PathVariable int userId, Map<String, Object> map) {

		User user = userService.getUser(userId);

		map.put("user", user);
		map.put("roleList", roleService.listRoles());
		map.put("edit", "editUser");

		return "/user/userForm";
	}	

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult result, Map<String, Object> map) {
		if (result.hasErrors()) {
			
			map.put("user", user);
			map.put("roleList", roleService.listRoles());
			
			return "/user/userForm";
		 
		} else {
		 
		    // form input is ok
			
		userService.saveUser(user);
		
		return "redirect:/editUsers";
		}
	}
	
	@RequestMapping(value = "/resetVote/{userId}")
	public String resetVote(@PathVariable int userId) {
		
		userService.userVoted(userId, false);
		
		return "redirect:/editUsers";
	}	
	
	@RequestMapping("/deleteUser/{userId}")
	public String deleteUser(@PathVariable("userId") int id) {

		userService.deleteUser(id);

		return "redirect:/editUsers";
	}
	
	// Get logged in users in json format for display using angularjs
	@RequestMapping(value="/activeUsers", method=RequestMethod.GET,produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Object> getPrincipals() {		
		
		List<Object> principals = sessionRegistry.getAllPrincipals();		
		
		return principals;		
	}	
}