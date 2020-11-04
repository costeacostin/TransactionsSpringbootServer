package com.bezkoder.spring.jwt.mongodb.repository;

import com.bezkoder.spring.jwt.mongodb.models.Role;
import com.bezkoder.spring.jwt.mongodb.models.Transaction;
import com.bezkoder.spring.jwt.mongodb.models.User;
import com.mongodb.lang.Nullable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    Optional<Transaction> findById(String id);

    List<Transaction> findBySenderUser(String senderUser);

    List<Transaction> findByDestinationUser(String destinationUser);

    List<Transaction> findByDateBetween(Date startDate, Date endDate);

    List<Transaction> findByIdOrSenderUserOrDestinationUserOrDateBetween(String id,
                                                                         String senderUser, String destinationUser, Date startDate, Date endDate);

    List<Transaction> findByIdAndSenderUserAndDestinationUserAndDateBetween(@Nullable String id,
                                                                         @Nullable String senderUser, @Nullable String destinationUser, @Nullable Date startDate, @Nullable Date endDate);

    List<Transaction> findBySenderUserAndDateBetween(String senderUser, Date startDate, Date endDate);
}
