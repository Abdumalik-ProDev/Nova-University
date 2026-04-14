package abdumalik.prodev.staffmodule.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE) // FORCE this one to be the VERY FIRST config loaded
public class StaffSecurityConfig {

//    static {
//        System.out.println("DEBUG: StaffSecurityConfig class has been loaded by the JVM");
//    }

//    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    @Primary // Spring ga FilterChain larni adashtirmasligi uchun va asosan shu classni ishlatishi uchun
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("BEAN INITILIZATION STARTED");
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/api/v1/staff/public/**").permitAll()
//                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
//                .anyRequest().authenticated()
                    .anyRequest().permitAll()
            );
//            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
