package lt.techin.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "cat_adoptions")
public class CatAdoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String catName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CatAdoptionStatus catAdoptionStatus;

    private LocalDate applicationDate;

    public CatAdoption(User user, String catName, CatAdoptionStatus catAdoptionStatus, LocalDate applicationDate) {
        this.user = user;
        this.catName = catName;
        this.catAdoptionStatus = catAdoptionStatus;
        this.applicationDate = applicationDate;
    }

    public CatAdoption() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public CatAdoptionStatus getCatAdoptionStatus() {
        return catAdoptionStatus;
    }

    public void setCatAdoptionStatus(CatAdoptionStatus catAdoptionStatus) {
        this.catAdoptionStatus = catAdoptionStatus;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }
}
