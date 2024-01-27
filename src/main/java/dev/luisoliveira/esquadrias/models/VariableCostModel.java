package dev.luisoliveira.esquadrias.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_VARIABLE_COSTS")
public class VariableCostModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID variableCostId;
    @NotNull(message = "value is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "value must be greater than 0")
    private BigDecimal valueVariableCosts;
    private String nameCosts;
    @Size(max = 100)
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    public void setUser(UUID userId) {
        this.user = new UserModel();
        this.user.setUserId(userId);
    }
}