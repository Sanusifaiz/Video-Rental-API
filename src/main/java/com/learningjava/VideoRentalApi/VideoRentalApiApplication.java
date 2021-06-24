package com.learningjava.VideoRentalApi;

import com.learningjava.VideoRentalApi.Authentication.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VideoRentalApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(VideoRentalApiApplication.class, args);}

		@Bean
		public FilterRegistrationBean<AuthFilter> filterFilterRegistrationBean() {
			FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
			AuthFilter authFilter = new AuthFilter();
			registrationBean.setFilter(authFilter);
			registrationBean.addUrlPatterns("/api/categories/*","/api/transactions/*","/api/videos/*");
			return registrationBean;
		}


}
