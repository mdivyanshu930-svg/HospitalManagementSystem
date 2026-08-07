package com.codingbydivyanshu.yt.HospitalManagement.Controller;

import com.codingbydivyanshu.yt.HospitalManagement.Dto.Doctordto;
import com.codingbydivyanshu.yt.HospitalManagement.Dto.addDoctordto;
import com.codingbydivyanshu.yt.HospitalManagement.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    @GetMapping
    public ResponseEntity<List<Doctordto>> getdoctor(){
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.getalldoctor());
    }
    @PostMapping
    public ResponseEntity<Doctordto> adddoctor(@RequestBody addDoctordto adddoctordto){
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.adddoctor(adddoctordto));
    }
}
