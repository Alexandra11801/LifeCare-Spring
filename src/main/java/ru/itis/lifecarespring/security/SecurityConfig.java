package ru.itis.lifecarespring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier(value = "customUserDetailsService")
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private DataSource dataSource;

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
		return jdbcTokenRepository;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/signup/**").permitAll()
				.antMatchers("/email_confirm/**").permitAll()
				.antMatchers("/sms_confirm").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("/profile/**").authenticated()
				.antMatchers("/edit").authenticated()
				.antMatchers("/article/**").permitAll()
				.antMatchers("/add").authenticated()
				.antMatchers("/search/**").permitAll()
				.antMatchers("/suggest-revision").authenticated()
				.antMatchers("/revisions/**").authenticated()
				.antMatchers("/sockets").authenticated()
				.antMatchers("/chat").authenticated()
				.and().rememberMe().rememberMeParameter("rememberMe").tokenRepository(persistentTokenRepository());
		http.formLogin()
				.loginPage("/signin")
				.defaultSuccessUrl("/")
				.failureUrl("/signin?error")
				.usernameParameter("email")
				.permitAll();
		http.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
				.deleteCookies("SESSION", "rememberMe")
				.invalidateHttpSession(true)
				.permitAll();
	}

	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}
}
