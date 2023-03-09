package com.oauth.sso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public ClientRegistrationRepository clientRepository() {

		ClientRegistration githubRegistration = CommonOAuth2Provider.GITHUB.getBuilder("github").clientId("id")
				.clientSecret("secret").build();

		ClientRegistration facebookRegistration = CommonOAuth2Provider.FACEBOOK.getBuilder("facebook").clientId("id")
				.clientSecret("secret").build();
		ClientRegistration googleRegistration = CommonOAuth2Provider.GOOGLE.getBuilder("google").clientId("id")
				.clientSecret("secret").build();

		return new InMemoryClientRegistrationRepository(googleRegistration, facebookRegistration, githubRegistration);
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated().and().oauth2Login()
				.loginPage("/").successHandler(successHandler()).failureHandler(failureHandler());
	}

	@Bean
	SimpleUrlAuthenticationSuccessHandler successHandler() {
		return new SimpleUrlAuthenticationSuccessHandler("/success");
	}

	@Bean
	SimpleUrlAuthenticationFailureHandler failureHandler() {
		return new SimpleUrlAuthenticationFailureHandler("/error");
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().anyRequest().authenticated().and().oauth2Login();
//	}
//	@Override
//	protected void configure(HttpSecurity httpSecurity) throws Exception{
//		httpSecurity.csrf(c -> c
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//            ).cors();
//		httpSecurity.authorizeRequests()
//		            .antMatchers("/mainlogin").permitAll()
//		            .anyRequest().authenticated()
//		            .and().oauth2Login().defaultSuccessUrl("/loginsuccess",true);
//		            //.loginPage("/mainlogin")
//		             //.and().defaultSuccessUrl("/loginsuccess");
//		            //.failureUrl("/error");
//	}
//	@Override
//	protected void configure(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity.authorizeRequests()
//		.anyRequest().authenticated().and().oauth2Login()
//				.successHandler(successHandler()).failureHandler(failureHandler());
//	}
//	protected void configure1(HttpSecurity http) throws Exception {
//	    http.authorizeRequests()
//		      .antMatchers("/").permitAll()
//		      .anyRequest().authenticated()
//	      .and()
//		      .oauth2Login()
//		      .loginPage("/");
//	}

}
