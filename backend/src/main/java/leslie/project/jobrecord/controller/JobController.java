package leslie.project.jobrecord.controller;

import leslie.project.jobrecord.entity.JobBean;
import leslie.project.jobrecord.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/getappliedjobs")
    public ResponseEntity<List<JobBean>> getAppliedJobs() {
        List<JobBean> appliedjobs = jobService.getApplied();
        if (appliedjobs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(appliedjobs);
    }

}
//    @GetMapping("/jobreview")
//    public List jobreview(int period){
////    本周/两周/四周/八周/12周/6个月/一年 -- 1，2，3，4，5，6，7
//        if(period == 1){
//            //本周
//
//        }
//        return [23,23];
//    }
//
//}