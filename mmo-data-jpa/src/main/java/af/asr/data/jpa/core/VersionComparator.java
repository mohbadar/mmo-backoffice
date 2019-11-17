package af.asr.data.jpa.core;


import liquibase.changelog.ChangeSet;

public class VersionComparator {

    private VersionComparator() {
        super();
    }

    public static int compare(final ChangeSet firstChangeSet, final ChangeSet secondChangeSet) {
        final String firstId = firstChangeSet.getId();
        final String secondId = secondChangeSet.getId();

        final String[] splitFirstId = firstId.split("\\.");
        final String[] splitSecondId = secondId.split("\\.");

        int index = 0;

        while (index < splitFirstId.length
                && index < splitSecondId.length
                && splitFirstId[index].equals(splitSecondId[index])) {
            index++;
        }

        if (index < splitFirstId.length && index < splitSecondId.length) {
            return Integer.valueOf(splitFirstId[index]).compareTo(Integer.valueOf(splitSecondId[index]));
        }

        return Integer.valueOf(splitFirstId.length).compareTo(Integer.valueOf(splitSecondId.length));
    }
}