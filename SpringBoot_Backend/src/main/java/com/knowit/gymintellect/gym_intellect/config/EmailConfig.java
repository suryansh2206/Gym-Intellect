package com.knowit.gymintellect.gym_intellect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

	public JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }
}
