
package com.codingbydivyanshu.yt.HospitalManagement.Repository;
import com.codingbydivyanshu.yt.HospitalManagement.Dto.BloodGroupCountResponseEntity;
import com.codingbydivyanshu.yt.HospitalManagement.entity.patient;
import com.codingbydivyanshu.yt.HospitalManagement.type.Bloodgrouptype;
import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface PatientRepository extends JpaRepository<patient,Long> {

 //  List<patient> findByNameOrEmail(String name, String email);
 //  @Query("select p from patient p where p.bloodgroup=?1")
  // List<patient> findByBloodGroup(@Param("BloodGroup") Bloodgrouptype bloodgroup);

   @Query("select p from patient p where p.DOB>:birthdate")
           List<patient> findByDOB(@Param("birthdate") LocalDate DOb);
   @Query("select new com.codingbydivyanshu.yt.HospitalManagement.Dto.BloodGroupCountResponseEntity (p.bloodgroup,Count(p) )from patient p group by p.bloodgroup ")
   List<BloodGroupCountResponseEntity>countEachBloodGroupType();

   @Query(value = "select * from Patient",nativeQuery = true)
   Page<patient> findallpatient(Pageable pageable);
   @Query("select p from patient p left join fetch p.appointments")
   List<patient>findAllWithAppointment();

   @Transactional
   @Modifying
   @Query("update patient p set p.name=:name where p.id=:id")
   int Updatenamewithid(@Param("name")String name,@Param("id") Long id);


   List<patient> findByNameContaining(String name);
}
