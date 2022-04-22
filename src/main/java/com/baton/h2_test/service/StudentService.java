package com.baton.h2_test.service;

import com.baton.h2_test.model.AcademicDetails;
import com.baton.h2_test.model.FinancialDetails;
import com.baton.h2_test.model.Student;
import com.baton.h2_test.repository.AcademicServicesRepository;
import com.baton.h2_test.repository.FinancialServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baton.h2_test.repository.StudentRepository;
import com.baton.h2_test.repository.FinancialServicesRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService
{
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    FinancialServicesRepository financialServicesRepository;
    @Autowired
    AcademicServicesRepository academicServicesRepository;
    public List<Student> getAllStudent()
    {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        return students;
    }

    public Student getStudentById(int id)
    {
        return studentRepository.findById(id).get();
    }

    public void student_SaveOrUpdate(Student student)
    {
        studentRepository.save(student);
    }

    public void financial_SaveOrUpdate(FinancialDetails fin_details)
{
    financialServicesRepository.save(fin_details);
}

    public void delete(int id)
    {
        studentRepository.deleteById(id);
    }

    public void saveAcademicDetails(AcademicDetails academicDetails){
        academicServicesRepository.save(academicDetails);
    }
    public List<Student> FinancialAidEligibilityFilter(){

        List<AcademicDetails> gpa_filtered_students =academicServicesRepository.FilterByGpa(9.00F);
        List<Student> fin_aid_filtered_students = new ArrayList<>();//initializing to empty Array list is to add elements to the List.
        gpa_filtered_students.forEach((individual_student) -> {//iterating against each student object from Gpa_filtered_students.
            if(financialServicesRepository.CheckFinAidStatus(individual_student.getId())){//checking the Student's FinancialAidStatus and if YES, adding to final results List(fin_aid_filered_students).
                fin_aid_filtered_students.add(getStudentById(individual_student.getId()));
            }});
        return fin_aid_filtered_students;
    }

    public List<Student> VotingEligibilityFilter() {
        List<Student> students;
        students = studentRepository.FilterByAge(18);//hard-coded the age filter parameter.
        return students;
    }
}
