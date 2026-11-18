package com.example.ec_site.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 必要に応じてメールアドレスによる検索メソッドなどを追加可能
    User findByEmail(String email);
}