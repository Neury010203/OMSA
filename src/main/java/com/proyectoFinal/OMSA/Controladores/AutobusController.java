package com.proyectoFinal.OMSA.Controladores;

import com.proyectoFinal.OMSA.Entities.*;
import com.proyectoFinal.OMSA.Repository.*;
import com.proyectoFinal.OMSA.Services.AutobusServices;
import com.proyectoFinal.OMSA.Services.ChequeoServices;
import com.proyectoFinal.OMSA.Services.RutaServices;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by anyderre on 04/07/17.
 */
@Controller
//@EnableWebMvc
@RequestMapping("/autobus")
public class AutobusController {
    @Autowired
    private AutobusServices autobusServices;
    @Autowired
    ChequeoServices chequeoServices;

    @Autowired
    RutaServices rutaServices;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("rutas", rutaServices.buscarTodasLasRutas());
        return "ver_autobus";
    }

    /**
     *
     * Modificar un autobus
     * @param id
     * @return
     */
    @RequestMapping("/editar/{id}")
    public String editarAutobus(Model model, @PathVariable("id")Long id){
        Autobus autobus =autobusServices.buscarUnAutobus(id);
        model.addAttribute("rutas", rutaServices.buscarTodasLasRutas());
        model.addAttribute("autobus", autobus);
        return "editar_autobus";
    }

    @PostMapping("/editar")
    public String guardarAutobusEditado( Model model,@RequestParam("matricula")String matricula, @RequestParam("ruta")Long id_ruta, @RequestParam(value="modelo", required= false)String modelo, @RequestParam(value="cantidadDeAsientos")Integer cantidadAsientos,
                                         @RequestParam(value = "peso", required = false)Float peso, @RequestParam(value = "anoFabricacion", required = false)Integer anoFabircacion, @RequestParam(value="conductor", required = false)String conductor,
                                         @RequestParam(value = "precio")Integer precio, @RequestParam("autobus")Long id_autobus, @RequestParam(value = "raspberryPiNumeroSerial")String numeroSerial,
                                         @RequestParam("tieneAireAcondicionado")Boolean tieneAire){
        boolean modificado= false;
        Autobus autobus = autobusServices.buscarAutobusPorId(id_autobus);
        autobus.setModelo(modelo);
        autobus.setRuta(rutaServices.buscarRutaPorId(id_ruta));
        autobus.setCantidadDeAsientos(cantidadAsientos);
        autobus.setPeso(peso);
        autobus.setMatricula(matricula);
        autobus.setAnoFabricacion(anoFabircacion);
        autobus.setConductor(conductor);
        autobus.setPrecio(precio);
        autobus.setRaspberryPiNumeroSerial(numeroSerial);
        autobus.setTieneAireAcondicionado(tieneAire);
        System.out.println("autobus = [" + autobus.getPrecio() + "], autobus = [" + autobus.getConductor() + "],autobus = [" + autobus.getActivo() +
                "],autobus = [" + autobus.getModelo() + "],autobus = [" + autobus.getRaspberryPiNumeroSerial() + "],autobus = [" + autobus.getAnoFabricacion() + "]," +
                "autobus = [" + autobus.getFechaCreada() + "]," +
                "autobus = [" + autobus.getCantidadDeAsientos() + "],autobus = [" + autobus.getCantidadDePasajerosActual() + "],autobus = [" + autobus.getTieneAireAcondicionado() + "], autobus = [" + autobus.getAnoFabricacion() + "], id = [" + id_autobus + "]");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=");


        if(autobusServices.guardarAutobus(autobus)!=null){
            modificado=true;
        }
        if (modificado){
             return "redirect:/autobus/";
        }else {
            //Autobus  autobus1 =autobusServices.buscarUnAutobus(id);
            model.addAttribute("rutas", rutaServices.buscarTodasLasRutas());
            model.addAttribute("message", "error");
            model.addAttribute(autobus);
            return "editar_autobus";
        }
    }

    @RequestMapping("/crear")
    public String crearAutobus(Model model){
        model.addAttribute("autobus", new Autobus());
        model.addAttribute("rutas", rutaServices.buscarTodasLasRutas());

        return "crear_autobus";
    }
    private Long getLongFromDate(Date date){
        return date.getTime()/1000;
    }

    @PostMapping("/crear")
    @Transactional
    public String guardarAutobusCreado(@ModelAttribute Autobus autobus, RedirectAttributes redirectAttributes, Model model, @RequestParam("ruta")Long id){
        Ruta ruta = rutaServices.buscarRutaPorId(id);
        autobus.setRuta(ruta);
        autobus.setActivo(false);
        autobus.setFechaCreada(getLongFromDate(new Date()));
        autobus.setUltimaFechaModificada(getLongFromDate(new Date()));
        autobus.setCantidadDePasajerosActual(0);
        System.out.println("autobus = [" + autobus.getPrecio() + "], autobus = [" + autobus.getConductor() + "],autobus = [" + autobus.getActivo() +
                "],autobus = [" + autobus.getModelo() + "],autobus = [" + autobus.getRaspberryPiNumeroSerial() + "],autobus = [" + autobus.getAnoFabricacion() + "]," +
                "autobus = [" + autobus.getFechaCreada() + "]," +
                "autobus = [" + autobus.getCantidadDeAsientos() + "],autobus = [" + autobus.getCantidadDePasajerosActual() + "],autobus = [" + autobus.getTieneAireAcondicionado() + "],id = [" + id + "]");
        boolean guardado= false;
        if(autobusServices.guardarAutobus(autobus)!=null){
            guardado= true;
        }
        if(guardado){
            return "redirect:/autobus/";
        }
        model.addAttribute("autobus", new Autobus());
        model.addAttribute("error", "no se pudo guardar el autobus");
        //redirectAttributes.addFlashAttribute("error", "No se pudo guardar el autobus");
        return "crear_autobus";
    }
    /**
     *
     * Eliminar un autobus
     * @param id
     * @return
     */
    @RequestMapping(value = "/eliminar/{id}")
    public String eliminarAutobus(@PathVariable("id") Long id){
        List<Chequeo> chequeos = chequeoServices.buscarChequeoPorAutobusId(id);
//        for (Chequeo chequeo : chequeos){
//            chequeoServices.elimin
//        }
        autobusServices.eliminarAutobusporId(id);
            return "redirect:/autobus/";

    }
}
