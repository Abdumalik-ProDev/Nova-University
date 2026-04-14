package abdumalik.prodev.staffmodule;


import abdumalik.prodev.securitymodule.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;import org.springframework.cloud.openfeign.EnableFeignClients;import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Import(SecurityConfig.class) // added this line cause spring cannot find it
public class StaffModuleApplication {
    public static void main(String[] args){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("NOVA STAFF MODULE IS STARTING - VERSION 2.0");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        SpringApplication.run(StaffModuleApplication.class, args);
    }
}
