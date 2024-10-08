package com.example.config;

import org.sql2o.Sql2o;

public class DatabaseConnection {
    private static Sql2o sql2o;

    private DatabaseConnection() {
    }

    public static Sql2o getInstance() {
        if (sql2o == null) {
            sql2o = new Sql2o("jdbc:mysql://localhost:3306/materia-prima", "root", "");
        }
        return sql2o;
    }
}

// bd: materia-prima
// tabla: materia-prima
// columnas: id (int(3))
// ----------nombre (varchar(50))
// ----------cantidad (float)
// ----------unidad (varchar(15))
// PK: id
// UNIQUE: nombre
// AUTOINCREMENT: id
// NOT NULL: nombre, cantidad
// NULL: unidad
