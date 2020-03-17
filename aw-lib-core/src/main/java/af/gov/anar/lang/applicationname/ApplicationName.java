package af.gov.anar.lang.applicationname;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SuppressWarnings("WeakerAccess")
public class ApplicationName {

    final private String serviceName;
    final private String versionString;

    private ApplicationName(final String serviceName, final String versionString) {
        this.serviceName = serviceName;
        this.versionString = versionString;
    }

    public static ApplicationName fromSpringApplicationName(final String springApplicationName) {
        return parse(springApplicationName);
    }

    public static ApplicationName appNameWithVersion(final String serviceName, final String versionString) {
        return new ApplicationName(serviceName, versionString);
    }

    static ApplicationName parse(final String springApplicationNameString) {
        if (springApplicationNameString.length() > 64) {
            throw new IllegalArgumentException("Spring application name strings for Platform applications should be 64 characters or less.");
        }

        final Pattern applicationNamePattern = Pattern.compile(
                "^(/??(?<name>\\p{Lower}[\\p{Lower}_]+)(?:-v(?<version>\\d[\\d\\._]*))?)$");

        final Matcher applicationNameMatcher = applicationNamePattern.matcher(springApplicationNameString);
        if (!applicationNameMatcher.matches()) {
            throw new IllegalArgumentException(
                    "This is not a spring application name string for application: "
                            + springApplicationNameString);
        }
        String versionString = applicationNameMatcher.group("version");
        if (versionString == null) {
            throw new IllegalArgumentException("Application name: '" + springApplicationNameString + "' requires a version.  For example 'amit/v1'.");
        }

        final String serviceName = applicationNameMatcher.group("name");
        return new ApplicationName(serviceName, versionString);
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getVersionString() {
        return versionString;
    }

    @Override
    public String toString() {
        return serviceName + "-v" + versionString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ApplicationName))
            return false;
        ApplicationName that = (ApplicationName) o;
        return Objects.equals(serviceName, that.serviceName) && Objects
                .equals(versionString, that.versionString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceName, versionString);
    }
}