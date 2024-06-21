package dh.backend.clinica_odontologica.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TurnosRequestDto {

    private Integer pacienteId;
    private Integer odontologoId;
    private String fecha;
}
