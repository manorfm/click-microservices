package br.com.clicks.api.user;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.clicks.api.user.model.User;

@FeignClient("user-service")
public interface UserController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
	User getUser(@PathVariable("id") Long id);

}