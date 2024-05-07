package com.example.bookStore.model.repo;

import com.example.bookStore.model.entities.Account;
import com.example.bookStore.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account,Integer> {
   public Optional<Account> findByEmail(String email);

   @Query("SELECT a.user FROM Account a WHERE a.role_id = :roleId")
   List<User> findUsersByAccountRoleId(@Param("roleId") int role_id);

   @Query("SELECT a.email " +
           "FROM Account a " +
           "WHERE a.user.id = :id")
    String findEmailByAccountId(@Param("id") int user_id);
}
