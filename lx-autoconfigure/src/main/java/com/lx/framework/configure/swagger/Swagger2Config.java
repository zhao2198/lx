package com.lx.framework.configure.swagger;


import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author zw 访问地址 http://127.0.0.1:9000/swagger-ui.html
 * 
 *         http://127.0.0.1:8000/ngt/v2/api-docs
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(value = "lx.swagger", matchIfMissing = true)
@ConditionalOnClass({Docket.class, ApiInfo.class})
public class Swagger2Config {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.lx"))
				.paths(PathSelectors.any()) // 过滤的接口
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("test")// 大标题
				.version("1.0")// 版本
				.build();
	}

}
