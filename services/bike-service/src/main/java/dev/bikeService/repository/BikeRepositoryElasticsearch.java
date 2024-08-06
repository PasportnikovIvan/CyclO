package dev.bikeService.repository;

import dev.bikeService.entity.Bike;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.UUID;
/**
 * Repository interface for ElasticSearch.
 */
public interface BikeRepositoryElasticsearch extends ElasticsearchRepository<Bike, String> {
    List<Bike> findByStationId(UUID stationId);
}


