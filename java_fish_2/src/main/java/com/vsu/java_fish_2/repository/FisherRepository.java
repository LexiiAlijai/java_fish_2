package com.vsu.java_fish_2.repository;

import com.vsu.java_fish_2.Entity.Fisher;
import com.vsu.java_fish_2.request.CreateFisherRequest;
import com.vsu.java_fish_2.utils.PasswordHasher;
import jakarta.validation.Valid;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class FisherRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public FisherRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Optional<Fisher> findByLogin(String login) {
        return namedParameterJdbcTemplate.query(
                "SELECT id, login, password, salt FROM fisher WHERE login = :login",
                Map.of("login", login),
                rs -> {
                    if (rs.next()) {
                        return Optional.of(
                                new Fisher(rs.getLong("id"),
                                        rs.getString("login"),
                                        rs.getString("password"),
                                        rs.getString("salt")));
                    }
                    return Optional.empty();
                });
    }

    public int create(@Valid CreateFisherRequest createFisherRequest) {
        String salt = PasswordHasher.generateSalt();
        return namedParameterJdbcTemplate.update("INSERT INTO fisher(login,password,salt) Values(:login,:password,:salt)",
        Map.of(
                "login",createFisherRequest.getFisherName(),
                "password",PasswordHasher.hashPassword( createFisherRequest.getPassword(),salt),
                "salt", salt
        ));
    }

    public Optional<Fisher> findByID(long fisherId) {
        return namedParameterJdbcTemplate.query(
                "select id, login, password, salt from fisher where id = :id",
                Map.of("id", fisherId),
                rs -> {
                    if (rs.next()) {
                        return Optional.of( new Fisher(
                                rs.getLong("id"),
                                rs.getString("login"),
                                rs.getString("password"),
                                rs.getString("salt")));
                    }
                    return Optional.empty();
                });
    }
}
