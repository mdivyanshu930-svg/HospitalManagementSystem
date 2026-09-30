package com.codingbydivyanshu.yt.HospitalManagement.Controller;

import com.codingbydivyanshu.yt.HospitalManagement.Dto.Patientdto;
import com.codingbydivyanshu.yt.HospitalManagement.Dto.addPatientdto;
import com.codingbydivyanshu.yt.HospitalManagement.service.Patientservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patient")




public class PatientController {
    private final Patientservice patientservice;

    @GetMapping("/{id}")
    private ResponseEntity<Patientdto>getPatiendbyId(@PathVariable Long id){
        return ResponseEntity.ok(patientservice.getPatientbyId(id));
    }
    @PostMapping
    public ResponseEntity<Patientdto> addpatient(@RequestBody  addPatientdto addpatientdto){
        return ResponseEntity.status(HttpStatus.CREATED).body(patientservice.addpatient(addpatientdto));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deletepatient(@PathVariable Long id){
        patientservice.deletepatientbyids(id);
        return ResponseEntity.noContent().build();

    }

}

