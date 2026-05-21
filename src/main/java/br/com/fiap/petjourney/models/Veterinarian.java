package br.com.fiap.petjourney.models;

import br.com.fiap.petjourney.dtos.request.VeterinarianRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_VETERINARIO")
public class Veterinarian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String crmv;

    private String phone;

    private String email;

    private String specialty;

    @ManyToOne
    private Clinic clinic;

    public Veterinarian(VeterinarianRequest request, Clinic clinic) {
        this.name = request.name();
        this.crmv = request.crmv();
        this.phone = request.phone();
        this.email = request.email();
        this.specialty = request.specialty();
        this.clinic = clinic;
    }

    public void updateFrom(VeterinarianRequest request, Clinic clinic) {
        this.name = request.name();
        this.crmv = request.crmv();
        this.phone = request.phone();
        this.email = request.email();
        this.specialty = request.specialty();
        this.clinic = clinic;
    }
}
