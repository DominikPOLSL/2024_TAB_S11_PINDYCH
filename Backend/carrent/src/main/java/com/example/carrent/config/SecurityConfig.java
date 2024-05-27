package com.example.carrent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.carrent.admin.AdminDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


	@Autowired
	private AdminDetailsService adminDetailsService;

    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
			.authorizeHttpRequests(registry->{
				registry.requestMatchers("/home").permitAll();
				registry.requestMatchers("/admin/**").hasRole("ADMIN");
				registry.requestMatchers("/employee/**").hasRole("USER");
				registry.anyRequest().authenticated();
			})
			.formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
			.build();
	}




		// http
		// 	.authorizeHttpRequests((requests) -> requests
		// 		.requestMatchers("/", "/home").permitAll()
		// 		.anyRequest().authenticated()
		// 	)
		// 	.formLogin((form) -> form
		// 		.loginPage("/login")
		// 		.permitAll()
		// 	)
		// 	.logout((logout) -> logout.permitAll());

		// return http.build();
	

    // @Bean
	// public UserDetailsService userDetailsService() {
	// 	UserDetails normalUser = User.builder()
	// 			.username("user")
	// 			.password("$2a$12$.ncwwlEKyEJeHTIimgl0leRC2T1b/wB/Fjjt3CL2cKGGzIAieh3XC")
	// 			.roles("USER")
	// 			.build();

	// 	UserDetails adminUser = User.builder()
	// 			.username("admin")
	// 			.password("$2a$12$y8pxqsp.MBiBmABIW4rIfOMnuexIi3.69rqGyugiM.Vt5hGtmdgcC")
	// 			.roles("ADMIN", "USER")
	// 			.build();

	// 	return new InMemoryUserDetailsManager(normalUser, adminUser);
	// }

	@Bean
	public UserDetailsService userDetailsService() {
		return adminDetailsService;
	}

	@Bean 
	AuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(adminDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
			
		
	}
    

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
