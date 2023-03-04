package leslie.project.jobrecord.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
// 要用我们自己建的interface去extend原来的jpa-interface
import org.springframework.stereotype.Repository;

import leslie.project.jobrecord.entity.JobBean;

@Repository
public interface JobRepository extends JpaRepository<JobBean,UUID> {
    //这个interface的input为什么是这样我也没看懂？？？？？？？？？？
}
