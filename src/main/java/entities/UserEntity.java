package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserEntity
 * @author: Bora Bejleri
 */

@Entity
@Table(name="user", catalog="career_tinder")
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;    
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
		

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity student;

	public Long getId() {
		return id;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}
	
	
   
}
