package com.example.ec_site.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(auth -> auth
				// /top を除外し、ログイン画面、新規登録画面、静用リソース、エラーページのみ認証不要にする
				.requestMatchers("/login", "/user_register", "/css/**", "/error").permitAll()
				// その他のリクエストはすべて認証を必須にする
				.anyRequest().authenticated()
			)
			.formLogin(form -> form
				.loginPage("/login") // カスタムログインページのURL
				.loginProcessingUrl("/login") // login.htmlの form action と一致させる
				.defaultSuccessUrl("/top", true) // ログイン成功時は必ず /top へ遷移
				.failureUrl("/login?error") // ログイン失敗時の遷移先
				.permitAll()
			)
			.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout")
				.permitAll()
			);
		return http.build();
	}

	// 本番必須：パスワードを安全にハッシュ化・照合するためのエンコーダー
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}