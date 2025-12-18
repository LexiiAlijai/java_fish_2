package com.vsu.java_fish_2.controller;

import com.vsu.java_fish_2.dto.CatchDTO;
import com.vsu.java_fish_2.dto.FisherDTO;
import com.vsu.java_fish_2.request.CreateCatchRequest;
import com.vsu.java_fish_2.request.CreateFisherRequest;
import com.vsu.java_fish_2.request.UpdateCatchRequest;
import com.vsu.java_fish_2.service.FisherService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final FisherService fisherService;

    public ProfileController(FisherService fisherService) {
        this.fisherService = fisherService;
    }

    @GetMapping("/by-fishername")
    public FisherDTO getById(@RequestParam String name) {
        return fisherService.findByLogin(name);
    }

    @PostMapping
    public FisherDTO create(@Valid @RequestBody CreateFisherRequest createProfileRequest) {
        return fisherService.createFisher(createProfileRequest);
    }
    @PostMapping("/{fisherId}/catch")
    public CatchDTO create(@PathVariable long fisherId, @Valid @RequestBody CreateCatchRequest createRecordRequest) {
        return fisherService.createCatch(createRecordRequest,fisherId);

    }
    @PutMapping("/{fisherId}/catch/edit")
    public CatchDTO update(@PathVariable long fisherId, @Valid @RequestBody UpdateCatchRequest updateRecordRequest) {
        return fisherService.updateCatch(updateRecordRequest,fisherId);
    }
    @GetMapping("/{fisherId}/catch/{id}")
    public CatchDTO getCatch(@PathVariable long fisherId, @PathVariable Long id) {
        return fisherService.findCatchById(id,fisherId);
    }
    @GetMapping("/{fisherId}/catch/fishName/{fishName}")
    public List<CatchDTO> getCatchByFishName(@PathVariable long fisherId, @PathVariable String fishName) {
        return fisherService.findCatchByFishName(fisherId,fishName);
    }
    @DeleteMapping("/{fisherId}/catch/delete/{id}")
    public void deleteCatch(@PathVariable long fisherId, @PathVariable Long id) {
        fisherService.deleteCatchById(id,fisherId);
    }
}
