package pl.warehouseapi.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class PeriodOfTime {
    private LocalDateTime from;
    private LocalDateTime to;
}
