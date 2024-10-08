package com.example.DAO;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.example.config.DatabaseConnection;
import com.example.models.MateriaPrima;

import java.util.List;

public class MateriaPrimaDAO {
    private Sql2o sql2o;

    public MateriaPrimaDAO() {
        this.sql2o = DatabaseConnection.getInstance();
    }

    public void agregarMateriaPrima(MateriaPrima materiaPrima) {
        String sql = "INSERT INTO `materia-prima` (nombre, cantidad, unidad) VALUES (:nombre, :cantidad, :unidad)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql, true)
                    .bind(materiaPrima) // Se usa bind para enlazar los parámetros automáticamente
                    // .addParameter("nombre", materiaPrima.getNombre())
                    // .addParameter("cantidad", materiaPrima.getCantidad())
                    // .addParameter("unidad", materiaPrima.getUnidad())
                    .executeUpdate();
        }
    }

    public MateriaPrima obtenerMateriaPrimaPorId(int id) {
        String sql = "SELECT * FROM `materia-prima` WHERE id = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(MateriaPrima.class);
        }
    }

    public MateriaPrima obtenerMateriaPrimaPorNombre(String nombre) {
        String sql = "SELECT * FROM `materia-prima` WHERE nombre = :nombre";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("nombre", nombre)
                    .executeAndFetchFirst(MateriaPrima.class);
        }
    }

    public List<MateriaPrima> obtenerTodas() {
        String sql = "SELECT * FROM `materia-prima`";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(MateriaPrima.class);
        }
    }

    public void actualizarMateriaPrima(MateriaPrima materiaPrima) {
        String sql = "UPDATE `materia-prima` SET nombre = :nombre, cantidad = :cantidad, unidad = :unidad WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .bind(materiaPrima) // Se usa bind para enlazar los parámetros automáticamente
                    // .addParameter("nombre", materiaPrima.getNombre())
                    // .addParameter("cantidad", materiaPrima.getCantidad())
                    // .addParameter("unidad", materiaPrima.getUnidad())
                    // .addParameter("id", materiaPrima.getId())
                    .executeUpdate();
        }
    }

    public void eliminarMateriaPrima(int id) {
        String sql = "DELETE FROM `materia-prima` WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}
