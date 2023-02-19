package leslie.project.jobrecord;

import leslie.project.jobrecord.entity.JobBean;
import leslie.project.jobrecord.repository.JobRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ConnectionTest {
    @Autowired
    private JobRepository jobRepository;

    @Test
    public void testConnection() {
        JobBean job = new JobBean();
        jobRepository.save(job);
    }
}
