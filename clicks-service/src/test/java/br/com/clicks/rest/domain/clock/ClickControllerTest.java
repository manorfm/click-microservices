package br.com.clicks.rest.domain.clock;

public class ClickControllerTest {

	/*private MockMvc mockMvc;

    @Mock
    private ClickService clockService;

    @Mock
    private UserController userController;

    @Spy
    private ClickResourcesAssembler clockAssembler;

    @InjectMocks
    private ClickController clockController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(clockController)
                .build();
    }
    
	/*@Test
    public void testClocksOfPeriod() throws Exception {
    	User user = new User();
    	user.setId(12345l);
    	user.setName("Manoel Medeiros");
    	
    	when(userController.getUser(12345l)).thenReturn(user);
    	when(clockService.findByUserAndPeriod(12345l, 4, 2018)).thenReturn(getCenario1Clocks());
    	
    	mockMvc.perform(get("/clockin/user/12345/year/2018/month/4"))
    	.andExpect(status().isOk())
    	.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
    	.andExpect(jsonPath("$.resource", notNullValue()))
    	.andExpect(jsonPath("$.resource.pis", is(12345)))
    	.andExpect(jsonPath("$.resource.name", is("Manoel Medeiros")))
    	.andExpect(jsonPath("$.resource.days[0].day", is(1)))
    	.andExpect(jsonPath("$.resource.days[0].total", is("16:00:00")))
    	.andExpect(jsonPath("$.resource.days[0].hours[0]", is("08:01:00")))
    	.andExpect(jsonPath("$.resource.days[0].hours[1]", is("12:00:00")))
    	.andExpect(jsonPath("$.resource.days[0].hours[2]", is("13:10:00")))
    	.andExpect(jsonPath("$.resource.days[0].hours[3]", is("17:11:00")))
    	
    	.andExpect(jsonPath("$.resource.days[1].day", is(2)))
    	.andExpect(jsonPath("$.resource.days[1].total", is("08:10:00")))
    	.andExpect(jsonPath("$.resource.days[1].warnings[0]", is("The minimum time of interval above 6h of work is 1 hours.")))
    	.andExpect(jsonPath("$.resource.days[1].hours[0]", is("08:05:00")))
    	.andExpect(jsonPath("$.resource.days[1].hours[1]", is("12:05:00")))
    	.andExpect(jsonPath("$.resource.days[1].hours[2]", is("13:00:00")))
    	.andExpect(jsonPath("$.resource.days[1].hours[3]", is("17:10:00")))

    	.andExpect(jsonPath("$.resource.days[2].day", is(3)))
    	.andExpect(jsonPath("$.resource.days[2].total", is("08:10:00")))
    	.andExpect(jsonPath("$.resource.days[2].hours[0]", is("08:00:00")))
    	.andExpect(jsonPath("$.resource.days[2].hours[1]", is("12:10:00")))
    	.andExpect(jsonPath("$.resource.days[2].hours[2]", is("13:10:00")))
    	.andExpect(jsonPath("$.resource.days[2].hours[3]", is("17:10:00")));

    	// .andDo(print());
    	verify(clockService, times(1)).findByUserAndPeriod(12345l, 4, 2018);
    	verifyNoMoreInteractions(clockService);
    	verify(userController, times(1)).getUser(12345l);
    	verifyNoMoreInteractions(userController);
    }
	
	private List<Click> getCenario1Clocks() {
		List<Click> clock = new ArrayList<>();
		
		clock.add(new Click(null, LocalDateTime.of(2018, 4, 1, 8, 1)));
		clock.add(new Click(null, LocalDateTime.of(2018, 4, 1, 12, 0)));
		clock.add(new Click(null, LocalDateTime.of(2018, 4, 1, 13, 10)));
		clock.add(new Click(null, LocalDateTime.of(2018, 4, 1, 17, 11)));
		
		clock.add(new Click(null, LocalDateTime.of(2018, 4, 2, 8, 5)));
		clock.add(new Click(null, LocalDateTime.of(2018, 4, 2, 12, 5)));
		clock.add(new Click(null, LocalDateTime.of(2018, 4, 2, 13, 0)));
		clock.add(new Click(null, LocalDateTime.of(2018, 4, 2, 17, 10)));
		
		clock.add(new Click(null, LocalDateTime.of(2018, 4, 3, 8, 0)));
		clock.add(new Click(null, LocalDateTime.of(2018, 4, 3, 12, 10)));
		clock.add(new Click(null, LocalDateTime.of(2018, 4, 3, 13, 10)));
		clock.add(new Click(null, LocalDateTime.of(2018, 4, 3, 17, 10)));

		return clock;
	}
	
	
	@Test
    public void testJustOneClocks() throws Exception {
    	User user = new User();
    	user.setId(12345l);
    	user.setName("Manoel Medeiros");
    	
    	when(userController.getUser(12345l)).thenReturn(user);
    	when(clockService.findByUserAndPeriod(12345l, 4, 2018)).thenReturn(getCenario2Clocks());
    	
    	mockMvc.perform(get("/clockin/user/12345/year/2018/month/4"))
    	.andExpect(status().isOk())
    	.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
    	.andExpect(jsonPath("$.resource", notNullValue()))
    	.andExpect(jsonPath("$.resource.pis", is(12345)))
    	.andExpect(jsonPath("$.resource.name", is("Manoel Medeiros")))


    	.andExpect(jsonPath("$.resource.days[0].day", is(4)))
    	.andExpect(jsonPath("$.resource.days[0].total", is("00:00:00")))
    	.andExpect(jsonPath("$.resource.days[0].hours[0]", is("08:30:00")));
    	verify(clockService, times(1)).findByUserAndPeriod(12345l, 4, 2018);
    	verifyNoMoreInteractions(clockService);
    	verify(userController, times(1)).getUser(12345l);
    	verifyNoMoreInteractions(userController);
    }

	private List<Click> getCenario2Clocks() {
		return Arrays.asList(new Click(null, LocalDateTime.of(2018, 4, 4, 8, 30)));
	}
	
	@Test
    public void testClocksOfPeriodWithOvertime() throws Exception {
    	User user = new User();
    	user.setId(12345l);
    	user.setName("Manoel Medeiros");
    	
    	when(userController.getUser(12345l)).thenReturn(user);
    	when(clockService.findByUserAndPeriod(12345l, 4, 2018)).thenReturn(getCenario3Clocks());
    	
    	mockMvc.perform(get("/clockin/user/12345/year/2018/month/4"))
    	.andExpect(status().isOk())
    	.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
    	.andExpect(jsonPath("$.resource", notNullValue()))
    	.andExpect(jsonPath("$.resource.pis", is(12345)))
    	.andExpect(jsonPath("$.resource.name", is("Manoel Medeiros")))

    	.andExpect(jsonPath("$.resource.days[0].day", is(4)))
    	.andExpect(jsonPath("$.resource.days[0].total", is("08:18:00")))
    	.andExpect(jsonPath("$.resource.days[0].hours[0]", is("04:30:00")))
    	.andExpect(jsonPath("$.resource.days[0].hours[1]", is("08:30:00")))
    	.andExpect(jsonPath("$.resource.days[0].hours[2]", is("09:40:00")))
    	.andExpect(jsonPath("$.resource.days[0].hours[3]", is("13:40:00")))

    	.andExpect(jsonPath("$.resource.days[1].day", is(5)))
    	.andExpect(jsonPath("$.resource.days[1].total", is("07:51:00")))
    	.andExpect(jsonPath("$.resource.days[1].hours[0]", is("15:30:45")))
    	.andExpect(jsonPath("$.resource.days[1].hours[1]", is("19:40:45")))
    	.andExpect(jsonPath("$.resource.days[1].hours[2]", is("20:25:11")))
    	.andExpect(jsonPath("$.resource.days[1].hours[3]", is("23:45:11")));
    	// .andDo(print());
    	verify(clockService, times(1)).findByUserAndPeriod(12345l, 4, 2018);
    	verifyNoMoreInteractions(clockService);
    	verify(userController, times(1)).getUser(12345l);
    	verifyNoMoreInteractions(userController);
    }

	private List<Click> getCenario3Clocks() {
		List<Click> clock = new ArrayList<>();
		
		clock.add(new Click(null, LocalDateTime.of(2018, 4, 4, 4, 30)));
		clock.add(new Click(null, LocalDateTime.of(2018, 4, 4, 8, 30)));
		clock.add(new Click(null, LocalDateTime.of(2018, 4, 4, 9, 40)));
		clock.add(new Click(null, LocalDateTime.of(2018, 4, 4, 13, 40)));

		clock.add(new Click(null, LocalDateTime.of(2018, 4, 5, 15, 30, 45)));
		clock.add(new Click(null, LocalDateTime.of(2018, 4, 5, 19, 40, 45)));
		clock.add(new Click(null, LocalDateTime.of(2018, 4, 5, 20, 25, 11)));
		clock.add(new Click(null, LocalDateTime.of(2018, 4, 5, 23, 45, 11)));
		
		return clock;
	}*/
}
