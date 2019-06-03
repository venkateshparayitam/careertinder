package com.softwaregiants.careertinder.utilities;

public interface Constants {
    String BASE_URL = "http://192.168.1.102:8080/" ;

    //API Methods
    String API_METHOD_LOGIN = "login";
    String API_METHOD_SIGNUP = "sign_up";
    String API_METHOD_ADD_NEW_JOB_OPENING = "add_job_opening";
    String API_METHOD_POST_SIGNUP = "post_signup";
    String API_METHOD_GET_JOB_OPENINGS = "get_job_list";
    String API_METHOD_EDIT_JOB_OPENING = "edit_job_opening";
    String API_METHOD_GET_CANDIDATE_PROFILE = "GET_CANDIDATE_PROFILE";

    //Success Codes
    String SC_JOB_CREATED_SUCCESS = "job_opening_created";
    String SC_SUCCESS = "Success";

    //Messages
    String MSG_CONNECTION_ERROR = "There seems to an error connecting to the internet.";
    String MSG_TECHNICAL_ERROR = "There is a technical error. Please try again.";
    String MSG_ERROR = "Error";
    String MSG_LOGIN_ERROR = "Please login again.";

    //Preference Keys
    String PK_AUTH_CODE = "PK_AUTH_CODE";
    String PK_LOGIN_STATE = "PK_LOGIN_STATE";
    String PK_USER_TYPE = "PK_USER_TYPE";
    String PK_EMAIL = "PK_EMAIL";
    String PK_PROFILE_CREATED = "PK_PROFILE_CREATED";

    //Misc
    String USER_TYPE_JOB_SEEKER = "jobseeker";
    String USER_TYPE_EMPLOYER = "employer";

    //Dev Mode
    boolean isDeveloperBuild = true;
    String PK_CUSTOM_URL = "PK_CUSTOM_URL";

    //Activity result codes
    int NEED_RESULT_JOB_OPENING_CREATION = 12345;
}
