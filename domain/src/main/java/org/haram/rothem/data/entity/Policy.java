package org.haram.rothem.data.entity;

import com.space.data.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Entity
@DynamicInsert
@Table(name = "rt_policy")
@NoArgsConstructor
@Setter
public class Policy extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policy_seq")
    private Long policySeq;

    private String title;

    private String content;

    @Column(name = "is_required")
    private Boolean isRequired;

    private Boolean status;

}
