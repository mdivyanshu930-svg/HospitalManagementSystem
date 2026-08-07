package com.codingbydivyanshu.yt.HospitalManagement.service;

import com.codingbydivyanshu.yt.HospitalManagement.Dto.Patientdto;
import com.codingbydivyanshu.yt.HospitalManagement.Dto.addPatientdto;
import com.codingbydivyanshu.yt.HospitalManagement.Repository.PatientRepository;
import com.codingbydivyanshu.yt.HospitalManagement.entity.patient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Patientservice {
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;
    public List<Patientdto>getallPatient(){
        List<patient>Patient=patientRepository.findAll();
        List<Patientdto>patientdtoList=Patient.stream()
                .map(patient ->modelMapper.map(patient,Patientdto.class) )
                .toList();
        return patientdtoList;

    }
    public Patientdto getPatientbyId(Long id){
        patient Patient=patientRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Patinet not found"));
        Patientdto patientdto=modelMapper.map(Patient,Patientdto.class);
        return patientdto;
    }
    public Patientdto addpatient(addPatientdto addpatientdto){
       patient p= modelMapper.map(addpatientdto,patient.class);
       patient Patient=patientRepository.save(p);
       return modelMapper.map(Patient,Patientdto.class);

    }
    public void deletepatientbyids(Long id){
        if(!patientRepository.existsById(id)){
            throw new IllegalArgumentException("patient not found");
        }
        patientRepository.deleteById(id);

    }
    @Transactional
    public patient getpatientByid(Long id){
        patient p1=patientRepository.findById(id).orElseThrow();
        patient p2=patientRepository.findById(id).orElseThrow();
        return p1;

    }
    public patient deletepatientbyid(Long id){
        patient Patient=patientRepository.findById(id).orElseThrow();
        patientRepository.deleteById(id);
        return Patient;
    }

}
