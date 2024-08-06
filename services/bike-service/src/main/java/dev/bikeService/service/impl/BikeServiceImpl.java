package dev.bikeService.service.impl;

import dev.bikeService.dto.BikeDTO;
import dev.bikeService.entity.Bike;
import dev.bikeService.exception.ResourceNotFoundException;
import dev.bikeService.mapper.BikeMapper;
import dev.bikeService.repository.BikeRepository;
import dev.bikeService.repository.BikeRepositoryElasticsearch;
import dev.bikeService.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
/**
 * Implementation of the BikeService interface. Provides methods for managing bikes.
 */
@Service
public class BikeServiceImpl implements BikeService {

    private final BikeRepositoryElasticsearch bikeRepositoryElasticsearch;
    private final BikeRepository bikeRepository;
    private final BikeMapper bikeMapper;

    @Autowired
    public BikeServiceImpl(BikeRepositoryElasticsearch bikeRepositoryElasticsearch,
                           BikeRepository bikeRepository, BikeMapper bikeMapper) {
        this.bikeRepositoryElasticsearch = bikeRepositoryElasticsearch;
        this.bikeRepository = bikeRepository;
        this.bikeMapper = bikeMapper;
    }
    /**
     * Retrieves all bikes.
     *
     * @return a list of BikeDTOs
     */
    @Override
    public List<BikeDTO> getAllBikes() {
        return bikeRepository.findAll().stream()
                .map(bikeMapper::bikeToBikeDTO)
                .collect(Collectors.toList());
    }
    /**
     * Retrieves a bike by its ID.
     *
     * @param id the ID of the bike
     * @return the BikeDTO
     * @throws ResourceNotFoundException if the bike is not found
     */
    @Override
    public BikeDTO getBikeById(Long id) {
        Bike bike = bikeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bike not found with id " + id));
        return bikeMapper.bikeToBikeDTO(bike);
    }
    /**
     * Creates a new bike.
     *
     * @param bikeDTO the bike details
     * @return the created BikeDTO
     */
    @Override
    public BikeDTO createBike(BikeDTO bikeDTO) {
        Bike bike = bikeMapper.bikeDTOToBike(bikeDTO);
        Bike createdBike = bikeRepository.save(bike);
        return bikeMapper.bikeToBikeDTO(createdBike);
    }
    /**
     * Updates a bike by its ID and provided details.
     *
     * @param id the ID of the bike to update
     */
    @Override
    public BikeDTO updateBike(Long id, BikeDTO bikeDTO) {
        Bike bikeDetails = bikeMapper.bikeDTOToBike(bikeDTO);
        Bike bike = bikeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bike not found with id " + id));
        bike.setModel(bikeDetails.getModel());
        bike.setType(bikeDetails.getType());
        Bike updatedBike = bikeRepository.save(bike);
        return bikeMapper.bikeToBikeDTO(updatedBike);
    }
    /**
     * Deletes a bike by its ID.
     *
     * @param id the ID of the bike to delete
     */
    @Override
    public void deleteBike(Long id) {
        Bike bike = bikeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bike not found with id " + id));
        bikeRepository.delete(bike);
    }
}
