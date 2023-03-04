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

    @PatchMapping(value = "/hello")
    public String add(String position) {
        System.out.println(position);
        System.out.println("hello");
        return "Hello";
    }

    @PostMapping(value = "/createjob")
    public ResponseEntity<JobBean> createJob(@RequestBody(required=false) JobBean job) {
        String position = job.getPosition();
        String company = job.getCompany();
        String level = job.getLevel();
        Date applytime = job.getTime();
        String jobstate = job.getStatus();
        JobBean createdJob = jobService.createNewJob(position, company, level, applytime, jobstate);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdJob);
    }


    @PatchMapping(value = "/changestatus/{id}")
    public ResponseEntity<JobBean> changeStatus(@PathVariable String id, @RequestBody Map<String,String> status){
        // requestbody是用来把requestbody里的东西bind 给一个method parameter。 但是pathvariable是把url里的value bind给method parameter
        System.out.println("change controller");
        System.out.println(id);
//        System.out.println(status);
        JobBean updatedJob = jobService.changeJobStatus(UUID.fromString(id), status.get("status"));
        return ResponseEntity.ok(updatedJob); /// ??????/what does responseentity.ok do?
    }

    @GetMapping("/getinfo/{id}")
    public ResponseEntity<JobBean> getInfo(@PathVariable String id) {
        Optional<JobBean> job = jobService.getJobInfo(UUID.fromString(id));
        if (!job.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(job.get());
    }

}
