package af.asr.cassandra.util;


import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.TypeCodec;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;

public final class CodecRegistry {

    private static final ArrayList<TypeCodec<?>> REGISTERED_CODECS = new ArrayList<>();

    private CodecRegistry() {
        super();
    }

    @SuppressWarnings({
            "unchecked"
    })
    public static <T> void register(@Nonnull final TypeCodec<T>... typeCodec) {
        Assert.notNull(typeCodec, "A type codec must be given.");
        Assert.notEmpty(typeCodec, "At least one type code must be given.");
        CodecRegistry.REGISTERED_CODECS.addAll(Arrays.asList(typeCodec));
    }

    public static void apply(@Nonnull final Cluster cluster) {
        Assert.notNull(cluster, "A cluster must be given.");
        cluster.getConfiguration().getCodecRegistry().register(CodecRegistry.REGISTERED_CODECS);
        CodecRegistry.REGISTERED_CODECS.clear();
    }
}