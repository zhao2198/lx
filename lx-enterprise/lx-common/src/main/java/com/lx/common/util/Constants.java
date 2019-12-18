
package com.lx.common.util;

public interface Constants {

	int SALT_SIZE = 8;

	int HASH_INTERATIONS = 1024;

	String USER_REDIS_KEY = "redis_user_session";

	String TOKEN_KEY = "NGT_TOKEN";

	String TOKEN_BACK_KEY = "NGT_BACK_TOKEN";

	int SESSION_EXPIRE = 1800;

	String TOKEN_AUTHORIZATION = "Authorization";

	String SESSION_LOGIN_NAME = "loginName";

	String SESSION_LOGIN_USER_ID = "userId";

	String SESSION_LOGIN_COMPANY_ID = "companyId";

	String SESSION_LOGIN_OFFICE_ID = "officeId";

	String SESSION_SYSTEM_LOGIN_KEY = "systemLoginKey";

	String DEFAULT_PASSWORD = "888888";

	int SYSTYPE_NGTONLINE = 1;

	String SYSTEM_DELETE_FLAG = "0";

	String SYSTEM_DELETE_FLAG_DELETE = "1";

	String SYSTEM_IS_NOT_SYSTEM = "0";

	String SYSTEM_DEFAULT_DATA_SCOPE = "5";

	String SYSTEM_DUTY_PLAN_PUNLISH_NO = "0";

	String SYSTEM_DUTY_PLAN_PUNLISH_YSE = "1";

	String SYSTEM_SHIFTCLASS_APPLYSTATUS_NO = "0";

	String SYSTEM_SHIFTCLASS_APPLYSTATUS_APPROVE = "1";

	String SYSTEM_SHIFTCLASS_APPLYSTATUS_REJECT = "2";

	String SYSTEM_DUTYMISSION_NON_EXECUTION = "0";// 值班任务 0---未执行 1--申请调班（执行受阻） 2---已执行

	String SYSTEM_DUTYMISSION_EXECUTION_NA = "1";

	String SYSTEM_DUTYMISSION_EXECUTIONED = "2";

}
