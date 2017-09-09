package com.proyectoFinal.OMSA.Repository;

import com.proyectoFinal.OMSA.Entities.Rol;
import com.proyectoFinal.OMSA.Entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Dany on 07/09/2017.
 */
@Repository
public interface RolRepository  extends CrudRepository<Rol, Long> {

    List<Rol> findAll();

    List<Rol> findAllByUsuario(Usuario usuario);

    void deleteRolById(Long id);
}
