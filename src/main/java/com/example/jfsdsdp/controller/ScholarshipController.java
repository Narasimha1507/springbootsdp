package com.example.jfsdsdp.controller;

import com.example.jfsdsdp.model.Application;
import com.example.jfsdsdp.model.Scholarship;
import com.example.jfsdsdp.service.ApplicationService;
import com.example.jfsdsdp.service.ScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/scholarships")
@CrossOrigin(origins = "http://localhost:3000")
public class ScholarshipController {

    @Autowired
    private ScholarshipService scholarshipService;
    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/add")
    public ResponseEntity<String> addScholarship(@RequestBody Scholarship scholarship) {
        scholarshipService.saveScholarship(scholarship);
        return ResponseEntity.ok("Scholarship added successfully!");
    }

    // New endpoint to retrieve all scholarships
    @GetMapping("/all")
    public ResponseEntity<List<Scholarship>> getAllScholarships() {
        List<Scholarship> scholarships = scholarshipService.getAllScholarships();
        return ResponseEntity.ok(scholarships);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScholarship(@PathVariable Long id) {
        scholarshipService.deleteScholarshipById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/applicationCounts")
    public ResponseEntity<Map<String, Long>> getApplicationCounts() {
        Map<String, Long> counts = scholarshipService.getApplicationCounts();
        return ResponseEntity.ok(counts);
    }
    @GetMapping("/applications")
    public ResponseEntity<List<Application>> getApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }
}
