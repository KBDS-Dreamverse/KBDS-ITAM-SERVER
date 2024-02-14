package com.kbds.itamserveradmin.domain.contract.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeriodType {
    @Id
    private Long periodId;

    @OneToOne
    @JoinColumn(name = "cont_id")
    private Contract cont;

    private LocalDateTime contStartDate;

    private LocalDateTime contEndDate;
}