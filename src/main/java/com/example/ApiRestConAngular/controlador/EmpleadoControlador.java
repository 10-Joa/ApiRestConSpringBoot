package com.example.ApiRestConAngular.controlador;

import com.example.ApiRestConAngular.EmpleadoExcepciones.ResourceNotFoundException;
import com.example.ApiRestConAngular.modelo.Empleado;
import com.example.ApiRestConAngular.repositorio.empleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmpleadoControlador {
    @Autowired
    private empleadoRepository empleadoRepository;

    @GetMapping("/empleados")
    public List<Empleado> listarTodosEmpleados(){  //devuelv todos los empleados
        return empleadoRepository.findAll();
    }

    //guardar empleados
    @PostMapping("/empleados")
    public Empleado guardarEmpleados(@RequestBody Empleado empleado){
        return empleadoRepository.save(empleado);
    }

    //para buscar un empleado
    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoId(@PathVariable Long id) { //responentiti para retornar q hubo un error
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con ID: "+id));
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado nuevoEmpleado) { //responentiti para retornar q hubo un error

        //primero busca el empleado
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con ID: "+id));

        //luego lo gaurdamos en la clase de empleado
        empleado.setNombre(nuevoEmpleado.getNombre());
        empleado.setApellido(nuevoEmpleado.getApellido());
        empleado.setEmail(nuevoEmpleado.getEmail());

        //aqui guardamos el empleado con el save
        Empleado empleadoActualizado = empleadoRepository.save(empleado);

        //retornamos el empelado guardado
        return ResponseEntity.ok(empleadoActualizado);
    }


    //este metodo sirve para eliminar un empleado
    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarEmpleado(@PathVariable Long id){
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el ID : " + id));

        empleadoRepository.delete(empleado);

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar",Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }


 /*   //eliminarEmpleado
    @DeleteMapping("/empleados/{id}")
    public String eliminarPorId(@PathVariable Long id){
        Boolean respuesta;
        try {
            empleadoRepository.deleteById(id);
             respuesta = true;
        }catch (Exception e){
            respuesta = false;
        }

        if (respuesta){
            return "Se elimino el usuario con el id :" + id;
        }else{
            return "Ocurrio un erro al eliminarse el usuario con el id :"+id;
        }

    }
    */

}
