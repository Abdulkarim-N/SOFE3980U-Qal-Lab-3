package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }
	@Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    @Test
    public void add3() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "000").param("operand2", "1010"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("1010"));
    }

    @Test
    public void add4() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","1").param("operand2","1010"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1011))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }
    @Test
    public void add5() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","1010").param("operand2","1010"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10100))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }
//tests for or
    @Test
    public void or1() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1","1").param("operand2","1010"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1011))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("or"));
    }
    @Test
    public void or2() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "000").param("operand2", "1010"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("1010"));
    }
    @Test
    public void or3() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1","1111").param("operand2","1010"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("or"));
    }

    //tests for and

    @Test
    public void and1() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1","1").param("operand2","1011"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1011))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("and"));
    }
    @Test
    public void and2() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "1010").param("operand2", "1010"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("1010"));
    }
    @Test
    public void and3() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1","1111").param("operand2","1010"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("and"));
    }
//tests for multi
    @Test
    public void multi1() throws Exception {
        this.mvc.perform(get("/multi_json").param("operand1","1").param("operand2","1011"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1011))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1011))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multi"));
    }
    @Test
    public void multi2() throws Exception {
        this.mvc.perform(get("/multi").param("operand1", "1010").param("operand2", "1010"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("1100100"));
    }
    @Test
    public void multi3() throws Exception {
        this.mvc.perform(get("/multi_json").param("operand1","0").param("operand2","1010"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multi"));
    }
}