package com.safelet.walletserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;


/**
 * Esta clase tiene habilitada la seguridad básica.
 *
 * @author Andres Sanchez
 */

@SpringBootApplication
//@EnableWebSecurity
public class WalletServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletServerApplication.class, args);
	}

//	/**
//	 * Provee la seguridad basica para el acceso a diferentes partes de la API
//	 */
//	@Configuration
//	static class SecurityConfig extends WebSecurityConfiguration {
//
//		protected void configure(HttpSecurity http) throws Exception {
//			http.authorizeHttpRequests()
//					.and()
//					.formLogin()
//					.and()
//					.logout();
//		}
//
//		@Bean
//		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//			http
//					.authorizeHttpRequests((authorizeHttpRequests) ->
//							authorizeHttpRequests
//									.requestMatchers("/**").hasRole("USER")
//									.requestMatchers("/admin/**").hasRole("ADMIN")
//					);
//			return http.build();
//		}
//
//		/**
//		 * Solo permite conexiones seguras mediante protocolo HTTPS
//		 * @param http
//		 * @return
//		 * @throws Exception
//		*/
//		@Bean
//		SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//			return http
//					.requiresChannel(channel ->
//							channel.anyRequest().requiresSecure())
//					.authorizeRequests(authorize ->
//							authorize.anyRequest().permitAll())
//					.build();
//		}
//	}
}
