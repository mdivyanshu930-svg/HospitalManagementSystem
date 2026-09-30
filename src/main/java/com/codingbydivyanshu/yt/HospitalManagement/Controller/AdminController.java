package com.codingbydivyanshu.yt.HospitalManagement.Controller;

import com.codingbydivyanshu.yt.HospitalManagement.Dto.Doctordto;
import com.codingbydivyanshu.yt.HospitalManagement.Dto.OnboardDoctorRequestDto;
import com.codingbydivyanshu.yt.HospitalManagement.Dto.Patientdto;
import com.codingbydivyanshu.yt.HospitalManagement.service.DoctorService;
import com.codingbydivyanshu.yt.HospitalManagement.service.Patientservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor


public class AdminController {
    private final Patientservice patientservice;
    private final DoctorService doctorService;
    @GetMapping("/patients")
    public ResponseEntity<List<Patientdto>>get(
            @RequestParam (value = "page",defaultValue = "0")Integer pagenumber,
            @RequestParam(value = "size",defaultValue = "10")Integer pagesize){
        return ResponseEntity.ok(patientservice.getallPatient(pagenumber,pagesize));

    }
    @PostMapping("/OnBoardnewDoctors")
    public ResponseEntity<Doctordto>onboard(@RequestBody OnboardDoctorRequestDto onboardDoctorRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.addonboarddoctor(onboardDoctorRequestDto));
    }

}
