package br.com.clicks.rest.domain.user;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.users.api.assembler.UserResourcesAssembler;
import br.com.users.api.controller.UserController;
import br.com.users.api.resources.UserResource;
import br.com.users.domain.User;
import br.com.users.domain.service.UserService;

public class UserControllerTest {

	private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Spy
    private UserResourcesAssembler userAssembler;

    @InjectMocks
    private UserController userController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
    }
    
    @Test
    public void testSaveUsers() throws Exception {
    	User user = new User();
    	user.setPassword("Teste@1");
    	user.setName("Manoel Medeiros");
    	
    	UserResource resource = new UserResource();
    	resource.setPassword("Teste@1");
    	resource.setName("Manoel Medeiros");
    	
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(resource);
        
		//when(userAssembler.convert(resource)).thenReturn(user);
    	mockMvc.perform(post("/users")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(requestJson))
    	.andExpect(status().isCreated());
    	//verify(userService, times(1)).save(user);
    	//verifyNoMoreInteractions(userService);
    }
    
    @Test
    public void testFindUsers() throws Exception {
    	User user = new User();
    	user.setId(12345l);
    	user.setName("Manoel Medeiros");
    	
        List<User> users = Arrays.asList(user);
        
        when(userService.findAll()).thenReturn(users);
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(12345)))
                .andExpect(jsonPath("$[0].name", is("Manoel Medeiros")))
                .andDo(print());
        verify(userService, times(1)).findAll();
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testFindUsersOne() throws Exception {
    	User user = new User();
    	user.setId(12345l);
    	user.setName("Manoel Medeiros");
    	
    	when(userService.get(12345l)).thenReturn(user);
    	mockMvc.perform(get("/users/12345"))
    	.andExpect(status().isOk())
    	.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
    	.andExpect(jsonPath("$", notNullValue()))
    	.andExpect(jsonPath("$.id", is(12345)))
    	.andExpect(jsonPath("$.name", is("Manoel Medeiros")))
    	.andDo(print());
    	verify(userService, times(1)).get(12345l);
    	verifyNoMoreInteractions(userService);
    }
    
    @Test
    public void testUpdateUsers() throws Exception {
    	User user = new User();
    	user.setId(12345l);
    	user.setName("Manoel Medeiros");
    	
    	UserResource resource = new UserResource();
    	resource.setId(12345l);
    	resource.setName("Manoel Rodrigo Farinha de Medeiros");
    	
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(resource);
        
    	when(userService.get(12345l)).thenReturn(user);
    	mockMvc.perform(put("/users/12345")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(requestJson))
    	.andExpect(status().isOk());
    	verify(userService, times(1)).get(user.getId());
    	verify(userService, times(1)).update(user);
    	verifyNoMoreInteractions(userService);
    }
    
    @Test
    public void testUpdateUsersNotFound() throws Exception {
    	UserResource resource = new UserResource();
    	resource.setId(12346l);
    	resource.setName("Manoel Rodrigo Farinha de Medeiros");
    	
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(resource);
        
    	when(userService.get(12346l)).thenReturn(null);
    	mockMvc.perform(put("/users/12346")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(requestJson))
    	.andExpect(status().is(404));
    	verify(userService, times(1)).get(12346l);
    	verifyNoMoreInteractions(userService);
    }
    
    @Test
    public void testUsersNotFound() throws Exception {
    	when(userService.get(11111l)).thenReturn(null);
    	mockMvc.perform(get("/users/11111"))
    	.andExpect(status().is(404))
    	.andDo(print());
    	verify(userService, times(1)).get(11111l);
    	verifyNoMoreInteractions(userService);
    }
}
