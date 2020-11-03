package pl.coderslab.charity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import pl.coderslab.charity.service.MySimpleUrlAuthenticationSuccessHandler;
import pl.coderslab.charity.service.SpringDataUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public SpringDataUserDetailsService customUserDetailsService() {
		return new SpringDataUserDetailsService();
	}
	
	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
	    return new MySimpleUrlAuthenticationSuccessHandler();
	}
	
	@Override
	protected void configure(HttpSecurity http)throws Exception {  
		http.authorizeRequests()        
		.antMatchers("/").permitAll()
		.antMatchers("/donation").authenticated()
		.and().formLogin()
			.loginPage("/login")
			.successHandler(myAuthenticationSuccessHandler())
			.usernameParameter("email")
			.failureUrl("/login?auth=failure")
		.and().logout()
			.logoutSuccessUrl("/");
	}

}
