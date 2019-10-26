package com.packt.cardatabase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void ログインに成功すると200を返す() throws Exception {
        // Testing authentication with correct credentials
        this.mockMvc.perform(post("/login")
                .content("{\"username\":\"user\", \"password\":\"password\"}"))
                .andDo(print()).andExpect(status().isOk()
        );
    }

    @Test
    public void ログインに失敗すると403を返す() throws Exception {
        // Testing authentication with wrong credentials
        this.mockMvc.perform(post("/login")
                .content("{\"username\":\"user\",\"password\":\"wrongpwd\"}"))
                .andDo(print())
                .andExpect(status().is4xxClientError()
                );
    }
}