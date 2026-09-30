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
@RequestMapping("/public")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    @GetMapping("/doctors")
    public ResponseEntity<List<Doctordto>> getdoctor(){
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.getalldoctor());
    }


}
