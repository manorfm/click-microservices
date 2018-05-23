package br.com.clicks.api.click;


import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

import br.com.clicks.api.assembler.ClickResourcesAssembler;
import br.com.clicks.api.controller.ClickController;
import br.com.clicks.api.user.UserController;
import br.com.clicks.api.user.model.User;
import br.com.clicks.domain.Click;
import br.com.clicks.domain.service.ClickService;

public class ClickControllerTest {

	private MockMvc mockMvc;

    @Mock
    private ClickService clickService;

    @Mock
    private UserController userController;

    @Spy
    private ClickResourcesAssembler clockAssembler;

    @InjectMocks
    private ClickController clickController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(clickController)
                .build();
    }
    
	@Test
    public void testFindClicks() throws Exception {
    	when(clickService.findAll()).thenReturn(getCenarioClicks());
    	
    	mockMvc.perform(get("/clicks"))
    	.andExpect(status().isOk())
    	.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
    	.andExpect(jsonPath("$", notNullValue()))
    	.andExpect(jsonPath("$[0].id", is(1)))
    	.andExpect(jsonPath("$[0].userId", is(12345)))
    	.andExpect(jsonPath("$[0].timer", is("23/05/2018 11:30:12")))
    	
    	.andExpect(jsonPath("$[1].id", is(2)))
    	.andExpect(jsonPath("$[1].userId", is(12345)))
    	.andExpect(jsonPath("$[1].timer", is("23/05/2018 11:30:24")))
    	
    	.andExpect(jsonPath("$[2].id", is(3)))
    	.andExpect(jsonPath("$[2].userId", is(12345)))
    	.andExpect(jsonPath("$[2].timer", is("23/05/2018 11:30:33")))
    	
    	.andExpect(jsonPath("$[3].id", is(4)))
    	.andExpect(jsonPath("$[3].userId", is(12345)))
    	.andExpect(jsonPath("$[3].timer", is("23/05/2018 11:30:35")))
    	
    	.andExpect(jsonPath("$[4].id", is(5)))
    	.andExpect(jsonPath("$[4].userId", is(12345)))
    	.andExpect(jsonPath("$[4].timer", is("23/05/2018 11:30:40")));
    	
    	//.andDo(print());
    	verify(clickService, times(1)).findAll();
    	verifyNoMoreInteractions(clickService);
    }
	
	@Test
    public void testFindClicks1() throws Exception {
    	User user = new User();
    	user.setId(12345l);
    	user.setName("Manoel Medeiros");
    	
    	Click click = new Click(1l, user.getId(), LocalDateTime.of(2018, 5, 23, 11, 30, 12));
    	
    	when(userController.getUser(12345l)).thenReturn(user);
    	when(clickService.get(1l)).thenReturn(click);
    	
    	mockMvc.perform(get("/clicks/1"))
    	.andExpect(status().isOk())
    	.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
    	.andExpect(jsonPath("$", notNullValue()))
    	.andExpect(jsonPath("$.id", is(1)))
    	.andExpect(jsonPath("$.user.id", is(12345)))
    	.andExpect(jsonPath("$.user.name", is("Manoel Medeiros")))
    	.andExpect(jsonPath("$.timer", is("23/05/2018 11:30:12")));
    	
    	//.andDo(print());
    	verify(clickService, times(1)).get(1l);
    	verifyNoMoreInteractions(clickService);
    	verify(userController, times(1)).getUser(12345l);
    	verifyNoMoreInteractions(userController);
    }
	
	@Test
    public void testFindClicksNotFound() throws Exception {
    	when(clickService.get(1l)).thenReturn(null);
    	
    	mockMvc.perform(get("/clicks/1"))
    	.andExpect(status().is(404));
    	
    	verify(clickService, times(1)).get(1l);
    	verifyNoMoreInteractions(clickService);
    	verify(userController, times(0)).getUser(12345l);
    	verifyNoMoreInteractions(userController);
    }
	
	private List<Click> getCenarioClicks() {
		List<Click> clicks = new ArrayList<>();
		
		clicks.add(new Click(1l, 12345l, LocalDateTime.of(2018, 5, 23, 11, 30, 12)));
		clicks.add(new Click(2l, 12345l, LocalDateTime.of(2018, 5, 23, 11, 30, 24)));
		clicks.add(new Click(3l, 12345l, LocalDateTime.of(2018, 5, 23, 11, 30, 33)));
		clicks.add(new Click(4l, 12345l, LocalDateTime.of(2018, 5, 23, 11, 30, 35)));
		clicks.add(new Click(5l, 12345l, LocalDateTime.of(2018, 5, 23, 11, 30, 40)));
		
		return clicks;
	}
	
	/*private List<Click> getCenarioClicks2() {
		List<Click> clicks = new ArrayList<>();
		
		clicks.add(new Click(6l, LocalDateTime.of(2018, 5, 23, 11, 30, 12)));
		clicks.add(new Click(7l, LocalDateTime.of(2018, 5, 23, 11, 30, 24)));
		clicks.add(new Click(12346l, LocalDateTime.of(2018, 5, 23, 11, 30, 33)));
		
		clicks.add(new Click(8l, LocalDateTime.of(2018, 5, 23, 11, 31, 25)));
		clicks.add(new Click(9l, LocalDateTime.of(2018, 5, 23, 11, 31, 28)));
		clicks.add(new Click(10l, LocalDateTime.of(2018, 5, 23, 11, 31, 39)));
		
		clicks.add(new Click(11l, LocalDateTime.of(2018, 5, 23, 11, 32, 9)));
		clicks.add(new Click(12l, LocalDateTime.of(2018, 5, 23, 11, 32, 11)));
		clicks.add(new Click(13l, LocalDateTime.of(2018, 5, 23, 11, 32, 19)));
		
		return clicks;
	}*/
}
