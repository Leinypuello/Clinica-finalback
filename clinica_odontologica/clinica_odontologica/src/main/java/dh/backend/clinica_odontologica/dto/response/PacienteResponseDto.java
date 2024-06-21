package dh.backend.clinica_odontologica.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteResponseDto {

    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;



}
