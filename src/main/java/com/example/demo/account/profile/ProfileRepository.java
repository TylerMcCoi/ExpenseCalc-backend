package com.example.demo.account.profile;

import com.example.demo.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query("SELECT s FROM Profile s WHERE s.account = ?1")
    Optional<List<Profile>> findProfilesByAccount(Account account);
    @Query("SELECT s FROM Profile s WHERE s.profileID = ?1 and s.account = ?2")
    Optional<Profile> findProfileByID(Long profileID, Account account);
}
