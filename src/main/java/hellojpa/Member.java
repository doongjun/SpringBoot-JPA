package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Member {

    @Id
    private String id;
 
    @Column(name = "name", nullable = false)
    private String username;

    public Member() {
    }
}
