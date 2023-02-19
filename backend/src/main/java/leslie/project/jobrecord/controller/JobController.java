package leslie.project.jobrecord.controller;

import leslie.project.jobrecord.entity.JobBean;
import leslie.project.jobrecord.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<JobBean> createJob(@RequestBody String position, String company, String level, Date applytime, String jobstate) {
        JobBean createdJob = jobService.createNewJob(position, company, level, applytime, jobstate);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdJob);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<JobBean> changeStatus(@PathVariable UUID id, @RequestBody Map<String, String> status){
        // requestbody是用来把requestbody里的东西bind 给一个method parameter。 但是pathvariable是把url里的value bind给method parameter
        JobBean updatedJob = jobService.changeJobStatus(id, status.get("status"));
        return ResponseEntity.ok(updatedJob); /// ??????/what does responseentity.ok do?
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobBean> getInfo(@PathVariable UUID id) {
        Optional<JobBean> job = jobService.getJobInfo(id);
        if (!job.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(job.get());
    }

}
