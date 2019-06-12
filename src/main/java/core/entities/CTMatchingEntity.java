package core.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * author: Bora Bejleri
 */

@Entity
@Table(name="ctmatching", catalog="career_tinder")
public class CTMatchingEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@Column(name = "applicant_id")
	private Long applicant_id;
	
	@Column(name = "company_id")
	private Long company_id;
	
	@Column(name = "applicant_swipe", updatable = true, nullable = true)
	private int applicant_swipe = 0; //default value 0 - offer not seen yet
	
	@Column(name = "company_swipe", updatable = true, nullable = true)
	private int company_swipe = 0;
	
	@Column(name = "matching_percentage", nullable = true, updatable = true)
	private int percentage;

	public Long getId() {
		return id;
	}

	public Long getApplicant_id() {
		return applicant_id;
	}

	public void setApplicant_id(Long applicant_id) {
		this.applicant_id = applicant_id;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	public int getApplicant_swipe() {
		return applicant_swipe;
	}

	public void setApplicant_swipe(int applicant_swipe) {
		this.applicant_swipe = applicant_swipe;
	}

	public int getCompany_swipe() {
		return company_swipe;
	}

	public void setCompany_swipe(int company_swipe) {
		this.company_swipe = company_swipe;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

}
