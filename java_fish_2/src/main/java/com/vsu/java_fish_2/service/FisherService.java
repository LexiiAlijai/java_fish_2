package com.vsu.java_fish_2.service;


import com.vsu.java_fish_2.Entity.Catch;
import com.vsu.java_fish_2.Entity.Fisher;
import com.vsu.java_fish_2.dto.CatchDTO;
import com.vsu.java_fish_2.dto.FisherDTO;
import com.vsu.java_fish_2.exception.NotFoundException;
import com.vsu.java_fish_2.exception.RepositoryException;
import com.vsu.java_fish_2.exception.ValidationException;
import com.vsu.java_fish_2.mappers.FisherMapper;
import com.vsu.java_fish_2.mappers.CatchMapper;
import com.vsu.java_fish_2.repository.FisherRepository;
import com.vsu.java_fish_2.repository.CatchRepository;
import com.vsu.java_fish_2.request.CreateCatchRequest;
import com.vsu.java_fish_2.request.CreateFisherRequest;
import com.vsu.java_fish_2.request.UpdateCatchRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Transactional
public class FisherService {
    private final static Logger log = Logger.getLogger(FisherService.class.getName());
    private final FisherRepository fisherRepository;
    private final CatchRepository catchRepository;
    private final FisherMapper fisherMapper;
    private final CatchMapper catchMapper;


    public FisherService(FisherRepository fisherRepository, CatchRepository catchRepository, FisherMapper fisherMapper, CatchMapper catchMapper) {
        this.fisherRepository = fisherRepository;
        this.catchRepository = catchRepository;
        this.fisherMapper = fisherMapper;
        this.catchMapper = catchMapper;
    }


    public FisherDTO findByLogin(String login) {
        Fisher fisher = fisherRepository.findByLogin(login).orElseThrow(() -> new NotFoundException("User not found"));
        return fisherMapper.toFisherDTO(fisher);
    }

    public FisherDTO createFisher(@Valid CreateFisherRequest createFisherRequest) {
        if (fisherRepository.findByLogin(createFisherRequest.getFisherName()).isPresent()) {
            throw new ValidationException("User already exists");
        }
        if (fisherRepository.create(createFisherRequest) == 1) {
            return fisherMapper.toFisherDTO(fisherRepository.findByLogin(createFisherRequest.getFisherName()).get());
        }
        throw new RepositoryException("Error creating profile");
    }

    public CatchDTO createCatch(@Valid CreateCatchRequest createCatchRequest, long fisherId) {
        Optional<Fisher> fisher = fisherRepository.findByID(fisherId);
        if (fisher.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        try {
            Fisher f = fisher.get();
            Catch aCatch = new Catch(
                    createCatchRequest.getFishName(),
                    createCatchRequest.getWeight(),
                    createCatchRequest.getLength(),
                    fisherId);
            Long id = catchRepository.create(aCatch);
            if (id != 0) {
                aCatch.setId(id);
                return catchMapper.toCatchDTO(aCatch);
            }
            throw new ValidationException("Wrong data");
        } catch (DataAccessException e) {
            throw new RepositoryException("Error data access");
        }


    }

    public CatchDTO updateCatch(@Valid UpdateCatchRequest updateCatchRequest, long fisherId) {
        Optional<Fisher> fisher = fisherRepository.findByID(fisherId);
        if (fisher.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        try {
            Fisher f = fisher.get();
            Catch aCatch = new Catch(
                    updateCatchRequest.getId(),
                    updateCatchRequest.getFishName(),
                    updateCatchRequest.getWeight(),
                    updateCatchRequest.getLength()
            );
            int result = catchRepository.update(aCatch);
            if (result ==1) {
                return catchMapper.toCatchDTO(aCatch);
            }
            throw new ValidationException("Wrong data");
        } catch (DataAccessException e) {
            throw new RepositoryException("Error data access");
        }


    }

    public CatchDTO findCatchById(long id, long fisherId) {
        Optional<Fisher> fisher = fisherRepository.findByID(fisherId);
        if (fisher.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        try {
            Catch aCatch = catchRepository.findById(id).orElseThrow(() -> new NotFoundException("Catch not found"));
            return catchMapper.toCatchDTO(aCatch);
        } catch (DataAccessException e) {
            throw new RepositoryException("Error data access");
        }


    }

    public List<CatchDTO> findCatchByFishName(long profileId, String fishName) {
        Optional<Fisher> fisher = fisherRepository.findByID(profileId);
        if (fisher.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        try {
            List<Catch> catches = catchRepository.findByFishName(fisher.get().getId(), fishName).orElseThrow(() -> new NotFoundException("Records not found"));
            List<CatchDTO> result = new ArrayList<>();
            for (Catch aCatch : catches) {
                result.add(catchMapper.toCatchDTO(aCatch));
            }
            return result;

        } catch (DataAccessException e) {
            throw new RepositoryException("Error data access");
        }

    }

    public void deleteCatchById(long id, long profileId) {
        Optional<Fisher> fisher = fisherRepository.findByID(profileId);
        if (fisher.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        if (catchRepository.deleteById(id) != 1) {
            throw new NotFoundException("Catch not found");
        }
    }
}
