package com.example;

import static spark.Spark.*;

import com.example.controllers.MateriaPrimaControlador;

public class MateriaPrimaMain {
    public static void main(String[] args) {
        port(4567); // Configurar el puerto del servidor

        MateriaPrimaControlador controlador = new MateriaPrimaControlador();

        // Rutas RESTful
        post("/materias-primas", controlador::agregarMateriaPrima);
        get("/materias-primas/:id", controlador::obtenerMateriaPrimaPorId);
        get("/materias-primas", controlador::obtenerTodasLasMateriasPrimas);
        put("/materias-primas/:id", controlador::actualizarMateriaPrima);
        delete("/materias-primas/:id", controlador::eliminarMateriaPrima);
    }
}
