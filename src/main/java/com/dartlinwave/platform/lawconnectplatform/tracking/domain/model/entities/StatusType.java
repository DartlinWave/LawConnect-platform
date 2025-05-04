package com.dartlinwave.platform.lawconnectplatform.tracking.domain.model.entities;

import com.dartlinwave.platform.lawconnectplatform.tracking.domain.model.valueobjects.StatusTypes;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor
public class StatusType {
    @Id
    @GeneratedValue
    private int id;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private StatusTypes type;

    public StatusType(StatusTypes type) {
        this.type = type;
    }
}
