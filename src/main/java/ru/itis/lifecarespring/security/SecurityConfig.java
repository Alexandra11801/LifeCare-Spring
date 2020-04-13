package ru.itis.lifecarespring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier(value = "customUserDetailsService")
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/signup/**").permitAll()
				.antMatchers("/email_confirm/**").permitAll()
				.antMatchers("/sms_confirm").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("/profile/**").authenticated()
				.antMatchers("/edit").authenticated()
				.antMatchers("/article/**").permitAll()
				.antMatchers("/add").authenticated()
				.antMatchers("/search/**").permitAll();
		http.formLogin()
				.loginPage("/signin")
				.defaultSuccessUrl("/")
				.failureUrl("/signin?error")
				.usernameParameter("email")
				.permitAll();
		http.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.permitAll();
	}

	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}
}
