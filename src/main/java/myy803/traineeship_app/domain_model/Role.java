package myy803.traineeship_app.domain_model;

public enum Role {
	STUDENT("Student"),
	PROFESSOR("Professor"),
	COMPANY_MEMBER("Company"),
	COMMITTEE_MEMBER("Committee");
	

    private final String value;

    private Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
