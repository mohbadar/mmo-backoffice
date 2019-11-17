package af.asr.cassandra.util;

import com.datastax.driver.core.Cluster;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class ContactPointUtils {

    private ContactPointUtils() {
        super();
    }

    public static void process(final Cluster.Builder clusterBuilder, final String contactPoints) {
        final String[] splitContactPoints = contactPoints.split(",");
        for (final String contactPoint : splitContactPoints) {
            if (contactPoint.contains(":")) {
                final String[] address = contactPoint.split(":");
                clusterBuilder.addContactPointsWithPorts(
                        new InetSocketAddress(address[0].trim(), Integer.valueOf(address[1].trim())));
            } else {
                try {
                    clusterBuilder.addContactPoints(InetAddress.getByName(contactPoint.trim()));
                } catch (final UnknownHostException uhex) {
                    throw new IllegalArgumentException("Host not found!", uhex);
                }
            }
        }
    }
}