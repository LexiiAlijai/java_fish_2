package com.vsu.java_fish_2.mappers;

import com.vsu.java_fish_2.Entity.Catch;
import com.vsu.java_fish_2.dto.CatchDTO;
import org.springframework.stereotype.Component;

@Component
public class CatchMapper {
    public CatchDTO toCatchDTO(Catch acatch) {
        return new CatchDTO(acatch.getId(),acatch.getFishName(), acatch.getWeight(),acatch.getLength());
    }
}
