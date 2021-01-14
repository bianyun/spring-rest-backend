package com.silentcloud.springrest.util;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.lang.SimpleCache;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.experimental.UtilityClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

@UtilityClass
public class LabelUtil {
    private final boolean useConjunctionBetweenClassAndField = true;
    private final String conjunctionBetweenClassAndField = "的";
    private final Class<? extends Annotation> CLASS_LABEL_ANNOTATION_CLASS = ApiModel.class;
    private final Class<? extends Annotation> FIELD_LABEL_ANNOTATION_CLASS = ApiModelProperty.class;
    private final SimpleCache<AnnotatedElement, String> LABEL_CACHE = new SimpleCache<>();

    public String getClassLabel(Class<?> clazz) {
        return clazz == null ? StrUtil.EMPTY : getLabel(clazz, CLASS_LABEL_ANNOTATION_CLASS);
    }

    public String getFieldLabel(Field field) {
        return getLabel(field, FIELD_LABEL_ANNOTATION_CLASS);
    }

    public String getFieldFullLabel(Class<?> clazz, Field field) {
        String classLabel = getClassLabel(clazz);
        String fieldLabel = getFieldLabel(field);

        if (classLabel.equals(clazz.getSimpleName())) {
            return fieldLabel;
        }

        if (useConjunctionBetweenClassAndField) {
            return classLabel + conjunctionBetweenClassAndField + fieldLabel;
        } else {
            return classLabel + fieldLabel;
        }
    }

    private String getLabel(AnnotatedElement annotatedElement, Class<? extends Annotation> annotionClass) {
        String label = LABEL_CACHE.get(annotatedElement);
        if (label == null) {
            label = AnnotationUtil.getAnnotationValue(annotatedElement, annotionClass);
            String annotatedElementName = parseAnnotatedElementName(annotatedElement);
            label = StrUtil.isNotBlank(label) ? label : annotatedElementName;
            LABEL_CACHE.put(annotatedElement, label);
        }
        return label;
    }

    private String parseAnnotatedElementName(AnnotatedElement annotatedElement) {
        if (annotatedElement instanceof Class<?>) {
            return ((Class<?>) annotatedElement).getSimpleName();
        } else if (annotatedElement instanceof Field) {
            return ((Field) annotatedElement).getName();
        } else {
            return StrUtil.EMPTY;
        }
    }
}
