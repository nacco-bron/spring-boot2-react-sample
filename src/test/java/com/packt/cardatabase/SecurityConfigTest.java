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
        String uri = "/login";
        String body = "{\"username\":\"user\", \"password\":\"password\"}";
        mockMvc.perform(post(uri).content(body))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void ログインに失敗すると403を返す() throws Exception {
         String uri = "/login";
         String body = "{\"username\":\"user\", \"password\":\"wrongpwd\"}";
         mockMvc.perform(post(uri).content(body))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}