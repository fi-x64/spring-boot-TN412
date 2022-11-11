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

import thud.entity.User;
import thud.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	public static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAlluser() {
		List<User> listuser = userRepository.findAll();
		if (listuser.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(listuser, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public User finduser(@PathVariable("id") long id) {
		Optional<User> user = userRepository.findById(id);
		if (user == null) {
			ResponseEntity.notFound().build();
		}
		User userData = user.get();
		return userData;
	}

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public User saveuser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}

	// @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	// public ResponseEntity<User> updateuser(@PathVariable("id") long id,
	// @Valid @RequestBody User userForm) {
	// Optional<User> user = userRepository.findById(id);
	// if(user.isPresent()) {
	// User userData = user.get();

	// userData.setImg(userForm.getName());
	// userData.setName(userForm.getName());
	// userData.setNotes(userForm.getNotes());
	// userData.setRole(userForm.getRole());
	// userData.setTitle(userForm.getTitle());
	// userData.setPrivileges(userForm.getPrivileges());

	// return new ResponseEntity<>(userRepository.save(userData), HttpStatus.OK);
	// }
	// return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	// }

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteuser(@PathVariable(value = "id") Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		User userData = user.get();
		userRepository.delete(userData);
		return ResponseEntity.ok().build();
	}
}
