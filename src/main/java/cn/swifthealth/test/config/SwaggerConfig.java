package cn.swifthealth.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wyh
 * @date 2019-04-01
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        ParameterBuilder userIdPar = new ParameterBuilder();
        ParameterBuilder hosIdPar = new ParameterBuilder();
        ParameterBuilder apiKeyPar = new ParameterBuilder();
        ParameterBuilder loginTypePar = new ParameterBuilder();
        ParameterBuilder appversionPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        //添加头信息
//        tokenPar.name(AuthConstants.HTTP_HEADER_TOKEN).description("token").modelRef(new ModelRef("string")).parameterType("header").required(false);
//        userIdPar.name(AuthConstants.HTTP_HEADER_USERID).description("usrId").modelRef(new ModelRef("string")).parameterType("header").required(false);
//        hosIdPar.name(AuthConstants.HTTP_HEADER_HOSID).description("hosId").modelRef(new ModelRef("string")).parameterType("header").required(false);
//        apiKeyPar.name(AuthConstants.HTTP_HEADER_APIKEY).description("apikey").modelRef(new ModelRef("string")).parameterType("header").required(false);
//        loginTypePar.name(AuthConstants.HTTP_HEADER_APP_VERSION).description("logintype").modelRef(new ModelRef("string")).parameterType("header").required(false);
//        appversionPar.name(AuthConstants.HTTP_HEADER_APP_VERSION).description("appversion").modelRef(new ModelRef("string")).parameterType("header").required(false);
//        pars.add(tokenPar.build());
//        pars.add(userIdPar.build());
//        pars.add(hosIdPar.build());
//        pars.add(apiKeyPar.build());
//        pars.add(loginTypePar.build());
//        pars.add(appversionPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("cn.swifthealth"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(pars).pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("redis测试服务")
                .description("redis测试文档")
                .termsOfServiceUrl("https://www.baidu.com/")
                .version("1.0")
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("**/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("**/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
