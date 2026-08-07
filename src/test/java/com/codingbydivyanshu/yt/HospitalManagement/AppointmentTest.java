package com.codingbydivyanshu.yt.HospitalManagement;

import com.codingbydivyanshu.yt.HospitalManagement.entity.Appointment;
import com.codingbydivyanshu.yt.HospitalManagement.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest

public class AppointmentTest {
    @Autowired
    private AppointmentService appointmentService;
    @Test
    public void CreateAppointment() {
        Appointment appointment = Appointment.builder().appointmentTime(LocalDateTime.of(2026, 7, 22, 19, 0, 0)).reason("Cancer").build();
        var newAppointment = appointmentService.createNewappointment(appointment, 2L, 3L);
        System.out.println(newAppointment);
        var newDoctor =appointmentService.assingingnewDoctor(newAppointment.getId(),3L);
        System.out.println(newDoctor);

    }
/* //   @Test
   // public void threeappointment(){
     //   Appointment appointment= Appointment.builder().appointmentTime(LocalDateTime.of(2026,7,24,16,30,00))
       //         .reason("sugar").build();
        //var Appointment1=appointmentService.creatingthreeAppointment(appointment,2L,4L);
      //  System.out.println(Appointment1);
      //  Appointment appointment1= Appointment.builder().appointmentTime(LocalDateTime.of
                        (2026,7,29,16,30,00))
                .reason("Blood Pressure").build();
        var Appointment2=appointmentService.creatingthreeAppointment
                (appointment1,2L,2L);
        System.out.println(Appointment2);
        Appointment appointment2= Appointment.builder().appointmentTime(LocalDateTime.of
                        (2026,8,15,16,30,00))
                .reason("Fever").build();
        var Appointment3=appointmentService.creatingthreeAppointment
                (appointment2,2L,3L);
        System.out.println(Appointment3);
*/

    }

