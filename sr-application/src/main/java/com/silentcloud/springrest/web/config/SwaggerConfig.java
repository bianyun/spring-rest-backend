package com.silentcloud.springrest.web.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import com.silentcloud.springrest.web.controller.AbstractBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Predicate;

@Configuration
@ConditionalOnProperty("knife4j.enable")
@EnableSwagger2WebMvc
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig implements WebMvcConfigurer {
    private static final List<ResponseMessage> RESPONSE_MESSAGES = responseMessageList();

    private static final String MODULE_BASE_PACKAGE = AbstractBaseController.class.getPackage().getName();

    private static final List<Module> MODULES_IN_ORDER = Arrays.asList(
            Module.of("系统管理", "sys"),
            Module.of("馆藏管理", "lib")
    );

    private final ApplicationContext applicationContext;
    private final OpenApiExtensionResolver openApiExtensionResolver;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public SwaggerConfig(ApplicationContext applicationContext,
                         OpenApiExtensionResolver openApiExtensionResolver) {
        this.applicationContext = applicationContext;
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    @PostConstruct
    public void init() {
        GenericApplicationContext context = (GenericApplicationContext) this.applicationContext;

        for (Module module : MODULES_IN_ORDER) {
            createApiDocketBean(context, module, openApiExtensionResolver);
        }
    }

    private static void createApiDocketBean(GenericApplicationContext context, Module module,
                                            OpenApiExtensionResolver openApiExtensionResolver) {
        Predicate<RequestHandler> apiPredicates = RequestHandlerSelectors.basePackage(module.getBasePackage())
                .and(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .and(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .and(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class));

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName(module.getNameChs())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, RESPONSE_MESSAGES)
                .globalResponseMessage(RequestMethod.POST, RESPONSE_MESSAGES)
                .globalResponseMessage(RequestMethod.PUT, RESPONSE_MESSAGES)
                .globalResponseMessage(RequestMethod.DELETE, RESPONSE_MESSAGES)
                .select()
                .apis(apiPredicates)
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildExtensions(module.getNameChs()))
                .apiInfo(apiInfo());

        context.registerBean(module.getDocketBeanName(), Docket.class, () -> docket);
    }

    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder
                .builder()
                .operationsSorter(OperationsSorter.ALPHA)
                .displayRequestDuration(true)
                .tagsSorter(TagsSorter.ALPHA)
                .defaultModelExpandDepth(1)
                .defaultModelsExpandDepth(2)
                .build();
    }

    private static List<ResponseMessage> responseMessageList() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(400).message("请求不合法").build(),
                new ResponseMessageBuilder()
                        .code(401).message("访问未授权").build(),
                new ResponseMessageBuilder()
                        .code(403).message("资源不可用").build(),
                new ResponseMessageBuilder()
                        .code(404).message("资源不存在").build(),
                new ResponseMessageBuilder()
                        .code(409).message("与服务器资源冲突").build(),
                new ResponseMessageBuilder()
                        .code(500).message("服务器发生异常")
                        .responseModel(new ModelRef("string"))
                        .build()
        );
    }

    private static ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring-Rest 项目接口文档")
                .version("1.0.0")
                .build();
    }

    @ConditionalOnProperty("knife4j.enable")
    @Primary
    @Component
    public static class CustomSwaggerResourceProvider extends InMemorySwaggerResourcesProvider {
        private static final Map<String, Integer> API_GROUP_ORDER_MAP;

        static {
            Map<String, Integer> tempMap = new HashMap<>();
            for (int i = 0; i < MODULES_IN_ORDER.size(); i++) {
                Module module = MODULES_IN_ORDER.get(i);
                tempMap.put(module.getNameChs(), i + 1);
            }
            API_GROUP_ORDER_MAP = Collections.unmodifiableMap(tempMap);
        }

        private static final Comparator<SwaggerResource> SWAGGER_RESOURCE_COMPARATOR = (res1, res2) -> {
            String groupName1 = res1.getName();
            String groupName2 = res2.getName();

            Integer order1 = API_GROUP_ORDER_MAP.getOrDefault(groupName1, Integer.MAX_VALUE);
            Integer order2 = API_GROUP_ORDER_MAP.getOrDefault(groupName2, Integer.MAX_VALUE);

            if (order1.equals(order2)) {
                return groupName1.compareTo(groupName2);
            } else {
                return order1 - order2;
            }
        };

        @Autowired
        public CustomSwaggerResourceProvider(Environment environment, DocumentationCache documentationCache) {
            super(environment, documentationCache);
        }

        @Override
        public List<SwaggerResource> get() {
            List<SwaggerResource> resources = super.get();
            resources.sort(SWAGGER_RESOURCE_COMPARATOR);
            return resources;
        }
    }

    private static class Module {
        private final String nameChs;
        private final String basePackage;
        private final String docketBeanName;

        private Module(String nameChs, String subPackageName) {
            this.nameChs = nameChs;
            this.basePackage = MODULE_BASE_PACKAGE + "." + subPackageName;
            this.docketBeanName = subPackageName + "Api";
        }

        public static Module of(String nameChs, String subPackageName) {
            return new Module(nameChs, subPackageName);
        }

        public String getNameChs() {
            return nameChs;
        }

        public String getBasePackage() {
            return basePackage;
        }

        public String getDocketBeanName() {
            return docketBeanName;
        }
    }
}
