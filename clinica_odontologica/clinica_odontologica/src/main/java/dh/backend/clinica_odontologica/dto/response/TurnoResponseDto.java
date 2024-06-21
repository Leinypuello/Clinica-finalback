package dh.backend.clinica_odontologica.dto.response;


import dh.backend.clinica_odontologica.entity.Odontologo;
import dh.backend.clinica_odontologica.entity.Paciente;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TurnoResponseDto {


    private Integer id;
    private OdontologoResponseDto odontologo;
    private PacienteResponseDto paciente;
    private String fecha;


}
