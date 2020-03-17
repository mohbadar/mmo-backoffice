package af.gov.anar.lib.audit.util.sample;


import lombok.Getter;

import static af.gov.anar.lib.audit.util.AuditEventType.USER_EVENT;
import static af.gov.anar.lib.audit.util.AuditEventType.SYSTEM_EVENT;

/**
 * Enum for Audit Events
 *
 */
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

    // Navigation
    NAV_NEW_ANAR("ANAR-EVT-010", USER_EVENT.getCode(), "NAV_NEW_ANAR", "Click of navigation link: New ANARistration"),
    NAV_UIN_UPDATE("ANAR-EVT-011", USER_EVENT.getCode(), "NAV_UIN_UPDATE", "Navigation link: UIN Update"),
    NAV_APPROVE_ANAR("ANAR-EVT-012", USER_EVENT.getCode(), "NAV_APPROVE_ANAR", "Navigation link: Approve ANARistration"),
    NAV_SYNC_PACKETS("ANAR-EVT-013", USER_EVENT.getCode(), "NAV_SYNC_PACKETS", "Navigation link: Sync Packet IDs"),
    NAV_UPLOAD_PACKETS("ANAR-EVT-014", USER_EVENT.getCode(), "NAV_UPLOAD_PACKETS", "Navigation link: Upload Packets"),
    NAV_VIRUS_SCAN("ANAR-EVT-015", USER_EVENT.getCode(), "NAV_VIRUS_SCAN", "Navigation link: Virus Scan"),
    NAV_SYNC_DATA("ANAR-EVT-016", USER_EVENT.getCode(), "NAV_SYNC_DATA", "Navigation link: Sync Data"),
    NAV_DOWNLOAD_PRE_ANAR_DATA("ANAR-EVT-017", USER_EVENT.getCode(), "NAV_DOWNLOAD_PRE_ANAR_DATA", "Navigation link: Download Pre-ANARistration Data"),
    NAV_GEO_LOCATION("ANAR-EVT-018", USER_EVENT.getCode(), "NAV_GEO_LOCATION", "Navigation link: Geo-location"),
    NAV_ON_BOARD_USER("ANAR-EVT-019", USER_EVENT.getCode(), "NAV_ON_BOARD_USER", "Navigation link: On-board Users"),
    NAV_RE_ANARISTRATION("ANAR-EVT-020", SYSTEM_EVENT.getCode(), "NAV_RE_ANARISTRATION", "Navigation link: Re-ANARistration"),
    NAV_HOME("ANAR-EVT-021", SYSTEM_EVENT.getCode(), "NAV_HOME", "Navigation link: Home"),
    NAV_REDIRECT_HOME("ANAR-EVT-022", SYSTEM_EVENT.getCode(), "NAV_REDIRECT_HOME", "Navigation link: Redirect to Home"),
    NAV_ON_BOARD_DEVICES("ANAR-EVT-023", USER_EVENT.getCode(), "NAV_ON_BOARD_DEVICES", "Navigation link: On-board Devices"),

    // ANARistration : Demographics Details
    ANAR_DEMO_PRE_ANAR_DATA_FETCH("ANAR-EVT-024", USER_EVENT.getCode(), "ANAR_DEMO_PRE_ANAR_DATA_FETCH", "Pre-ANARistration: Fetch data for selected Pre-ANARistration"),
    ANAR_DEMO_NEXT("ANAR-EVT-025", USER_EVENT.getCode(), "ANAR_DEMO_NEXT", "Click of Next after capturing demographic details"),

    // ANARistration : Documents
    ANAR_DOC_POA_SCAN("ANAR-EVT-026", USER_EVENT.getCode(), "ANAR_DOC_POA_SCAN", "PoA: Click of Scan"),
    ANAR_DOC_POA_VIEW("ANAR-EVT-027", USER_EVENT.getCode(), "ANAR_DOC_POA_VIEW", "PoA: View"),
    ANAR_DOC_POA_DELETE("ANAR-EVT-028", USER_EVENT.getCode(), "ANAR_DOC_POA_DELETE", "PoA: Delete"),
    ANAR_DOC_POI_SCAN("ANAR-EVT-029", USER_EVENT.getCode(), "ANAR_DOC_POI_SCAN", "PoI: Click of Scan"),
    ANAR_DOC_POI_VIEW("ANAR-EVT-030", USER_EVENT.getCode(), "ANAR_DOC_POI_VIEW", "PoI: View"),
    ANAR_DOC_POI_DELETE("ANAR-EVT-031", USER_EVENT.getCode(), "ANAR_DOC_POI_DELETE", "PoI: Delete"),
    ANAR_DOC_POR_SCAN("ANAR-EVT-032", USER_EVENT.getCode(), "ANAR_DOC_POR_SCAN", "PoR: Click of Scan"),
    ANAR_DOC_POR_VIEW("ANAR-EVT-033", USER_EVENT.getCode(), "ANAR_DOC_POR_VIEW", "PoR: View"),
    ANAR_DOC_POR_DELETE("ANAR-EVT-034", USER_EVENT.getCode(), "ANAR_DOC_POR_DELETE", "PoR: Delete"),
    ANAR_DOC_POB_SCAN("ANAR-EVT-035", USER_EVENT.getCode(), "ANAR_DOC_POB_SCAN", "PoB: Click of Scan"),
    ANAR_DOC_POB_VIEW("ANAR-EVT-036", USER_EVENT.getCode(), "ANAR_DOC_POB_VIEW", "PoB: View"),
    ANAR_DOC_POB_DELETE("ANAR-EVT-037", USER_EVENT.getCode(), "ANAR_DOC_POB_DELETE", "PoB: Delete"),
    ANAR_DOC_POE_SCAN("ANAR-EVT-147", USER_EVENT.getCode(), "ANAR_DOC_POB_SCAN", "PoB: Click of Scan"),
    ANAR_DOC_POE_VIEW("ANAR-EVT-148", USER_EVENT.getCode(), "ANAR_DOC_POB_VIEW", "PoB: View"),
    ANAR_DOC_POE_DELETE("ANAR-EVT-149", USER_EVENT.getCode(), "ANAR_DOC_POB_DELETE", "PoB: Delete"),
    ANAR_DOC_NEXT("ANAR-EVT-038", USER_EVENT.getCode(), "ANAR_DOC_NEXT", "Click of Next after uploading documents"),
    ANAR_DOC_BACK("ANAR-EVT-039", USER_EVENT.getCode(), "ANAR_DOC_BACK", "Click of Back to demographic details"),

    // ANARistration: Operator/Supervisor Authentication
    ANAR_OPERATOR_AUTH_PASSWORD("ANAR-EVT-058", USER_EVENT.getCode(), "ANAR_OPERATOR_AUTH_PASSWORD", "Operator authentication with password: Click of Submit"),
    ANAR_OPERATOR_AUTH_GET_OTP("ANAR-EVT-059", USER_EVENT.getCode(), "ANAR_OPERATOR_AUTH_GET_OTP", "Operator authentication with OTP: Get OTP"),
    ANAR_OPERATOR_AUTH_SUBMIT_OTP("ANAR-EVT-060", USER_EVENT.getCode(), "ANAR_OPERATOR_AUTH_SUBMIT_OTP", "Operator authentication with OTP: Submit OTP"),
    ANAR_OPERATOR_AUTH_RESEND_OTP("ANAR-EVT-061", USER_EVENT.getCode(), "ANAR_OPERATOR_AUTH_RESEND_OTP", "Operator authentication with OTP: Resend OTP"),
    ANAR_OPERATOR_AUTH_PREVIEW("ANAR-EVT-065", USER_EVENT.getCode(), "ANAR_OPERATOR_AUTH_PREVIEW", "Back to Preview"),
    ANAR_ACK_PRINT("ANAR-EVT-066", USER_EVENT.getCode(), "ANAR_ACK_PRINT", "Print receipt"),
    ANAR_SUPERVISOR_AUTH_PASSWORD("ANAR-EVT-067", USER_EVENT.getCode(), "ANAR_SUPERVISOR_AUTH_PASSWORD", "Supervisor authentication with password: Click of Submit"),
    ANAR_SUPERVISOR_AUTH_GET_OTP("ANAR-EVT-068", USER_EVENT.getCode(), "ANAR_SUPERVISOR_AUTH_GET_OTP", "Supervisor authentication with OTP: Get OTP"),
    ANAR_SUPERVISOR_AUTH_SUBMIT_OTP("ANAR-EVT-069", USER_EVENT.getCode(), "ANAR_SUPERVISOR_AUTH_SUBMIT_OTP", "Supervisor authentication with OTP: Submit OTP"),
    ANAR_SUPERVISOR_AUTH_RESEND_OTP("ANAR-EVT-070", USER_EVENT.getCode(), "ANAR_SUPERVISOR_AUTH_RESEND_OTP", "Supervisor authentication with OTP: Resend OTP"),
    ANAR_SUPERVISOR_AUTH_PREVIEW("ANAR-EVT-074", USER_EVENT.getCode(), "ANAR_SUPERVISOR_AUTH_PREVIEW", "Back to Preview");

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