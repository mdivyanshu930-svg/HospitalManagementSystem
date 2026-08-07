package com.codingbydivyanshu.yt.HospitalManagement;

import com.codingbydivyanshu.yt.HospitalManagement.entity.Insurance;
import com.codingbydivyanshu.yt.HospitalManagement.entity.patient;
import com.codingbydivyanshu.yt.HospitalManagement.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTest {
    @Autowired
    private InsuranceService insuranceService;
    @Test
    public void testInsurance(){
        Insurance insurance=Insurance.builder().policyNumber("HDFC_122").provider("HDFC")
                .validUntil(LocalDate.of(2027,7,25)).build();
        patient Patient=insuranceService.assingnInsurancetoPatient(insurance,1L);
        System.out.println();



    }
    @Test
    public void removeappointment(){
        insuranceService.dissociateinsurancefrompatient(1L);

    }
}
