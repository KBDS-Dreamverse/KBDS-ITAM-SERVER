package com.kbds.itamserveradmin.department.domain;

import com.kbds.itamserveradmin.cooperation.domain.Cooperation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    @Id
    private String deptId;
    private String deptName;

    @ManyToOne
    @JoinColumn(name = "corp_id")
    private Cooperation cooperation;
}
