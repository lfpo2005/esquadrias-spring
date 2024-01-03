package dev.luisoliveira.esquadrias.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import dev.luisoliveira.esquadrias.enums.AddressType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_ADDRESS")
public class AddressModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID addressId;
    @Column(length = 60)
    private String address;
    @Column(length = 6)
    private String number;
    @Column(length = 50)
    private String city;
    @Column(length = 2)
    private String state;
    @Column(length = 10)
    private String zipCode;
    @Column(length = 50)
    private String complement;
    @Column(length = 20)
    private String neighborhood;
    @Size(max = 500)
    private String description;
    @Column(nullable = false)
    private boolean active = true;
    @Column(nullable = false)
    private boolean isDeleted = false;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AddressType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel user;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private CompanyModel company;
}
