package com.ecommerceproject.config;


import com.ecommerceproject.dto.UserDTO;
import com.ecommerceproject.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    @Bean
    public ModelMapper modelMapperCustomer() {
        ModelMapper modelMapper = new ModelMapper();

        // Cấu hình mapping từ PA sang PB
        modelMapper.typeMap(User.class, UserDTO.class).addMappings(mapper -> {
            mapper.map(User::getUsername, UserDTO::setUsername);

        });



        return modelMapper;
    }
}
