package com.codingbydivyanshu.yt.HospitalManagement.Repository;

import com.codingbydivyanshu.yt.HospitalManagement.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
