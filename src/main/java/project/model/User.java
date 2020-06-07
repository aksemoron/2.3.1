package project.model;


import javax.persistence.*;

@Entity
@Table(name = "crudspringuser")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    public User(long id) {
        this.id = id;
    }

    public User(long id, String name, String family, long balans) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.balans = balans;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "family")
    private String family;

    @Column(name = "balans")
    private long balans;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public long getBalans() {
        return balans;
    }

    public void setBalans(long balans) {
        this.balans = balans;
    }

    public User() {
    }

    public User(String name, String family, long balans) {
        this.name = name;
        this.family = family;
        this.balans = balans;
    }
}
