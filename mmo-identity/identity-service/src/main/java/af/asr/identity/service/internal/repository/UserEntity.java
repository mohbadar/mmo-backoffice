package af.asr.identity.service.internal.repository;


import com.datastax.driver.core.LocalDate;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import java.nio.ByteBuffer;

@Table(name = Users.TABLE_NAME)
public class UserEntity {
    @PartitionKey
    @Column(name = Users.IDENTIFIER_COLUMN)
    private String identifier;
    @Column(name = Users.ROLE_COLUMN)
    private String role;
    @Column(name = Users.PASSWORD_COLUMN)
    private ByteBuffer password;
    @Column(name = Users.SALT_COLUMN)
    private ByteBuffer salt;
    @Column(name = Users.ITERATION_COUNT_COLUMN)
    private int iterationCount;
    @Column(name = Users.PASSWORD_EXPIRES_ON_COLUMN)
    private LocalDate passwordExpiresOn;

    public UserEntity() {}

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ByteBuffer getPassword() {
        return password;
    }

    public void setPassword(ByteBuffer password) {
        this.password = password;
    }

    public ByteBuffer getSalt() {
        return salt;
    }

    public void setSalt(ByteBuffer salt) {
        this.salt = salt;
    }

    public int getIterationCount() {
        return iterationCount;
    }

    public void setIterationCount(int iterationCount) {
        this.iterationCount = iterationCount;
    }

    public LocalDate getPasswordExpiresOn() {
        return passwordExpiresOn;
    }

    public void setPasswordExpiresOn(LocalDate passwordExpiresOn) {
        this.passwordExpiresOn = passwordExpiresOn;
    }
}
© 2019 GitHub, Inc.