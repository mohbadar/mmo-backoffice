### Audit Library of ePayFrame

#### Usage Guide

```
		<dependency>
	               <groupId>af.gov.anar.lib</groupId>
	                <artifactId>anar-lib-audit</artifactId>
                        <version>${project.version}</version>
		</dependency>

```

Create AuditEvent enum for each action in your service. like following

```$xslt
@Getter
public enum AuditEvent {

    // Login
    LOGIN_AUTHENTICATE_USER_ID("ANAR-EVT-001", USER_EVENT.getCode(), "LOGIN_AUTHENTICATE_USER_ID", "Login authenticating user id: Click of Submit"),
    LOGIN_WITH_PASSWORD("ANAR-EVT-002", USER_EVENT.getCode(), "LOGIN_WITH_PASSWORD", "Login with password: Click of Submit"),
    LOGIN_GET_OTP("ANAR-EVT-003", USER_EVENT.getCode(), "LOGIN_GET_OTP", "Login with OTP: Get OTP"),
    LOGIN_SUBMIT_OTP("ANAR-EVT-004", USER_EVENT.getCode(), "LOGIN_SUBMIT_OTP", "Login with OTP: Submit OTP"),
    LOGIN_RESEND_OTP("ANAR-EVT-005", USER_EVENT.getCode(), "LOGIN_RESEND_OTP", "Login with OTP: Resend OTP"),
    LOGIN_WITH_FINGERPRINT("ANAR-EVT-006", USER_EVENT.getCode(), "LOGIN_WITH_FINGERPRINT", "Login with fingerprint: Capture and submit"),
    LOGOUT_USER("ANAR-EVT-009", USER_EVENT.getCode(), "LOGOUT_USER", "Logout"),

    /**
     * The constructor
     */
    private AuditEvent(String id, String type, String name, String description) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
    }

    private final String id;
    private final String type;
    private final String name;
    private final String description;

}
```

and use an audithandler

```$xslt

    

	@Autowired
	private AuditHandler<AuditRequestDto> auditHandler;


		AuditRequestBuilder auditRequestBuilder = new AuditRequestBuilder();
		auditRequestBuilder.setActionTimeStamp(LocalDateTime.now(ZoneOffset.UTC))
				.setApplicationId(String.valueOf(ApplicationContext.map().get(Constants.APP_ID)))
				.setApplicationName(String.valueOf(ApplicationContext.map().get(Constants.APP_NAME)))
				.setCreatedBy(userService.userName()).setDescription(auditEventEnum.getDescription())
				.setEventId(auditEventEnum.getId()).setEventName(auditEventEnum.getName())
				.setEventType(auditEventEnum.getType()).setHostIp(hostIP).setHostName(hostName).setId(refId)
				.setIdType(refIdType).setModuleId(appModuleEnum.getId()).setModuleName(appModuleEnum.getName())
				.setSessionUserId(SessionContext.userId()).setSessionUserName(SessionContext.userName());
		auditHandler.addAudit(auditRequestBuilder.build());

```
#### Documentation

Refer to this repository **Wiki** section.
https://github.com/Anar-Framework/anar-lib-audit/wiki



