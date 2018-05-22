package br.com.clicks.api.controller;

import java.util.List;

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

	@Autowired
	private UserController userController;
	
	@Autowired
	private ClickService clickService;

	@Autowired
	private ClickResourcesAssembler clickResourcesAssembler;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody ClickResource resource) {
		try {
			Click click = clickResourcesAssembler.convert(resource);
			clickService.save(click);
			return responseOk();
		} catch (Exception e) {
			e.printStackTrace();
			return responseError(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		try {
			List<Click> clicks = clickService.findAll();
			List<ClickResource> resources = clickResourcesAssembler.convert(clicks);
			
			return responseOk(resources);
		} catch (Exception e) {
			e.printStackTrace();
			return responseError(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable Long id) {
		try {
			Click click = clickService.get(id);
			User user = userController.getUser(click.getUserId());
			ClickResource resource = clickResourcesAssembler.convert(click, user);
			return responseOk(resource);
		} catch (Exception e) {
			e.printStackTrace();
			return responseError(HttpStatus.CONFLICT);
		}
	}
}