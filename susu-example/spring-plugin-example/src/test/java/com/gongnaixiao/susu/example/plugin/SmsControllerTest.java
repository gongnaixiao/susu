package com.gongnaixiao.susu.example.plugin;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class SmsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void AliSendMsgTest() throws Exception {
		Req req = new Req();
		req.setSmsType(SmsType.ALI);
		req.setMobilePhone("18660836451");
		req.setMsgContent("hello, world");

		mockMvc.perform(MockMvcRequestBuilders.post("/sendMsg").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(req))).andExpect(MockMvcResultMatchers.status().isOk());
	}

}
