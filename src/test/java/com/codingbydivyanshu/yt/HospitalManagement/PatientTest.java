package com.codingbydivyanshu.yt.HospitalManagement;

import com.codingbydivyanshu.yt.HospitalManagement.Dto.BloodGroupCountResponseEntity;
import com.codingbydivyanshu.yt.HospitalManagement.Repository.PatientRepository;
import com.codingbydivyanshu.yt.HospitalManagement.entity.patient;
import com.codingbydivyanshu.yt.HospitalManagement.service.Patientservice;
import com.codingbydivyanshu.yt.HospitalManagement.type.Bloodgrouptype;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@SpringBootTest

public class PatientTest {
    @Autowired
    private Patientservice patientservice;
    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testPatientRepository() {
        List<patient> patientlist = patientRepository.findAllWithAppointment();
        System.out.println(patientlist);
    }

    @Test
    public void testTransactionMethods() {
        //         List<patient> patientList= patientRepository.findallpatient();
        //         for(patient p:patientList)
        Page<patient> patientPage = patientRepository.findallpatient(PageRequest.of(0, 2, Sort.by("name")));
        for (patient p : patientPage) {
            System.out.println(p);
        }
    }






        //      System.out.println(p);
        //
        //List<patient> Patient = patientRepository.findByNameOrEmail("mishra jdbd", "mndhs@gmail.com");
        // List<patient> Patient=patientRepository.findByNameContaining("ndh");

        //       List<patient> Patient=patientRepository. findByBloodGroup(Bloodgrouptype.A_POSITIVE);
        //       List<patient> patientdob=patientRepository.findByDOB(LocalDate.of(1988,5,23));
        //     for (patient pat : patientdob) {
        //         System.out.println(pat);


           //   List<BloodGroupCountResponseEntity> bloodGroupList=patientRepository.countEachBloodGroupType();
             //for(BloodGroupCountResponseEntity bloodGroupCountResponse:bloodGroupList){
               //   System.out.println(bloodGroupCountResponse);
           //}
      //  int rowUpdated = patientRepository.Updatenamewithid("divyanshu", 1L);
       // System.out.println(rowUpdated);
    }



