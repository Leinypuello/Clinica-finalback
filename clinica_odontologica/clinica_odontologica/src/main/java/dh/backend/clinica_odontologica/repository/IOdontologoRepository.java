package dh.backend.clinica_odontologica.repository;

import dh.backend.clinica_odontologica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo,Integer> {


    @Query("Select o from Odontologo o where LOWER(o.apellido) = LOWER(:apellido)")
    List<Odontologo> buscarPorApellido(String apellido);

    @Query("Select o from Odontologo o where LOWER(o.nombre) LIKE LOWER(CONCAT('%',:nombre,'%'))")
    List<Odontologo> findByNombreLike(@Param("nombre") String nombre);


}
