package af.asr.command.repository;

import af.asr.command.util.CommandConstants;
import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;


import java.util.Date;

@SuppressWarnings("unused")
@Table(name = CommandConstants.COMMAND_SOURCE_TABLE_NAME)
public final class CommandSource {

    @SuppressWarnings("DefaultAnnotationParam")
    @PartitionKey(0)
    @Column(name = "source")
    private String source;
    @PartitionKey(1)
    @Column(name = "bucket")
    private String bucket;
    @SuppressWarnings("DefaultAnnotationParam")
    @ClusteringColumn(0)
    @Column(name = "created_on")
    private Date createdOn;
    @Column(name = "command")
    private String command;
    @Column(name = "processed")
    private Boolean processed;
    @Column(name = "failed")
    private Boolean failed;
    @Column(name = "failure_message")
    private String failureMessage;

    public CommandSource() {
        super();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    public Boolean getFailed() {
        return failed;
    }

    public void setFailed(Boolean failed) {
        this.failed = failed;
    }

    public String getFailureMessage() {
        return failureMessage;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommandSource that = (CommandSource) o;

        if (!source.equals(that.source)) return false;
        if (!bucket.equals(that.bucket)) return false;
        return createdOn.equals(that.createdOn);
    }

    @Override
    public int hashCode() {
        int result = source.hashCode();
        result = 31 * result + bucket.hashCode();
        result = 31 * result + createdOn.hashCode();
        return result;
    }
}