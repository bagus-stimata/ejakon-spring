package com.desgreen.gov.database.presentation.SecurityConfig;

import com.desgreen.gov.database.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter  implements WebMvcConfigurer{
    

    // /*
    // Ini akan melakukan permit all kepada semua
    // */
    // // @Override  
    // // protected void configure(HttpSecurity http) throws Exception {
    // //     http.authorizeRequests().anyRequest().permitAll();          
    // // }
    @Autowired
    private MyAuthenticationSuccessHandler successHandler;

    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;


    @Autowired
    private AuthUserDetailsService userDetailsService;

    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    // 	auth.inMemoryAuthentication()
    //       .withUser("user")
    //         .password(passwordEncoder().encode("user"))
    //         .roles(Role.USER, "USER")
    //       .and()
    //       .withUser("admin")
    //         .password(passwordEncoder().encode("admin"))
    //         // .roles(Role.ADMIN, Role.USER);
    //         .roles(Role.ADMIN);
    // }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

    //Dengan database cara pertama
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        auth.userDetailsService(userDetailsService).passwordEncoder(PassEncoding.getInstance().passwordEncoder());
    }    

    // @Bean //tidak perlu di perlukan
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }


    // Dengan database cara kedua
    // @Bean
    // public DaoAuthenticationProvider authenticationProvider() {
    //     DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    //     authenticationProvider.setUserDetailsService(userDetailsService);
    //     authenticationProvider.setPasswordEncoder(passwordEncoder());
    //     return authenticationProvider;
    // }

    // @Autowired
    // public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.userDetailsService(userDetailsService);
    //     auth.authenticationProvider(authenticationProvider());
    // }

    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.userDetailsService(userDetailsService);
    // }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }


    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http
           /**
             *  dengan menggunakan metode/konsep csrf yang digunakan pada controller 
             *  akan dapat mengamankan aplikasi. metode ini seperti halnya Outh2 yang dipasang
             *  pada controller
             * 
             * Khusus untuk vaadin .csrf harus di dsable
             */
            .csrf().disable()

			.authorizeRequests()
                // .antMatchers( "/**" ).permitAll() // Untuk pMelakukan Permit kepada semua dan tidak perlu otorisasi: Mengacu pada contoh diatas
                // // .antMatchers( "/", "/home", "resources/**", "/registration" ).permitAll() // daftar un-secure page

                .antMatchers(
                    "/**",
                    "/home",
                    "/js/**",
                    "/css/**", "/font/**", "/mdb-addons/**", "/scss/**", 
                    "/src/**", "/assets/**", 
                    "/img/**", "/images/**", "/app-images/**",
                    "/template/**",
                    "/webjars/**", "/registration")
                .permitAll()
                    
                /**
                 * Otomatis dibaca:
                 *  ROLE_ADMIN, ROLE_USER, ROLE_ACCOUNTING
                 */

                // .antMatchers("/admin/**").hasRole("ADMIN")
                // .antMatchers("/task/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/login*", "/register/**")
                .permitAll() 
                .anyRequest().authenticated()
			.and()
			.formLogin() //Jika un-autorize akan di redirct ke /login 
                .loginPage("/login")
                // .failureUrl("/login?error=1")
                // .defaultSuccessUrl("/home")
                .successHandler(successHandler) //more spesific using success handler
				.permitAll()
			.and()
            .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            .and()
            .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
                // .accessDeniedPage("/access-denied"); //this is the same
                
    }
    
	/**
     * Just use for  ### VAADIN FLOW ####
	 * Allows access to static resources, bypassing Spring security.
	 */
	// @Override
	// public void configure(WebSecurity web) {
	// 	web.ignoring().antMatchers(

	// 			// Vaadin Flow static resources
	// 			"/VAADIN/**",

	// 			// // the standard favicon URI
	// 			"/favicon.ico",

	// 			// // the robots exclusion standard
	// 			"/robots.txt",

	// 			// // web application manifest
	// 			"/manifest.webmanifest",
	// 			"/sw.js",
	// 			"/offline-page.html",

	// 			// // icons and images
	// 			"/icons/**",
	// 			"/images/**",

	// 			// // (development mode) static resources
	// 			"/frontend/**",

    //             /**
    //              * #### INI BUAT ERROR TIME STAMP YA ####
    //              */
	// 			// // (development mode) webjars
	// 			// "/webjars/**",

	// 			// // (development mode) H2 debugging console
	// 			"/h2-console/**",

	// 			// // (production mode) static resources
    //             "/frontend-es5/**", "/frontend-es6/**"
                
    //             );
	// }




    // @Autowired
    // private MyBasicAuthenticationEntryPoint authenticationEntryPoint;
 
    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.inMemoryAuthentication()
    //       .withUser("user").password(passwordEncoder().encode("user")).authorities("ROLE_USER")
    //       .and()
    //       .withUser("admin").password(passwordEncoder().encode("admin")).roles("USER", "ADMIN");    
    // }

 
       // public static String uploadDirectory= System.getProperty("user.home") + "\\images";
    //  public static String uploadDirectory = "/Users/yhawin/gambarnya";
    public static String uploadDirectory = Constants.APP_IMAGE_PATH;

    /**
     * gambarnya --> /images
     */
   //  @Override
   //  public void addResourceHandlers(ResourceHandlerRegistry registry) {
   //      registry.addResourceHandler("/images/**").addResourceLocations("file:" + uploadDirectory+"/");
   //  }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**", "/css/**", "/img/**", "/js/**")
                .addResourceLocations(
               "file:" + uploadDirectory + "/"
               , "file:" + uploadDirectory + "/jasper_report/"
               ,"classpath:/static/home"
               ,"classpath:/static/js/"
               ,"classpath:/static/css/"
                ,"classpath:/static/src/"
                ,"classpath:/static/img/"
                ,"classpath:/static/assets/"
                ,"classpath:/static/template/"
                ,"classpath:/static/webjars/");
    }

  
}