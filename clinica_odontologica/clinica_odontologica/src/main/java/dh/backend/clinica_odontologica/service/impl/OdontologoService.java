package dh.backend.clinica_odontologica.service.impl;


import dh.backend.clinica_odontologica.entity.Odontologo;
import dh.backend.clinica_odontologica.repository.IOdontologoRepository;
import dh.backend.clinica_odontologica.service.IOdontologoService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
public class OdontologoService implements IOdontologoService {

    private IOdontologoRepository odontologoRepository;

    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo agregarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologo> buscarUnOdontologo(int id) {
        return odontologoRepository.findById(id);
    }

    @Override
    public List<Odontologo> buscarTodosOdontologos() {
        return odontologoRepository.findAll();
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        odontologoRepository.save(odontologo);
    }

    @Override
    public void eliminar(Integer id) {
        odontologoRepository.deleteById(id);
    }

    @Override
    public List<Odontologo> buscarPorApellido(String apellido) {
        return odontologoRepository.buscarPorApellido(apellido);
    }

    @Override
    public List<Odontologo> buscarPorNombre(String nombre) {
        return odontologoRepository.findByNombreLike(nombre);
    }


}
