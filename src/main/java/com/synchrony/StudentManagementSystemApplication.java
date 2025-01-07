package com.synchrony;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class StudentManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}



	/**
	 * Bean definition for {@link MessageSource}.
	 *
	 * The `MessageSource` is responsible for loading messages from a properties file.
	 * In this configuration, it is set to load messages from `messages.properties` located in the `resources` folder.
	 *
	 * Features:
	 * - Supports UTF-8 encoding for multilingual support.p
	 * - Can handle messages for validation annotations and other application-level messages.
	 *
	 * @return a configured {@link ResourceBundleMessageSource} instance.
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages"); // Load messages from messages.properties
		messageSource.setDefaultEncoding("UTF-8"); // Support for multilingual characters
		return messageSource;
	}

	/**
	 * Bean definition for {@link LocalValidatorFactoryBean}.
	 *
	 * The `LocalValidatorFactoryBean` is a Spring-specific implementation that bridges the Java Bean Validation (JSR-380)
	 * with the Spring framework. This configuration links the validation system with the `MessageSource` to enable
	 * custom validation messages defined in `messages.properties`.
	 *
	 * Features:
	 * - Ensures validation annotations (e.g., @Pattern, @NotNull) use messages from `messages.properties`.
	 * - Simplifies localization of validation messages for internationalized applications.
	 *
	 * @param ms the {@link MessageSource} instance to be used for resolving validation messages.
	 * @return a configured {@link LocalValidatorFactoryBean} instance.
	 */
	@Bean
	public LocalValidatorFactoryBean validator(MessageSource ms) {
		LocalValidatorFactoryBean lvfb = new LocalValidatorFactoryBean();
		lvfb.setValidationMessageSource(ms); // Link the message source to validation
		return lvfb;
	}


	/**
	 * Configures and provides a ModelMapper bean.
	 *
	 * @return ModelMapper instance with custom configurations.
	 */
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		// Set the configuration to skip null fields during mapping
		modelMapper.getConfiguration()
				.setFieldMatchingEnabled(true)  // Enables matching fields directly
				.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)  // Access private fields
				.setSkipNullEnabled(true);  // Skip null values during mapping
		return modelMapper;
	}
}
