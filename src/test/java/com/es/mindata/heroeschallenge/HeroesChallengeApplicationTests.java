package com.es.mindata.heroeschallenge;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.WebApplicationContext;

import com.es.mindata.heroeschallenge.entity.Hero;
import com.es.mindata.heroeschallenge.repository.HeroRepository;

import lombok.SneakyThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class HeroesChallengeApplicationTests {
	
	private static final Long ID = 1L;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before()
	public void setup(){
	    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
	@Test
	@SneakyThrows
	@WithMockUser(username="admin", password="admin")
	public void testFindAllHeroes() {
		mockMvc.perform(MockMvcRequestBuilders.get("/heroes/find-all-heroes/")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(
						MockMvcResultMatchers.content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

    @Test
	@SneakyThrows
	@WithMockUser(username="admin", password="admin")
    public void findHeroByIdNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/find-hero-by-id/233223"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    
}
