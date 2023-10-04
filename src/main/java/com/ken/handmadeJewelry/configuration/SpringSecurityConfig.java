package com.ken.handmadeJewelry.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		post通信をするために、csrfを無効化
		http.csrf().disable();

//		ロールがUSERのユーザーにアクセス許可付与
		http.authorizeRequests().antMatchers("/artistPrivate", "/resisterProduct", "/deleteProduct").hasRole("USER")
				.anyRequest().permitAll().and()
//				ログイン後に遷移するページを指定(ログイン前アクサス試行したページに依らない(true))
				.formLogin(form -> form.loginPage("/login").permitAll().defaultSuccessUrl("/redirectLogin", true))
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/redirectLogout").and().formLogin().failureUrl("/loginFail");
		return http.build();
	}

//	ユーザ情報の読み込み
	@Bean
	UserDetailsManager users(DataSource dataSource) {
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
		return users;
	}

//	デフォルトのエンコーダをbcryptにセット
	@Bean
	public static PasswordEncoder passwordEncoder() {
		String idForEncode = "bcrypt";
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put(idForEncode, new BCryptPasswordEncoder());
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idForEncode, encoders);
		passwordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());

		return passwordEncoder;
	}
}
