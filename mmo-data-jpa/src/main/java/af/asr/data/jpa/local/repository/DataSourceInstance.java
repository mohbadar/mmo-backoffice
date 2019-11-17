package af.asr.data.jpa.local.repository;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data_source_instances")
@Data
@ToString
public class DataSourceInstance {

    @Id
    @Column(name = "identifier")
    private String identifier;
    @Column(name = "driver_class")
    private String driverClass;
    @Column(name = "jdbc_url")
    private String jdbcUrl;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    public DataSourceInstance() {
        super();
    }

}
