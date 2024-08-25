package com.philately.repository;


import com.philately.model.entity.Stamp;
import com.philately.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StampRepository extends JpaRepository<Stamp, Long> {
    List<Stamp> findByOwner(User owner);

    // Find all stamps purchased by a specific user
    List<Stamp> findByOwnerAndPurchasedIsTrue(User owner);

    // Find all stamps offered by other users (excluding the current user)
    List<Stamp> findByOwnerNot(User owner);

    // Find all stamps in the wishlist of a specific user
    List<Stamp> findByOwnerAndWishedIsTrue(User owner);
}
