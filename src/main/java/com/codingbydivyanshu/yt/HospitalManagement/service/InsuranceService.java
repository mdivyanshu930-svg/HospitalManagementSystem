package com.codingbydivyanshu.yt.HospitalManagement.service;

import com.codingbydivyanshu.yt.HospitalManagement.Repository.InsuranceRepository;
import com.codingbydivyanshu.yt.HospitalManagement.Repository.PatientRepository;
import com.codingbydivyanshu.yt.HospitalManagement.entity.Insurance;
import com.codingbydivyanshu.yt.HospitalManagement.entity.patient;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final PatientRepository patientRepository;
    private final InsuranceRepository insuranceRepository;

    @Transactional
    public patient assingnInsurancetoPatient(Insurance insurance, Long patientId) {
        patient Patient = patientRepository.findById(patientId).
                orElseThrow(() -> new EntityNotFoundException("Patient not found by id"));
        Patient.setInsurance(insurance);
        insurance.setPatient(Patient);
        return Patient;


    }
@Transactional
    public patient dissociateinsurancefrompatient(Long patientId) {
        patient Patient = patientRepository.findById(patientId).orElseThrow();
        Patient.setInsurance(null);
        return Patient;
    }
}
