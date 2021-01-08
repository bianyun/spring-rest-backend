package com.silentcloud.spring.rest.jooq;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.silentcloud.springrest.model.entity.sys.User;
import com.silentcloud.springrest.model.enums.base.EnumConst;
import lombok.SneakyThrows;
import org.jooq.codegen.GenerationTool;
import org.jooq.codegen.JavaGenerator;
import org.jooq.meta.extensions.jpa.JPADatabase;
import org.jooq.meta.jaxb.*;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JooqCodeGenerator {
    private static final String JOOQ_CODEGEN_MODULE_NAME = "sr-jooq";
    private static final String JOOQ_CODEGEN_DATABASE_NAME = JPADatabase.class.getName();
    private static final String JOOQ_CODEGEN_DATABASE_INPUT_SCHEMA = "PUBLIC";
    private static final String JOOQ_CODEGEN_GENERATOR_NAME = JavaGenerator.class.getName();
    private static final String TARGET_DIRECTORY = JOOQ_CODEGEN_MODULE_NAME + "/src/main/java";
    private static final String TARGET_PACKAGE;
    private static final String SCAN_PACKAGE;
    private static final List<Property> DATABASE_PROPERTIES;

    static {
        String basePackage = ClassUtil.getPackage(JooqCodeGenerator.class);
        TARGET_PACKAGE = basePackage.concat(".gen");

        String userClassPackage = ClassUtil.getPackage(User.class);
        SCAN_PACKAGE = userClassPackage.substring(0, userClassPackage.indexOf(".entity"));
        DATABASE_PROPERTIES = Arrays.asList(
                new Property().withKey("packages").withValue(SCAN_PACKAGE),
                new Property().withKey("useAttributeConverters").withValue("true"),
                new Property().withKey("hibernate.physical_naming_strategy")
                        .withValue("org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy")
        );
    }

    @SneakyThrows
    public static void main(String[] args) {
        Configuration configuration = buildConfiguration();
        GenerationTool.generate(configuration);
    }

    private static Configuration buildConfiguration() {
        Target target = new Target()
                .withPackageName(TARGET_PACKAGE)
                .withDirectory(TARGET_DIRECTORY);

        Database database = new Database()
                .withName(JOOQ_CODEGEN_DATABASE_NAME)
                .withIncludeTables(true)
                .withIncludeRoutines(false)
                .withIncludeTriggerRoutines(false)
                .withIncludePackages(false)
                .withIncludePackageRoutines(false)
                .withIncludePackageConstants(false)
                .withIncludePackageUDTs(false)
                .withIncludeUDTs(false)
                .withIncludeSequences(false)
                .withIncludeIndexes(false)
                .withIncludePrimaryKeys(false)
                .withIncludeUniqueKeys(false)
                .withIncludeForeignKeys(false)
                .withProperties(DATABASE_PROPERTIES)
                .withForcedTypes(buildForcedTypes())
                .withInputSchema(JOOQ_CODEGEN_DATABASE_INPUT_SCHEMA);

        Generate generate = new Generate()
                .withJavaTimeTypes(true)
                .withGlobalObjectReferences(true)
                .withGlobalCatalogReferences(false)
                .withGlobalSchemaReferences(false)
                .withGlobalTableReferences(true)
                .withGlobalSequenceReferences(false)
                .withGlobalUDTReferences(false)
                .withGlobalRoutineReferences(false)
                .withGlobalQueueReferences(false)
                .withGlobalLinkReferences(false)
                .withGlobalKeyReferences(false);

        return new Configuration()
                .withGenerator(new Generator()
                        .withName(JOOQ_CODEGEN_GENERATOR_NAME)
                        .withDatabase(database)
                        .withGenerate(generate)
                        .withTarget(target));
    }

    @SuppressWarnings("unchecked")
    private static Set<ForcedType> buildForcedTypes() {
        Set<Class<? extends EnumConst<?, ?>>> classes =
                ClassUtil.scanPackageBySuper(SCAN_PACKAGE, EnumConst.class).stream()
                        .map(clazz -> (Class<? extends EnumConst<?, ?>>)clazz).collect(Collectors.toSet());

        return classes.stream().map(JooqCodeGenerator::generateEnumConstForcedType).collect(Collectors.toSet());
    }

    private static ForcedType generateEnumConstForcedType(Class<? extends EnumConst<?, ?>> enumConstClass) {
        Class<?> idClass = ((Class<?>) ((ParameterizedType) enumConstClass.getGenericInterfaces()[0]).getActualTypeArguments()[1]);
        String enumConstClassSimpleName = enumConstClass.getSimpleName();
        String converter = StrUtil.builder()
                .append("org.jooq.Converter.of(").append(idClass.getSimpleName()).append(".class, ")
                .append(enumConstClassSimpleName).append(".class, ")
                .append("i -> ").append(enumConstClassSimpleName).append(".HELPER.byId(i), ")
                .append(enumConstClassSimpleName).append("::getId)").toString();

        return new ForcedType()
                .withUserType(enumConstClass.getName())
                .withIncludeExpression(".*\\." + StrUtil.toUnderlineCase(enumConstClassSimpleName).toUpperCase())
                .withIncludeTypes(".*")
                .withConverter(converter);
    }
}
