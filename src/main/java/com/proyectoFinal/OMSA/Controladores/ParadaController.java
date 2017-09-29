package com.proyectoFinal.OMSA.Controladores;

import com.proyectoFinal.OMSA.Entities.Coordenada;
import com.proyectoFinal.OMSA.Entities.Parada;
import com.proyectoFinal.OMSA.Entities.Ruta;
import com.proyectoFinal.OMSA.Entities.Usuario;
import com.proyectoFinal.OMSA.Services.CoordenadaServices;
import com.proyectoFinal.OMSA.Services.ParadaServices;
import com.proyectoFinal.OMSA.Services.RutaServices;
import com.proyectoFinal.OMSA.Services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import sun.awt.ModalExclude;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by anyderre on 31/07/17.
 */
@Controller
//@EnableWebMvc
@RequestMapping("/parada")
public class ParadaController {
    @Autowired
    ParadaServices paradaServices;
    @Autowired
    RutaServices rutaServices;
    @Autowired
    UsuarioServices usuarioServices;
    @Autowired
    CoordenadaServices coordenadaServices;

    @RequestMapping("/crear/{id}")
    public String crearParada(@PathVariable("id")Long id, HttpServletRequest request, Model model){
        String username = request.getSession().getAttribute("username").toString();
        Usuario user = usuarioServices.buscarUsuarioPorUsername(username);
        model.addAttribute("usuario", user);

        model.addAttribute("id_ruta", id);
        model.addAttribute("parada", new Parada());
        model.addAttribute("paradas", paradaServices.buscarParadaPorRutaId(id));
        model.addAttribute("ruta", rutaServices.buscarRutaPorId(id));
        model.addAttribute("rutas", rutaServices.buscarTodasLasRutas());
        return "crear_parada";
    }

    @Transactional
    @PostMapping("/crear")
    public String guardarParadaCreada(@RequestParam("nombre")String nombre,@RequestParam("latitude")Double latitude,
                                      @RequestParam("longitud")Double longitud, @RequestParam("ruta")Long id_ruta,
                                      HttpServletRequest request, Model model){
        String username = request.getSession().getAttribute("username").toString();
        Usuario user = usuarioServices.buscarUsuarioPorUsername(username);
        model.addAttribute("usuario", user);

       // System.out.println(parada.getId()+"/"+parada.getParadaAnterior()+"/"+parada.getParadaSiguiente()+"/"+parada.getCoordenada().getLongitud()+"/"+parada.getCoordenada().getLatitude()+"================================================================");

        Ruta ruta = rutaServices.buscarRutaPorId(id_ruta);
        Parada parada = new Parada();
        parada.setRuta(ruta);
        parada.setCoordenada(new Coordenada(latitude, longitud));
        parada.setNombre(nombre);
        if(paradaServices.guardarParada(parada)!=null){
            model.addAttribute("message", "success");
        }else {
            model.addAttribute("message", "error");
        }

        model.addAttribute("id_ruta", id_ruta);
        model.addAttribute("parada", new Parada());
        model.addAttribute("paradas", paradaServices.buscarParadaPorRutaId(id_ruta));
        model.addAttribute("ruta", rutaServices.buscarRutaPorId(id_ruta));
        model.addAttribute("rutas", rutaServices.buscarTodasLasRutas());
        return "crear_parada";
    }

    @RequestMapping(value = "/editar/{id_ruta}/{id_parada}", method = RequestMethod.GET)
    public String modificarParada(Model model, @PathVariable("id_parada")Long id_parada, @PathVariable("id_ruta")Long id_ruta,
                                  HttpServletRequest request){
        String username = request.getSession().getAttribute("username").toString();
        Usuario user = usuarioServices.buscarUsuarioPorUsername(username);
        model.addAttribute("usuario", user);
        Parada parada = paradaServices.buscarParada(id_parada);
        model.addAttribute("rutas", rutaServices.buscarTodasLasRutas());
        model.addAttribute("parada", parada);
        model.addAttribute("paradas", paradaServices.buscarParadaPorRutaId(id_ruta));
        return "/editar_parada";
    }

    @PostMapping("/editar")
    public ModelAndView guardarParadaModificada(@RequestParam("nombre")String nombre, @RequestParam("ruta")Long id_ruta, @RequestParam("latitude")Double latitude,
                                                @RequestParam("longitud")Double longitud, @RequestParam(value = "paradaSiguiente", required = false)Long paradaSiguiente,
                                                @RequestParam(value = "paradaAnterior", required = false)Long paradaAnterior, @RequestParam("id_parada")Long id_parada, HttpServletRequest request, Model model){
        String username = request.getSession().getAttribute("username").toString();
        Usuario user = usuarioServices.buscarUsuarioPorUsername(username);
        model.addAttribute("usuario", user);

        Ruta ruta = rutaServices.buscarRutaPorId(id_ruta);
        Parada parada = paradaServices.buscarParada(id_parada);
        parada.setRuta(ruta);
        parada.setNombre(nombre);
        parada.getCoordenada().setLatitude(latitude);
        parada.getCoordenada().setLongitud(longitud);
        parada.setParadaSiguiente(paradaSiguiente);
        parada.setParadaAnterior(paradaAnterior);
           boolean modificado = true;
        if(paradaServices.guardarParada(parada)!=null){
            modificado =true;
        }
        if (modificado){
            return new ModelAndView("redirect:/ruta/listar/paradas/"+id_ruta);

        }
       model.addAttribute("message", "error");
        model.addAttribute("rutas", rutaServices.buscarTodasLasRutas());
        model.addAttribute("parada", parada);
        model.addAttribute("paradas", paradaServices.buscarParadaPorRutaId(id_ruta));
        return  new ModelAndView("/editar_parada");

    }

    @RequestMapping("/eliminar")
    public String eliminarParada(@RequestParam("id")Long id){
        coordenadaServices.eliminarCoordenada(paradaServices.buscarParada(id).getCoordenada().getId());
        paradaServices.eliminarParadaPor(id);
        return "redirect:/parada/";
    }
}
