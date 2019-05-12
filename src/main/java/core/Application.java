package core;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.StudentEntity;
import entities.UserEntity;

/**
 * Main method for testing
 * @author: Bora Bejleri
 */

public class Application {

	public static void main(String[] args) {

   StudentEntity student = new StudentEntity();
   student.setName("Bora");
   student.setAddress("Bamberg, Germany");
   student.setEmail("test@gmail.com");
   student.setPassword("1234");
   student.setFirstskill("java");
   student.setSecondskill(".net");
   student.setThirdskill("Javascript");
   student.setBirthday(LocalDate.of(
	        2019, 05, 12));
   student.setEu(false);
   student.setMothertounge("Albanian");
   student.setNationality("ALB");
   student.setUniversity("Uni Bamberg");
   student.setQualification("Masters");
   
   UserEntity user = new UserEntity();
   user.setStudent(student);    
   
   EntityManagerFactory emf = Persistence.createEntityManagerFactory("mobi");
   EntityManager em = emf.createEntityManager();
   
   em.getTransaction().begin();
   em.persist(student);
   em.persist(user);
   em.getTransaction().commit();
   em.close();

	}

}
