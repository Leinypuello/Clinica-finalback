package dh.backend.clinica_odontologica.service;

import dh.backend.clinica_odontologica.entity.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {

    Odontologo agregarOdontologo(Odontologo odontologo);

    Optional<Odontologo> buscarUnOdontologo(int id);
    List<Odontologo> buscarTodosOdontologos();

    void actualizar(Odontologo odontologo);
    void eliminar(Integer id);

    List<Odontologo> buscarPorApellido(String apellido);
    List<Odontologo> buscarPorNombre(String nombre);
}
