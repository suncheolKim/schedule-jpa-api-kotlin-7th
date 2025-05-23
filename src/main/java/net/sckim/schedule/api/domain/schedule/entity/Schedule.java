package net.sckim.schedule.api.domain.schedule.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.sckim.schedule.api.domain.common.BaseEntity;
import org.apache.logging.log4j.util.Strings;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "schedule")
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    public Schedule(String name, String title, String contents) {
        this.name = name;
        this.title = title;
        this.contents = contents;
    }

    public void edit(String name, String title, String contents) {
        if (Strings.isNotBlank(name)) {
            this.name = name;
        }

        if (Strings.isNotBlank(title)) {
            this.title = title;
        }

        if (Strings.isNotBlank(contents)) {
            this.contents = contents;
        }
    }
}
