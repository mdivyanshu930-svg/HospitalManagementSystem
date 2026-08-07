package com.codingbydivyanshu.yt.HospitalManagement.service;

import com.codingbydivyanshu.yt.HospitalManagement.Repository.AppointmentRepository;
import com.codingbydivyanshu.yt.HospitalManagement.Repository.DoctorRepository;
import com.codingbydivyanshu.yt.HospitalManagement.Repository.PatientRepository;
import com.codingbydivyanshu.yt.HospitalManagement.entity.Appointment;
import com.codingbydivyanshu.yt.HospitalManagement.entity.Doctor;
import com.codingbydivyanshu.yt.HospitalManagement.entity.patient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor


public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    @Transactional
    public Appointment createNewappointment(Appointment appointment,Long doctorId,Long patientId){
        Doctor doctor=doctorRepository.findById(doctorId).orElseThrow();
        patient Patient=patientRepository.findById(patientId).orElseThrow();
        if(appointment.getId()!=null) throw new IllegalArgumentException("Appointmemt cannot be created");
        appointment.setPatient( Patient);
        appointment.setDoctor(doctor);

        Patient.getAppointments().add(appointment);
        return  appointmentRepository.save(appointment);






    }
    @Transactional
    public Appointment assingingnewDoctor(Long appointmentId,Long doctorId){
        Appointment appointment=appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor=doctorRepository.findById(doctorId).orElseThrow();
        appointment.setDoctor(doctor);
        doctor.getAppointments().add(appointment);
        return appointment;



    }
    @Transactional
    public Appointment creatingthreeAppointment(Appointment appointment,Long patientId,Long doctorId){
        patient Patient=patientRepository.findById(patientId).orElseThrow();
        Doctor doctor=doctorRepository.findById(doctorId).orElseThrow();
        appointment.setPatient(Patient);
        appointment.setDoctor(doctor);
        Patient.getAppointments().add(appointment);
        doctor.getAppointments().add(appointment);
        return appointmentRepository.save(appointment);

    }
}
