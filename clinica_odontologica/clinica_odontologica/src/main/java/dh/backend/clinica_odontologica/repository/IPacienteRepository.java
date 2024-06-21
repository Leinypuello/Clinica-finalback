package dh.backend.clinica_odontologica.repository;

import dh.backend.clinica_odontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente,Integer> {
}
