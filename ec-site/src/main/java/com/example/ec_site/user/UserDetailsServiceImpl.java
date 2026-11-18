package com.example.ec_site.user;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // メールアドレスをキーにしてデータベースからユーザーを検索
        User user = userRepository.findByEmail(email);
        
        if (user == null) {
            throw new UsernameNotFoundException("ユーザーが見つかりません: " + email);
        }

        // Spring Securityが認識するUserDetailsオブジェクトを生成して返す
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),     // ログインIDとして使用する値（メールアドレス）
            user.getPasswords(), // データベースに保存されているハッシュ化済みパスワード
            Collections.emptyList() // 権限（ロール）のリスト（必要に応じて設定）
        );
    }
}