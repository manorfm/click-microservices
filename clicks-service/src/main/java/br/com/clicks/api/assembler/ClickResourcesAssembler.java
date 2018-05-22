package br.com.clicks.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.clicks.api.resources.ClickResource;
import br.com.clicks.api.resources.UserResource;
import br.com.clicks.api.user.model.User;
import br.com.clicks.domain.Click;
import br.com.commons.rest.services.AbstractAssembler;

@Component
public class ClickResourcesAssembler extends AbstractAssembler<Click, ClickResource> {
   
	public List<ClickResource> convert(List<Click> clicks) {
    	return clicks.stream()
			.map(click -> mapper.map(click, ClickResource.class))
			.collect(Collectors.toList());
    }
    
    public ClickResource convert(Click click) {
        return mapper.map(click, ClickResource.class);
    }

    public Click convert(ClickResource click) {
    	return mapper.map(click, Click.class);
    }
    
    public ClickResource convert(Click click, User user) {
    	UserResource userResource = mapper.map(user, UserResource.class);
    	ClickResource clickResource = mapper.map(click, ClickResource.class);
    	clickResource.setUserId(null);
    	clickResource.setUser(userResource);
    	
		return clickResource;
    }

}
