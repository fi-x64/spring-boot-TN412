package thud.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import thud.entity.Users;
import thud.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	public static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<Users>> listAlluser(){
		List<Users> listuser= userService.findAll();
		if(listuser.isEmpty()) {
			return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Users>>(listuser, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public Users finduser(@PathVariable("id") long id) {
		Optional<Users> user = userService.findById(id);
		if(user == null) {
			ResponseEntity.notFound().build();
		}
		Users userData =user.get();
		return userData;
	}
	
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public Users saveuser(@Valid @RequestBody Users user) {
		return userService.save(user);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Users> updateuser(@PathVariable("id") long id, 
	                                       @Valid @RequestBody Users userForm) {
		Optional<Users> user = userService.findById(id);
	    if(user.isPresent()) {
	    	Users userData = user.get();
	 	   
		    userData.setImg(userForm.getName());
		    userData.setName(userForm.getName());
		    userData.setNotes(userForm.getNotes());
		    userData.setRole(userForm.getRole());
		    userData.setTitle(userForm.getTitle());
		    userData.setPrivileges(userForm.getPrivileges());
		    
		    return new ResponseEntity<>(userService.save(userData), HttpStatus.OK);
	    }
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Users> deleteuser(@PathVariable(value = "id") Long id) {
		Optional<Users> user = userService.findById(id);
	    if(user == null) {
	        return ResponseEntity.notFound().build();
	    }
	    Users userData = user.get();
	    userService.delete(userData);
	    return ResponseEntity.ok().build();
	}
}

