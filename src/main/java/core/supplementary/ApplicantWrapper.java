package core.supplementary;

import java.util.List;

import core.entities.CTApplicantEntity;

/**
 * @author: Bora Bejleri
 */

public class ApplicantWrapper {
	
	private List<CTApplicantEntity> applicant_profiles;

	public List<CTApplicantEntity> getApplicant_profiles() {
		return applicant_profiles;
	}

	public void setApplicant_profiles(List<CTApplicantEntity> applicant_profiles) {
		this.applicant_profiles = applicant_profiles;
	}

}
