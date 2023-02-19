package leslie.project.jobrecord.entity;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class JobBean {
    // why do i have to mark these @? id, generated value, entity?
    @Id
    @GeneratedValue
    private UUID id;
    private String position;
    private String company;
    private String level;
    private Date applytime;
    private String jobstate;
    // private (only access within class)
    public JobBean() {}

    public JobBean(String position, String company, String level, Date applytime, String jobstate){
        // how to generate an uuid / ?????? why cgpt doesn't assign to id with uuid?

        this.id = UUID.randomUUID();
        this.position = position;
        this.company = company;
        this.level = level;
        this.applytime = applytime;
        this.jobstate = jobstate;
    }
    public UUID getId() {
        return id;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setTime(Date time) {
        this.applytime = time;
    }

    public Date getTime() {
        return applytime;
    }

    public void setStatus(String status) {
        this.jobstate = status;
    }

    public String getStatus() {
        return jobstate;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
}

