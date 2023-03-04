package leslie.project.jobrecord.service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import leslie.project.jobrecord.entity.JobBean;
import leslie.project.jobrecord.repository.JobRepository;

@Service
public class JobService {
    // jobservice class's attribute (jobrepo type) and constructor
    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    public JobBean createNewJob(String position, String company, String level, Date applytime, String jobstate){
        JobBean newjob = new JobBean(position, company, level, applytime, jobstate);
        // add this job into mysql
        return jobRepository.save(newjob); //????? jobRepository.save()
    }

    public JobBean changeJobStatus(UUID id, String status){
        System.out.println("change service");
        System.out.println(id);
        System.out.println(status);
        JobBean currentjob = jobRepository.findById(id).orElse(null);
        if (currentjob != null){
            currentjob.setStatus(status);
            jobRepository.save(currentjob);
        }
        return currentjob;
    }

    public Optional<JobBean> getJobInfo(UUID id){
        return jobRepository.findById(id);
    }

    public int calculatePercentage(int timeperiod){
        if (timeperiod == 1){
            // percentage for last week
        }
        return 1;
    }
}
