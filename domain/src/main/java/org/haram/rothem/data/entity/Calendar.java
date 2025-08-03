package org.haram.rothem.data.entity;

import com.space.data.type.rothem.WeekStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "rt_calendar")
@NoArgsConstructor
@Setter
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_seq")
    private Long calendarSeq;

    @Column(name = "calendar_ymd")
    private String calendarYmd;

    private String day;

    private String year;

    private String month;

    private String date;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @Column(name = "week_status")
    @Enumerated(value = EnumType.STRING)
    private WeekStatus weekStatus;

}
