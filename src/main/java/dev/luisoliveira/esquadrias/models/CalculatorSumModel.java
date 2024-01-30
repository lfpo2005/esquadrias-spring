package dev.luisoliveira.esquadrias.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CalculatorSumModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal totalDepreciation;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal totalFixedCosts;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal totalVariableCosts;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal totalEmployeeCosts;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Integer totalTaxes;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Integer totalProfit;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Integer totalCommission;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Integer totalSumTaxesCommissionProfit;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal totalSumServices;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal totalMonthly;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal valueWorkDay;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal valueWorkHour;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    protected UserModel user;

    public void setUser(UUID userId) {
        this.user = new UserModel();
        this.user.setUserId(userId);
    }

}
