package com.baton.h2_test;


import com.baton.h2_test.model.FinancialDetails;
import com.baton.h2_test.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.baton.h2_test.model.AcademicDetails;

@SpringBootTest
@WebAppConfiguration
public class H2TestApplicationTests{

    @Autowired
    public WebApplicationContext webApplicationContext;

    public String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }//Method to convert string to Json.

    public <T> T mapFromJson(String json, Class<T> clazz) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }//Method to convert Json to String.

    @Test
    public void saveStudentDetails() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "/save/student_details/";
        Student student = new Student();
        student.setId(1);
        student.setName("Praveen Kumar");
        student.setAge(22);
        student.setEmail("Praveen@gmail.com");


        String inputJson = "["+mapToJson(student)+"]"; //Listing elements in Json to suit input.
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Student Details Saved..!");
    }
    @Test
    public void saveAcademicDetails() throws Exception{
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "/save/academic_details/";
        AcademicDetails academicDetails = new AcademicDetails();
        academicDetails.setId(1);
        academicDetails.setGpa((float) 9.44);

        String inputJson = "["+mapToJson(academicDetails)+"]"; //Listing elements in Json to suit input.
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void saveFinancialDetails() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "/save/financial_details/";
        FinancialDetails fin_details  = new FinancialDetails();
        fin_details.setId(1);
        fin_details.setName("Praveen Kumar");
        fin_details.setFinancial_aid_status(0);

        String inputJson = "["+mapToJson(fin_details)+"]";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Student Financial Details Saved..!");
    }

    @Test
    public void getAllStudents() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "/get/students_details";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        Student[] student_details = mapFromJson(mvcResult.getResponse().getContentAsString(), Student[].class);
        assertTrue(student_details.length > 0);
    }

    @Test
    public void VotingEligibilityFiltering() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "/get/eligible_students/voting";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        Student[] student_details = mapFromJson(mvcResult.getResponse().getContentAsString(), Student[].class);
        assertTrue(student_details.length > 0);

    }
    @Test
    public void FinancialAidEligibilityFiltering() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "/get/eligible_students/financial_aid";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        Student[] student_details = mapFromJson( mvcResult.getResponse().getContentAsString(), Student[].class);
        assertTrue(student_details.length > 0);
    }



}
