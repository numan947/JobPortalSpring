package com.numan947.JobMicroService.job;

import com.numan947.JobMicroService.job.dto.JobDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<JobDTO>> findAllJobs(){
        return new ResponseEntity<>(jobService.findAllJobs(), HttpStatus.OK);
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<String> createJob(@RequestBody JobModel job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> findJobById(@PathVariable Long id){
        JobDTO j = jobService.findJobById(id);
        if (j == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(j, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        if (!jobService.deleteJob(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody JobModel job){
        if (!jobService.updateJob(id, job)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
    }
}
