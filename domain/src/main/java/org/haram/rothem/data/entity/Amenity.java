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
@Table(name = "rt_amenity")
@NoArgsConstructor
@Setter
public class Amenity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amenity_seq")
    private Long amenitySeq;

    private String title;

    @Column(name = "file_path")
    private String filePath;

    private Boolean status;

}
