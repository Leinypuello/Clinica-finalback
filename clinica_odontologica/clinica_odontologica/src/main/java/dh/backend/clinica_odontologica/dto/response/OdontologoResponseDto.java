package dh.backend.clinica_odontologica.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dh.backend.clinica_odontologica.entity.Turno;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OdontologoResponseDto {


    private Integer id;
    private String numeroMatricula;
    private String nombre;
    private String apellido;



}
