package com.rts.tap.constants;

public class APIConstants {

	private APIConstants() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static final String BASE_URL = "/admin-service/tap";
	public static final String FRONT_END_URL = "http://localhost:3000";

	// TEAM - A
	public static final String ADD_ADMIN_URL = "/createadmin";
	public static final String GET_ALL_ADMINS_URL = "/admins";
	public static final String GET_ADMIN_BY_ID_URL = "/admin/{id}";

	public static final String CROSS_ORIGIN_URL = "http://localhost:3000";

	// ORGANIZATION
	public static final String ADD_ORGANIZATION_URL = "/createorganization";
	public static final String UPDATE_ORGANIZATION_URL = "/updateorganization/{id}";
	public static final String GETALL_ORGANIZATION_URL = "/getallorganization";

	// BUSINESS UNIT
	public static final String ADD_BUSINESSUNIT_URL = "/createbusinessunit";
	public static final String GETALL_BUSINESSUNIT_URL = "/getallbusinessunit";

	public static final String GET_BUSINESSUNIT_BY_LOCATION_URL = "/getbusinessunitbylocation/{location}";
	public static final String UPDATE_BUSINESSUNIT_URL = "/updatebusinessunit/{id}";

	// DEPARTMENT
	public static final String ADD_DEPARTMENT_URL = "/createdepartment";
	public static final String UPDATE_DEPARTMENT_URL = "/updatedepartment/{id}";
	public static final String GETALL_DEPARTMENT_URL = "/getalldepartment";
	public static final String GET_DEPARTMENT_BY_ID_URL = "/getdepartment/{id}";

	// ROLE
	public static final String ADD_ROLE_URL = "/createrole";
	public static final String GETALL_ROLE_URL = "/getallrole";
	public static final String UPDATE_ROLE_URL = "/updaterole/{id}";
	public static final String GET_ROLE_BY_ID_URL = "/getrolebyid/{id}";

	// LOCATION
	public static final String ADD_ORG_LOCATION_URL = "/organizationlocation";
	public static final String GET_ALL_ORG_LOCATIONS_URL = "/getallorganizationlocations";
	public static final String GET_ORG_LOCATION_BY_ID_URL = "/getorganizationlocation/{id}";
	public static final String UPDATE_ORG_LOCATION_URL = "/updateorganizationlocation/{id}";

}
