package com.vsu.java_fish_2.mappers;

import com.vsu.java_fish_2.Entity.Fisher;
import com.vsu.java_fish_2.dto.FisherDTO;
import org.springframework.stereotype.Component;

@Component
public class FisherMapper {
    public FisherDTO toFisherDTO(Fisher fisher) {
        return new FisherDTO(fisher.getId(), fisher.getLogin());
    }
}
