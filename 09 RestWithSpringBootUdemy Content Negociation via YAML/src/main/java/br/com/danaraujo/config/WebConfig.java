package br.com.danaraujo.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.danaraujo.serialization.converter.YamlJackson2HttpMessageConverter;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	
	private static final MediaType MEDIA_TYPE_YML = MediaType.valueOf("application/x-yaml");
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters ) {
		
		converters.add(new YamlJackson2HttpMessageConverter());
	}
	//passar através do YAML
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configure) {
		configure.favorPathExtension(false) //deixa de enviar via extensão
		.favorParameter(false)
		.ignoreAcceptHeader(false)
		.useRegisteredExtensionsOnly(false)
		.defaultContentType(MediaType.APPLICATION_JSON)
		.mediaType("json", MediaType.APPLICATION_JSON)
		.mediaType("xml", MediaType.APPLICATION_XML)
		.mediaType("x-yaml", MEDIA_TYPE_YML);
	}
}
