package br.com.fiap.petjourney.dtos.response;

import br.com.fiap.petjourney.models.Tutor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TutorResponse extends RepresentationModel<TutorResponse> {
    private Long id;
    private String name;
    private String cpf;
    private String phone;
    private String email;

    public static TutorResponse fromEntity(Tutor tutor) {
        return TutorResponse.builder()
                .id(tutor.getId())
                .name(tutor.getName())
                .cpf(tutor.getCpf())
                .phone(tutor.getPhone())
                .email(tutor.getEmail())
                .build();
    }
}
