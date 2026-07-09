package valeriafarinosi.U5_W2_D4.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigClass {

    @Bean
    public Cloudinary getCloudinaryUploader() {
    }

}
