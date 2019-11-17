package af.asr.async.util;

public interface AsyncConstants {

    String CORE_POOL_SIZE_PROP = "async.corePoolSize";
    String CORE_POOL_SIZE_DEFAULT = "32";
    String MAX_POOL_SIZE_PROP = "async.maxPoolSize";
    String MAX_POOL_SIZE_DEFAULT = "16384";
    String QUEUE_CAPACITY_PROP = "async.queueCapacity";
    String QUEUE_CAPACITY_DEFAULT = "0";
    String THREAD_NAME_PROP = "async.threadName";
    String THREAD_NAME_DEFAULT = "async-processor-";
}
