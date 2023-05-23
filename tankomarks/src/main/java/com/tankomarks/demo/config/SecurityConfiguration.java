package com.tankomarks.demo.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.tankomarks.demo.service.UsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioService usuarioService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(usuarioService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	String[] resources = new String[] {
			"/styles/**", "/images/**", "/fonts/**", "/js/**","/error/**"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(resources).permitAll()
				.antMatchers("/registro").hasRole("ANONYMOUS")
				.antMatchers("/", "/favoritos", "/perfil", "/leyendo", "/busqueda", "/leidos").hasAnyAuthority("USER") 
				.antMatchers("/administracion").hasAnyAuthority("ADMIN")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.successHandler((request, response, authentication) -> {
		            // Obtener los roles del usuario autenticado
		            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		            for (GrantedAuthority authority : authorities) {
		                if (authority.getAuthority().equals("ADMIN")) {
		                    // Si el rol es admin, redireccionar a /administracion
		                    response.sendRedirect("/administracion");
		                } else {
		                    // Si el rol es user u otro, redireccionar a /
		                    response.sendRedirect("/");
		                }
		            }
		        })
				.permitAll()
				.and()
				.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout")
				.permitAll()
				.and()
	            .exceptionHandling().accessDeniedPage("/403")
				;
	}
}
