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

/**
 * Security configuration
 * @author ken
 *
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	/**
	 * Access permissions
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		Disable csrf to unable post resquests
		http.csrf().disable();

//		Give access permisions to USER role
		http.authorizeRequests().antMatchers("/artistPrivate", "/resisterProduct", "/deleteProduct").hasRole("USER")
				.anyRequest().permitAll().and()
//				Set a page redirected after login (independent to a page before login (ture))
				.formLogin(form -> form.loginPage("/login").permitAll().defaultSuccessUrl("/redirectLogin", true))
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/redirectLogout").and().formLogin().failureUrl("/loginFail");
		return http.build();
	}

	/**
	 * Load user info
	 * @param dataSource
	 * @return
	 */
	@Bean
	UserDetailsManager users(DataSource dataSource) {
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
		return users;
	}

	/**
	 * Password encoder
	 * @return
	 */
	@Bean
	public static PasswordEncoder passwordEncoder() {
		String idForEncode = "bcrypt";
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put(idForEncode, new BCryptPasswordEncoder());
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idForEncode, encoders);
//		Set a default encoder to bcrypt
		passwordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());

		return passwordEncoder;
	}
}
