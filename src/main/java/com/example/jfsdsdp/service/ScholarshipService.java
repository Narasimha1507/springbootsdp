package com.example.jfsdsdp.service;

import com.example.jfsdsdp.model.Scholarship;
import com.example.jfsdsdp.repository.ApplicationRepository;
import com.example.jfsdsdp.repository.ScholarshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScholarshipService {

    @Autowired
    private ScholarshipRepository scholarshipRepository;
    
    @Autowired
    private ApplicationRepository applicationRepository;

    public Scholarship saveScholarship(Scholarship scholarship) {
        return scholarshipRepository.save(scholarship);
    }

    // New method to retrieve all scholarships
    public List<Scholarship> getAllScholarships() {
        return scholarshipRepository.findAll();
    }
    public void deleteScholarshipById(Long id) {
        scholarshipRepository.deleteById(id);
    }
 // New method to fetch counts by application status
    public Map<String, Long> getApplicationCounts() {
        List<Object[]> counts = applicationRepository.countApplicationsByStatus();
        Map<String, Long> statusCounts = new HashMap<>();

        for (Object[] count : counts) {
            String status = (String) count[0];
            Long countValue = (Long) count[1];
            statusCounts.put(status, countValue);
        }

        // Ensure missing statuses return 0
        statusCounts.putIfAbsent("Approved", 0L);
        statusCounts.putIfAbsent("Pending", 0L);
        statusCounts.putIfAbsent("Rejected", 0L);

        return statusCounts;
    }
}
