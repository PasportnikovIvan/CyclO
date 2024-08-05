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

    @Override
    public List<BikeDTO> getAllBikes() {
        return bikeRepository.findAll().stream()
                .map(bikeMapper::bikeToBikeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BikeDTO getBikeById(Long id) {
        Bike bike = bikeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bike not found with id " + id));
        return bikeMapper.bikeToBikeDTO(bike);
    }

    @Override
    public BikeDTO createBike(BikeDTO bikeDTO) {
        Bike bike = bikeMapper.bikeDTOToBike(bikeDTO);
        Bike createdBike = bikeRepository.save(bike);
        return bikeMapper.bikeToBikeDTO(createdBike);
    }

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

    @Override
    public void deleteBike(Long id) {
        Bike bike = bikeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bike not found with id " + id));
        bikeRepository.delete(bike);
    }
}
