package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }
	
	    @Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }
	@Test
	    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"));
    }

    @Test
    public void getDefaultWithInvalidParameter() throws Exception { //test1
        // Simulate a GET request to the root URL with an invalid parameter
        this.mvc.perform(get("/").param("operand1", "invalidValue")) // Provide an invalid value
                .andExpect(status().isOk()) // Expect a 200 OK status if you handle errors gracefully
                .andExpect(view().name("calculator")) // This should still return to the calculator view
                .andExpect(model().attribute("operand1", "invalidValue")) // Check if the model returns the invalid input
                .andExpect(model().attribute("operand1Focused", true)); // Expect focus to be true to indicate some error message
    }

    @Test
    public void getParameter2() throws Exception { //changed the value to make sure it works with 0 (test2)
        this.mvc.perform(get("/").param("operand1","0"))
                .andExpect(status().isOk())
                .andExpect(view().name("calculator"))
                .andExpect(model().attribute("operand1", "0"))
                .andExpect(model().attribute("operand1Focused", true));
    }
    @Test
    public void postParameter2() throws Exception { //changed the values to make sure it works for different cases (test3)
        this.mvc.perform(post("/").param("operand1","001").param("operator","+").param("operand2","110"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "111"))
                .andExpect(model().attribute("operand1", "001"));
    }

    @Test //multiply 1
    public void postParameterMulti1() throws Exception { //changed the values to make sure it works for different cases
        this.mvc.perform(post("/").param("operand1","001").param("operator","*").param("operand2","110"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "110"))
                .andExpect(model().attribute("operand1", "001"));
    }
    @Test //multiply 2
    public void postParameterMulti2() throws Exception { //changed the values to make sure it works for different cases
        this.mvc.perform(post("/").param("operand1","00").param("operator","*").param("operand2","0"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "0"))
                .andExpect(model().attribute("operand1", "00"));
    }
    @Test //multiply 3
    public void postParameterMulti3  () throws Exception { //changed the values to make sure it works for different cases
        this.mvc.perform(post("/").param("operand1","11011").param("operator","*").param("operand2","110"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "10100010"))
                .andExpect(model().attribute("operand1", "11011"));
    }

    @Test //and1
    public void postParameterand1() throws Exception { //changed the values to make sure it works for different cases
        this.mvc.perform(post("/").param("operand1","1010").param("operator","&").param("operand2","1100"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1000"))
                .andExpect(model().attribute("operand1", "1010"));
    }
    @Test //and2
    public void postParameterand2() throws Exception { //changed the values to make sure it works for different cases
        this.mvc.perform(post("/").param("operand1","00").param("operator","&").param("operand2","0"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "0"))
                .andExpect(model().attribute("operand1", "00"));
    }
    @Test //and3
    public void postParameterand3() throws Exception { //changed the values to make sure it works for different cases
        this.mvc.perform(post("/").param("operand1","00100").param("operator","&").param("operand2","0000000000100"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "100"))
                .andExpect(model().attribute("operand1", "00100"));
    }

    @Test //or1
    public void postParameteror1() throws Exception { //changed the values to make sure it works for different cases
        this.mvc.perform(post("/").param("operand1","001").param("operator","|").param("operand2","110"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "111"))
                .andExpect(model().attribute("operand1", "001"));
    }
    @Test //or2
    public void postParameteror2() throws Exception { //changed the values to make sure it works for different cases
        this.mvc.perform(post("/").param("operand1","00").param("operator","|").param("operand2","0"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "0"))
                .andExpect(model().attribute("operand1", "00"));
    }
    @Test //or3
    public void postParameteror3() throws Exception { //changed the values to make sure it works for different cases
        this.mvc.perform(post("/").param("operand1","11011").param("operator","|").param("operand2","100"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "11111"))
                .andExpect(model().attribute("operand1", "11011"));
    }





}