package com.codingbydivyanshu.yt.HospitalManagement.Repository;

import com.codingbydivyanshu.yt.HospitalManagement.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
