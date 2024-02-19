package com.hastega.review.userlibrary.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public interface UserBookRepository extends JpaRepository<UserBookEntity, BookID> {

    List<UserBookEntity> findByBookIdUserID(Integer UserId);
}
