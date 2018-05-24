package br.com.users.api.controller;

import java.util.List;

//import org.slf4j.Logger;
import java.util.logging.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.commons.rest.services.AbstractController;
import br.com.users.api.assembler.UserResourcesAssembler;
import br.com.users.api.resources.UserResource;
import br.com.users.domain.User;
import br.com.users.domain.service.UserService;


@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends AbstractController<UserResource> {

	 private static Logger log = Logger.getLogger(UserController.class.getName());
	 
	@Autowired
	private UserService userService;

	@Autowired
	private UserResourcesAssembler userAssembler;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody UserResource resource) {
		log.info(String.format("Saving user name's: %s", resource.getName()));
		User user = userAssembler.convert(resource);
		userService.save(user);

		return responseCreated();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getUser(@PathVariable Long id) {
		log.info("Getting user id: " + id);
		User user = userService.get(id);
		if (user == null) {
			return notFound(String.format("404 - Not found user id: %d", id));
		}
		UserResource resource = userAssembler.convert(user);
		return response(resource, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getUsers() {
		log.info("Getting all users");
		List<User> users = userService.findAll();
		List<UserResource> resources = userAssembler.convert(users);

		return response(resources, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		try {
			log.info(String.format("Getting remove user#%d", id));
			userService.remove(id);
			return responseOk();
		} catch (Exception e) {
			log.severe(String.format("problem removing user#%d", id));
			return notFound("404 - Not found user id: %d");
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestBody UserResource resource) {
		User user = userService.get(resource.getId());
		
		if (user == null) {
			return notFound(String.format("404 - Not found user id: %d", resource.getId()));
		}
		
		user.setName(resource.getName());
		user.setPassword(resource.getPassword());
		userService.update(user);

		UserResource newResource = userAssembler.convert(user);
		return response(newResource, HttpStatus.OK);
	}
	
	private ResponseEntity<?> notFound(String warning) {
		log.warning(warning);
		return responseError(HttpStatus.NOT_FOUND);
	}
}