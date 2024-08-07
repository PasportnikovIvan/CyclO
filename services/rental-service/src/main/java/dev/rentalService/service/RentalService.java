package dev.rentalService.service;

import dev.rentalService.dto.RentalDTO;
import java.util.List;

/**
 * Service interface for managing rental records.
 * Provides methods for creating, updating, retrieving, and deleting rental records,
 * as well as starting and ending rentals.
 */
public interface RentalService {

    /**
     * Retrieves all rental records.
     *
     * @return a list of RentalDTO objects representing all rental records
     */
    List<RentalDTO> getAllRentalRecords();

    /**
     * Retrieves a rental record by its ID.
     *
     * @param rentalId the ID of the rental record to retrieve
     * @return the RentalDTO representing the retrieved rental record
     */
    RentalDTO getRentalRecordById(Long rentalId);

    /**
     * Creates a new rental record.
     *
     * @param rentalDTO the RentalDTO representing the rental record to create
     * @return the RentalDTO representing the created rental record
     */
    RentalDTO createRentalRecord(RentalDTO rentalDTO);

    /**
     * Updates an existing rental record.
     *
     * @param rentalId the ID of the rental record to update
     * @param rentalDetails the RentalDTO containing the updated rental details
     * @return the RentalDTO representing the updated rental record
     */
    RentalDTO updateRentalRecord(Long rentalId, RentalDTO rentalDetails);

    /**
     * Deletes a rental record by its ID.
     *
     * @param rentalId the ID of the rental record to delete
     */
    void deleteRentalRecord(Long rentalId);

    /**
     * Starts a rental for a user with a specific bike part.
     *
     * @param userId the ID of the user starting the rental
     * @param bikePartId the ID of the bike part being rented
     */
    void startRental(Long userId, Long bikePartId);

    /**
     * Ends a rental by its ID.
     *
     * @param rentalId the ID of the rental to end
     */
    void endRental(Long rentalId);
}
