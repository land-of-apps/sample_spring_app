package org.springframework.samples.petclinic.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorizeRequests -> authorizeRequests.requestMatchers("/login")
			.permitAll()
			.anyRequest()
			.authenticated())
			.formLogin(formLogin -> formLogin.loginPage("/login").permitAll().defaultSuccessUrl("/", true))
			.logout(logout -> logout.permitAll());

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder().username("user").password("{noop}password").roles("USER").build();

		return new InMemoryUserDetailsManager(user);
	}

}
