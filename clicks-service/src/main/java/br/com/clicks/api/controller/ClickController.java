package br.com.clicks.api.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.clicks.api.assembler.ClickResourcesAssembler;
import br.com.clicks.api.resources.ClickResource;
import br.com.clicks.api.user.UserController;
import br.com.clicks.api.user.model.User;
import br.com.clicks.domain.Click;
import br.com.clicks.domain.service.ClickService;
import br.com.commons.rest.services.AbstractController;

@RestController
@RequestMapping(value = "/clicks", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClickController extends AbstractController<ClickResource> {

	private static Logger log = Logger.getLogger(ClickController.class.getName());
	
	@Autowired
	private UserController userController;
	
	@Autowired
	private ClickService clickService;

	@Autowired
	private ClickResourcesAssembler clickResourcesAssembler;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody ClickResource resource) {
		try {
			log.info(String.format("Saving click of user: %s", resource.getUserId()));
			Click click = clickResourcesAssembler.convert(resource);
			clickService.save(click);
			return responseOk();
		} catch (Exception e) {
			log.severe(String.format("Error trying to saving click of user: %s", resource.getUserId()));
			e.printStackTrace();
			return responseError(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		try {
			log.info("get all clicks");
			List<Click> clicks = clickService.findAll();
			List<ClickResource> resources = clickResourcesAssembler.convert(clicks);
			
			return responseOk(resources);
		} catch (Exception e) {
			log.severe("get all clicks");
			e.printStackTrace();
			return responseError(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable Long id) {
		try {
			log.info(String.format("get click id: %d", id));
			Click click = clickService.get(id);
			if (click == null) {
				log.warning(String.format("404 - Not found user id: %d", id));
				return responseError(HttpStatus.NOT_FOUND);
			}
			User user = userController.getUser(click.getUserId());
			ClickResource resource = clickResourcesAssembler.convert(click, user);
			return responseOk(resource);
		} catch (Exception e) {
			log.severe(String.format("erro trying to get click with id: %d", id));
			e.printStackTrace();
			return responseError(HttpStatus.CONFLICT);
		}
	}
}