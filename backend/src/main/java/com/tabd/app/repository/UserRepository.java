package com.tabd.app.repository;

import com.tabd.app.entity.*;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Map;
import java.util.Date;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate template;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public String registerUser(String userEmail, String userPassword, String userName, String userLastName, String userRole, String userAdresse, int userNReg) {
        String sql = "CALL RegisterUser(?, ?, ?, ?, ?, ?, ?)";
        try {
            return template.queryForObject(sql, String.class,
                        userEmail,
                        userPassword,
                        userName, 
                        userLastName, 
                        userRole, 
                        userAdresse, 
                        userNReg);
        } catch (Exception e) {
            return "An error occurred during user registration";
        }
    }
}