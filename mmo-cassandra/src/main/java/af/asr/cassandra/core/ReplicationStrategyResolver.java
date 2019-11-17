package af.asr.cassandra.core;


@SuppressWarnings("WeakerAccess")
public class ReplicationStrategyResolver {

    private ReplicationStrategyResolver() {
        super();
    }

    public static String replicationStrategy(final String type, final String replicas) {
        final StringBuilder builder = new StringBuilder();
        if (type.equalsIgnoreCase("Simple")) {
            builder.append("{'class': 'SimpleStrategy', ");
            builder.append("'replication_factor': ");
            builder.append(replicas);
            builder.append("}");
            return builder.toString();
        } else if (type.equalsIgnoreCase("Network")) {
            builder.append("{'class': 'NetworkTopologyStrategy', ");

            final String[] splitReplicas = replicas.split(",");
            for (int i = 0; i < splitReplicas.length; i++) {
                final String[] replicaDataCenter = splitReplicas[i].split(":");
                builder.append("'").append(replicaDataCenter[0].trim()).append("': ");
                builder.append(replicaDataCenter[1].trim());
                if ((i + 1) < splitReplicas.length) {
                    builder.append(", ");
                }
            }

            builder.append("}");
            return builder.toString();
        } else {
            throw new IllegalArgumentException("Unknown replication strategy: " + type);
        }
    }
}