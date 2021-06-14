package com.learningjava.expensetrackerapi.logics;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.beans.PropertyDescriptor;
import java.util.stream.Stream;



public class CopyProperty {

    public static  String[] getNullPropertyNames(Object source) {

        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);

        PropertyDescriptor[] propertyDescriptors = wrappedSource.getPropertyDescriptors();

        return Stream.of(propertyDescriptors)

                .map(FeatureDescriptor::getName)

                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)

                .toArray(String[]::new);

    }

    public static void CopyObject(Object source, Object target) {

        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }


}
