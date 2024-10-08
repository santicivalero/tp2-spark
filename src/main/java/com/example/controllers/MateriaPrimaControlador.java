package com.example.controllers;

import com.example.DAO.MateriaPrimaDAO;
import com.example.models.MateriaPrima;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class MateriaPrimaControlador {
    private MateriaPrimaDAO dao;
    private Gson gson = new Gson();

    public MateriaPrimaControlador() {
        this.dao = new MateriaPrimaDAO();
    }

    public String agregarMateriaPrima(Request req, Response res) {
        try {
            MateriaPrima materiaPrima = gson.fromJson(req.body(), MateriaPrima.class);
            if (materiaPrima.getNombre() == null || materiaPrima.getCantidad() == null) { // "unidad" puede ser nulo
                res.status(400);
                return gson.toJson("Faltan datos requeridos (nombre, cantidad, unidad)");
            }

            // Validación si el nombre ya existe
            MateriaPrima existente = dao.obtenerMateriaPrimaPorNombre(materiaPrima.getNombre());
            if (existente != null) {
                res.status(409); // Código 409: Conflicto
                return gson.toJson("El nombre de la materia prima ya existe");
            }

            dao.agregarMateriaPrima(materiaPrima);
            res.status(201);
            return gson.toJson("Materia prima agregada");
        } catch (Exception e) {
            res.status(500);
            return gson.toJson("Error al agregar la materia prima: " + e.getMessage());
        }
    }

    // Obtener Materia Prima por ID
    public String obtenerMateriaPrimaPorId(Request req, Response res) {
        try {
            int id = validarYObtenerId(req, res); // Validar ID
            MateriaPrima materiaPrima = validarExistenciaMateriaPrima(id, res); // Validar existencia
            return gson.toJson(materiaPrima); // Devolver el json si existe
        } catch (NumberFormatException e) {
            return gson.toJson("ID inválido, debe ser un número");
        } catch (IllegalArgumentException e) {
            return gson.toJson(e.getMessage()); // "Materia prima no encontrada"
        } catch (Exception e) {
            res.status(500);
            return gson.toJson("Error al obtener la materia prima: " + e.getMessage());
        }
    }

    // Obtener todas las Materias Primas
    public String obtenerTodasLasMateriasPrimas(Request req, Response res) {
        try {
            return gson.toJson(dao.obtenerTodas());
        } catch (Exception e) {
            res.status(500);
            return gson.toJson("Error al obtener todas las materias primas: " + e.getMessage());
        }
    }

    // Actualizar Materia Prima
    public String actualizarMateriaPrima(Request req, Response res) {
        try {
            int id = validarYObtenerId(req, res); // Validar ID
            MateriaPrima materiaPrimaExistente = validarExistenciaMateriaPrima(id, res); // Validar existencia

            MateriaPrima materiaPrima = gson.fromJson(req.body(), MateriaPrima.class);

            // Validación si el nombre ya existe (y no es el mismo ID)
            if (materiaPrima.getNombre() != null) {
                MateriaPrima existente = dao.obtenerMateriaPrimaPorNombre(materiaPrima.getNombre());
                if (existente != null && existente.getId() != id) {
                    res.status(409); // Código 409: Conflicto
                    return gson.toJson("El nombre de la materia prima ya existe");
                }
                materiaPrimaExistente.setNombre(materiaPrima.getNombre());
            }

            if (materiaPrima.getCantidad() != null) {
                materiaPrimaExistente.setCantidad(materiaPrima.getCantidad());
            }
            if (materiaPrima.getUnidad() != null) {
                materiaPrimaExistente.setUnidad(materiaPrima.getUnidad());
            }

            dao.actualizarMateriaPrima(materiaPrimaExistente);
            return gson.toJson("Materia prima actualizada: " + gson.toJson(materiaPrimaExistente)); // Devolver el json
                                                                                                    // actualizado

        } catch (NumberFormatException e) {
            return gson.toJson("ID inválido, debe ser un número");
        } catch (IllegalArgumentException e) {
            return gson.toJson(e.getMessage()); // "Materia prima no encontrada"
        } catch (Exception e) {
            res.status(500);
            return gson.toJson("Error al actualizar la materia prima: " + e.getMessage());
        }
    }

    // Eliminar Materia Prima
    public String eliminarMateriaPrima(Request req, Response res) {
        try {
            int id = validarYObtenerId(req, res); // Validar ID
            validarExistenciaMateriaPrima(id, res); // Validar existencia

            dao.eliminarMateriaPrima(id);
            return gson.toJson("Materia prima eliminada con éxito"); // Mensaje de eliminación
        } catch (NumberFormatException e) {
            return gson.toJson("ID inválido, debe ser un número");
        } catch (IllegalArgumentException e) {
            return gson.toJson(e.getMessage()); // "Materia prima no encontrada"
        } catch (Exception e) {
            res.status(500);
            return gson.toJson("Error al eliminar la materia prima: " + e.getMessage());
        }
    }

    // Validaciones
    private int validarYObtenerId(Request req, Response res) throws NumberFormatException {
        return Integer.parseInt(req.params(":id"));
    }

    private MateriaPrima validarExistenciaMateriaPrima(int id, Response res) {
        MateriaPrima materiaPrima = dao.obtenerMateriaPrimaPorId(id);
        if (materiaPrima == null) {
            res.status(404);
            throw new IllegalArgumentException("Materia prima no encontrada");
        }
        return materiaPrima;
    }

}
