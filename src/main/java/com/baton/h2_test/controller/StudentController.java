package com.baton.h2_test.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baton.h2_test.model.Student;
import com.baton.h2_test.service.StudentService;
import com.baton.h2_test.model.FinancialDetails;
import com.baton.h2_test.model.AcademicDetails;

@RestController
public class StudentController
{

    @Autowired
    StudentService studentService;

    @RequestMapping(method = RequestMethod.POST,value = "/save/student_details")
    private String saveStudentDetails(@RequestBody List<Student> student)
    {   student.forEach(individual_student -> studentService.student_SaveOrUpdate(individual_student));
        return "Student Details Saved..!";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/save/academic_details")
    private String saveAcademicDetails(@RequestBody List<AcademicDetails> student_academic_details)
    {   student_academic_details.forEach(individual_student -> studentService.saveAcademicDetails(individual_student));
        return "Student Details Saved..!";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/save/financial_details")
    private String saveFinancialDetails(@RequestBody List<FinancialDetails> fin_details)
    {   fin_details.forEach(individual_details -> studentService.financial_SaveOrUpdate(individual_details));
        return "Student Financial Details Saved..!";
    }

        @RequestMapping(method = RequestMethod.GET,value="/get/students_details")//retrieve all objects from the Database
        private List<Student> getAllStudents()
        {
            return studentService.getAllStudent();
        }

        @RequestMapping(method = RequestMethod.GET,value="/get/student_details/")//retrieve required objects from the database
        private Student getStudent(@RequestParam("id") int id)
    {
        return studentService.getStudentById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/delete/student_details/")
    private String deleteStudent(@RequestParam("id") int id)
    {
        studentService.delete(id);
        return "Deleted..!";
    }

    @RequestMapping(method = RequestMethod.GET,value = "/get/eligible_students/financial_aid")
    private List<Student> FinancialAidEligibilityFiltering(){
        return studentService.FinancialAidEligibilityFilter();
    }

    @RequestMapping(method = RequestMethod.GET,value = "/get/eligible_students/voting")
    private List<Student> VotingEligibilityFiltering(){
        return studentService.VotingEligibilityFilter();
    }


}