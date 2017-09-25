package com.proyectoFinal.OMSA.Services;

import com.proyectoFinal.OMSA.Entities.*;
import com.proyectoFinal.OMSA.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anyderre on 01/07/17.
 */
@Service
public class RutaServices {
    @Autowired
    RutaRepository rutaRepository;

    public List<Ruta> buscarRutaPorNombreCorredor(String nombre){
        return rutaRepository.findAllByNombreCorredor(nombre);
    }

    public List<Ruta> buscarRutasPorPagina(int page, int itemPerPage){
        Pageable pageable = new PageRequest(page, itemPerPage);
        return rutaRepository.findAll(pageable);
    }

    public List<Ruta>buscarTodasLasRutas(){
        return rutaRepository.findAll();
    }

    public Ruta buscarRutaPorId(Long id){
        return rutaRepository.findRutaById(id);
    }

    public Ruta guardarRuta(Ruta ruta){
        return rutaRepository.save(ruta);
    }

    @Transactional
    public void eliminarRutaPorId(Long id){
         rutaRepository.deleteById(id);
    }

    @Transactional
    public void modificarRutaPorId(Ruta ruta){
         rutaRepository.modifyRutaById(ruta.getDistanciaTotal(), ruta.getEsDireccionSubida(),ruta.getFechaUltimaModificacion(),ruta.getNombreCorredor(),ruta.getId());
    }

    public void guardarRutaAlPrincipio(){
        Ruta ruta = new Ruta();
        ruta.setDistanciaTotal((float) 43233);
        ruta.setFechaCreada((long) 1500573374);
        ruta.setFechaUltimaModificacion((long) 1500573374);
        ruta.setCiudad("Santiago de Los Caballeros");
        ruta.setNombreCorredor("C-3(Baldom)");
        ruta.setEsDireccionSubida(true);

        ArrayList<Coordenada> coordenadas = new ArrayList<>();
//        coordenadas.add(new Coordenada(19.))


    }
}
