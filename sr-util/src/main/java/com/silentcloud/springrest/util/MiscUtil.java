package com.silentcloud.springrest.util;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@UtilityClass
public class MiscUtil {

    public List<Field> getFieldsListWithAnnotation(final Class<?> clazz, final Class<? extends Annotation> annotationClass) {
        Assert.notNull(clazz, "The class must not be null");
        Assert.notNull(annotationClass, "The annotation class must not be null");

        final Field[] allFields = ReflectUtil.getFields(clazz);
        final List<Field> annotatedFields = new ArrayList<>();
        for (Field field : allFields) {
            if (field.isAnnotationPresent(annotationClass)) {
                annotatedFields.add(field);
            }
        }
        return annotatedFields;
    }

    public IllegalArgumentException illegalArgumentException(String messageTemplate, Object... args) {
        return new IllegalArgumentException(StrUtil.format(messageTemplate, args));
    }

    public RuntimeException runtimeException(Throwable cause, String messageTemplate, Object... args) {
        return new RuntimeException(StrUtil.format(messageTemplate, args), cause);
    }

    public <T> T assignDefaultValueIfNull(T value, @NonNull T defaultValue) {
        return value == null ? defaultValue : value;
    }

    public <T> Class<T> getDtoGenericParameterClass(Class<?> baseClass) {
        return getGenericParameterClass(baseClass, "DTO");
    }

    public <T> Class<T> getEntityGenericParameterClass(Class<?> baseClass) {
        return getGenericParameterClass(baseClass, "Entity");
    }

    @SuppressWarnings("unchecked")
    public <T> Class<T> getGenericParameterClass(Class<?> baseClass, String genericTypeParamName) {
        Assert.notBlank(genericTypeParamName);

        if (!baseClass.isInterface() && baseClass.getGenericSuperclass().getTypeName().equals(Object.class.getName())) {
            return null;
        }

        ParameterizedType parameterizedType;
        if (baseClass.isInterface()) {
            parameterizedType = (ParameterizedType) baseClass.getGenericInterfaces()[0];
        } else {
            parameterizedType = (ParameterizedType) baseClass.getGenericSuperclass();
            if (parameterizedType == null) {
                parameterizedType = (ParameterizedType) baseClass.getGenericInterfaces()[0];
            }
        }

        int genericParamIndex = resolveGenericParamIndex(parameterizedType, genericTypeParamName);
        return ((Class<T>) parameterizedType.getActualTypeArguments()[genericParamIndex]);
    }

    public String parseDomainOfControllerClass(Class<?> controllerClass) {
        String className = controllerClass.getName();
        return StrUtil.subBefore(StrUtil.subAfter(className, "controller.", true), "Controller", false)
                .toLowerCase().replaceAll("\\.", ":");
    }

    private int resolveGenericParamIndex(ParameterizedType parameterizedType, String genericTypeParamName) {
        Class<?> rawType = ((Class<?>) parameterizedType.getRawType());
        TypeVariable<?>[] typeVars = rawType.getTypeParameters();

        return IntStream.range(0, typeVars.length)
                .filter(i -> typeVars[i].getName().equals(genericTypeParamName)).findFirst()
                .orElseThrow(() -> illegalArgumentException("类[{}] 中没有定义泛型参数[{}]", rawType, genericTypeParamName));
    }
 }
