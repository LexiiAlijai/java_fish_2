package com.vsu.java_fish_2.repository;

import com.vsu.java_fish_2.Entity.Catch;
import jakarta.validation.Valid;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CatchRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CatchRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Long create(@Valid Catch aCatch) {
        return namedParameterJdbcTemplate.queryForObject(
                "INSERT INTO catch(fish_name, weight, length, fisher_id) Values(:fish_name,:weight,:length,:fisher_id) RETURNING id ",
                Map.of(
                        "fish_name", aCatch.getFishName(),
                        "weight", aCatch.getWeight(),
                        "length", aCatch.getLength(),
                        "fisher_id", aCatch.getFisherId()
                ),
                Long.class
        );
    }

    public int update(@Valid Catch aCatch) {
        return namedParameterJdbcTemplate.update(
                "UPDATE catch SET fish_name = :fish_name, weight = :weight, length = :length WHERE id = :id",
                Map.of(
                        "fish_name", aCatch.getFishName(),
                        "weight", aCatch.getWeight(),
                        "length", aCatch.getLength(),
                        "id", aCatch.getId()

                )
        );
    }

    public Optional<Catch> findById(Long id) {
        return Optional.ofNullable(namedParameterJdbcTemplate.query(
                "SELECT id, fish_name, weight, length, fisher_id FROM catch WHERE id = :id",
                Map.of("id",id),
                rs -> {
                    if (rs.next()) {
                        return new Catch(
                                rs.getLong("id"),
                                rs.getString("fish_name"),
                                rs.getFloat("weight"),
                                rs.getFloat("length"),
                                rs.getLong("fisher_id")
                        );
                    }
                    return null;
                }
        ));
    }

    public Optional<List<Catch>> findByFishName(Long profileId, String fishName) {

        List<Catch> catches = namedParameterJdbcTemplate.query(
                "SELECT id, fish_name, weight, length, fisher_id FROM catch " +
                        "WHERE profile_id = :profileId AND site_address LIKE :fish_name",
                Map.of(
                        "profileId", profileId,
                        "fish_name", "%" + fishName + "%"
                ),
                (rs, rowNum) -> new Catch(
                        rs.getLong("id"),
                        rs.getString("fish_name"),
                        rs.getFloat("weight"),
                        rs.getFloat("length"),
                        rs.getLong("fisher_id")
                )
        );

        return catches.isEmpty() ? Optional.empty() : Optional.of(catches);
    }

    public int deleteById(Long id) {
        return namedParameterJdbcTemplate.update(
                "DELETE FROM catch WHERE id = :id",
                Map.of("id", id)
        );
    }
}
