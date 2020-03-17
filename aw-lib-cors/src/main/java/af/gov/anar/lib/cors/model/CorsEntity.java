package af.gov.anar.lib.cors.model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The CorsEntity class with required fields to be captured and recorded
 */
@Entity()
@Table(name = "cors")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Getter
@Setter
@Builder
public class CorsEntity {

    /**
     * Field for immutable universally unique identifier (UUID)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long uuid ;


    @Column(name = "allowed_origin", unique = true)
    private String allowedOrigin;


    @Column(name = "allowed_method", unique = true)
    private String allowedMethod;


    @Column(name = "allowed_header", unique = true)
    private String allowedHeader;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;


}
