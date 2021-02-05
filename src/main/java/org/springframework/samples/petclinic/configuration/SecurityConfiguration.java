package org.springframework.samples.petclinic.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author japarejo
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/resources/**","/webjars/**","/h2-console/**").permitAll()
				.antMatchers(HttpMethod.GET, "/","/oups").permitAll()
				.antMatchers("/users/new").permitAll()
				.antMatchers("/admin/**").hasAnyAuthority("admin")
				.antMatchers("/mecanico/**").hasAnyAuthority("mecanico","admin")
				.antMatchers("/cliente/**").hasAnyAuthority("admin")
				.antMatchers("/clientes/**").hasAnyAuthority("admin")
				.antMatchers("/cita/find").hasAnyAuthority("admin")
				.antMatchers("/cliente/{clienteId}/cita/new").hasAnyAuthority("cliente", "admin")
				.antMatchers("/cliente/{clienteId}/cita/**").hasAnyAuthority("cliente")
				.antMatchers("/cliente/{clienteId}/citas").hasAnyAuthority("cliente")
				.antMatchers("/citas/new").hasAnyAuthority("admin", "cliente")
				.antMatchers("/citas").hasAnyAuthority("mecanico","admin")
				.antMatchers("/citas/cliente").hasAnyAuthority("cliente")
				.antMatchers("/cita/**").hasAnyAuthority("mecanico","admin")
				.antMatchers("/cita/{citaId}/details").authenticated()
				.antMatchers("/cita/{citaId}/remove").hasAnyAuthority("admin", "cliente")
				.antMatchers("/vehiculo/new").hasAnyAuthority("cliente")
				.antMatchers("/vehiculos").hasAnyAuthority("mecanico", "admin")
				.antMatchers("/vehiculo/**").hasAnyAuthority("mecanico", "admin")
				.antMatchers("/vehiculos/cliente").hasAnyAuthority("cliente")
				.antMatchers("/vehiculo/{vehiculoId}").permitAll()
				.antMatchers("/vehiculo/**").permitAll()
				.antMatchers("/vehiculo/reparaciones").hasAnyAuthority("admin", "mecanico")
				.antMatchers("/vehiculo/delete/{vehiculoId}").hasAnyAuthority("admin", "cliente")
				.antMatchers("/revision/all").hasAnyAuthority("mecanico")
				.antMatchers("/revisiones").hasAnyAuthority("admin")
				.antMatchers("/revision/**").hasAnyAuthority("admin","mecanico")
				.antMatchers("/pedidos").hasAnyAuthority("admin")
				.antMatchers("/pedidos/**").hasAnyAuthority("admin")
				.antMatchers("/pedido/**").hasAnyAuthority("admin")
				.antMatchers("/pedido/all").hasAnyAuthority("admin")
				.antMatchers("/pedido/new").hasAnyAuthority("admin")
				.antMatchers("/proveedores").hasAnyAuthority("admin")
				.antMatchers("/proveedor/all").hasAnyAuthority("admin")
				.antMatchers("/proveedores/new").hasAnyAuthority("admin")
				.antMatchers("/proveedor/**").hasAnyAuthority("admin")
				.antMatchers("/productos").hasAnyAuthority("admin")
				.antMatchers("/productos/**").hasAnyAuthority("admin")
				.antMatchers("/facturas/**").hasAnyAuthority("admin","cliente")
				.antMatchers("/factura/**").hasAnyAuthority("admin")
				.antMatchers("/estancias").hasAnyAuthority("admin", "mecanico")
				.antMatchers("/estancias/**").hasAnyAuthority("admin", "mecanico")
				.antMatchers("/reparacion/**").hasAnyAuthority("admin", "mecanico")
				.antMatchers("/reparaciones").hasAnyAuthority("admin", "mecanico")
				.antMatchers("/reparaciones/**").hasAnyAuthority("admin", "mecanico")
				.anyRequest().denyAll()
				.and()
				 	.formLogin()
				 	/*.loginPage("/login")*/
				 	.failureUrl("/login-error")
				.and()
					.logout()
						.logoutSuccessUrl("/"); 
                // Configuración para que funcione la consola de administración 
                // de la BD H2 (deshabilitar las cabeceras de protección contra
                // ataques de tipo csrf y habilitar los framesets si su contenido
                // se sirve desde esta misma página.
                http.csrf().ignoringAntMatchers("/h2-console/**");
                http.headers().frameOptions().sameOrigin();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
	      .dataSource(dataSource)
	      .usersByUsernameQuery(
	       "select username,password,enabled "
	        + "from users "
	        + "where username = ?")
	      .authoritiesByUsernameQuery(
	       "select username, authority "
	        + "from authorities "
	        + "where username = ?")	      	      
	      .passwordEncoder(passwordEncoder());	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {	    
		PasswordEncoder encoder =  NoOpPasswordEncoder.getInstance();
	    return encoder;
	}
	
}


