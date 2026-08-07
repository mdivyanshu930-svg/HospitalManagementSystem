package com.codingbydivyanshu.yt.HospitalManagement.service;

import com.codingbydivyanshu.yt.HospitalManagement.Dto.Doctordto;
import com.codingbydivyanshu.yt.HospitalManagement.Dto.addDoctordto;
import com.codingbydivyanshu.yt.HospitalManagement.Repository.DoctorRepository;
import com.codingbydivyanshu.yt.HospitalManagement.config.Mapperconfigu;
import com.codingbydivyanshu.yt.HospitalManagement.entity.Doctor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    public List<Doctordto>  getalldoctor(){
        List<Doctor> doctor=doctorRepository.findAll();
        List<Doctordto> doctordtos=doctor.stream().map(doctor1 -> modelMapper.map(doctor1,Doctordto.class)).toList();
        return doctordtos;
    }
    public Doctordto adddoctor(addDoctordto adddoctordto){
        Doctor doctor=modelMapper.map(adddoctordto,Doctor.class);
        Doctor d=doctorRepository.save(doctor);
        return modelMapper.map(d,Doctordto.class);
    }
}
