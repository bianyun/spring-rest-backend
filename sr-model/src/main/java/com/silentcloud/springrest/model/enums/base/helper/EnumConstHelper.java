package com.silentcloud.springrest.model.enums.base.helper;


import com.silentcloud.springrest.model.enums.base.EnumConst;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * An enum helper class
 *
 * @author bianyun
 */
public class EnumConstHelper<E extends Enum<E> & EnumConst<E, ID>, ID> {
    private static final Map<Class<?>, EnumConstHelper<?, ?>> HELPER_MAP = new HashMap<>();

    private final E[] enumConstants;

    private EnumConstHelper(Class<E> type) {
        this.enumConstants = type.getEnumConstants();
    }

    @SuppressWarnings("unchecked")
    public static <E extends Enum<E> & EnumConst<E, ID>, ID>
    EnumConstHelper<E, ID> of(Class<E> actualEnumClass) {
        EnumConstHelper<E, ID> result = (EnumConstHelper<E, ID>) HELPER_MAP.get(actualEnumClass);
        if (result == null) {
            result = new EnumConstHelper<>(actualEnumClass);
            HELPER_MAP.put(actualEnumClass, result);
        }

        return result;
    }

    /**
     * Finds enum {@link E} by id using its {@link EnumConst#getId()}
     * method.
     *
     * @param id an id to search by
     * @return {@link E} enum constant if found. Could throw
     * {@link NoSuchElementException}
     * if there's no constant for given {@code id}
     */
    public E byId(ID id) {
        return byFilter(elem -> elem.getId().equals(id));
    }

    /**
     * Finds enum {@link E} by id using its {@link EnumConst#getId()}
     * method. If one is not found - returns a default value.
     *
     * @param id           an id to search by
     * @param defaultValue a default value to return if nothing is found for given
     *                     {@code id}
     * @return {@link E} enum constant if found, {@code defaultValue}
     * otherwise
     */
    public E byIdOrDefault(ID id, E defaultValue) {
        return byFilterOrDefault(elem -> elem.getId().equals(id), defaultValue);
    }

    /**
     * Finds enum {@link E} by id using its {@link EnumConst#getId()}
     * method. If one is not found - will throw an exception provided by the passed
     * {@code exceptionSupplier}.
     *
     * @param id                an id to search by
     * @param exceptionSupplier provides an exception that will be thrown in case
     *                          nothing is found for a given {@code id}
     * @param <X>               an exception type to be thrown in case nothing is found
     * @return {@link E} enum constant if found, otherwise throws an
     * exception provided by {@code exceptionSupplier}
     * @throws X when nothing is found for a given {@code id}
     */
    public <X extends Throwable> E byIdOrThrow(
            ID id,
            Supplier<? extends X> exceptionSupplier) throws X {
        return byFilterOrThrow(elem -> elem.getId().equals(id), exceptionSupplier);
    }

    /**
     * Finds enum {@link E} by name using its {@link EnumConst#getLabel()}
     * method.
     *
     * @param name a name to search by
     * @return {@link E} enum constant if found. Could throw
     * {@link NoSuchElementException} if there's no constant for given {@code name}
     */
    public E byName(String name) {
        return byFilter(elem -> elem.getLabel().equals(name));
    }

    /**
     * Finds enum {@link E} by name using its {@link EnumConst#getLabel()}
     * method. If one is not found - returns a default value.
     *
     * @param name         a name to search by
     * @param defaultValue a default value to return if nothing is found for given
     *                     {@code name}
     * @return {@link E} enum constant if found, {@code defaultValue}
     * otherwise
     */
    public E byNameOrDefault(String name, E defaultValue) {
        return byFilterOrDefault(elem -> elem.getLabel().equals(name), defaultValue);
    }

    /**
     * Finds enum {@link E} by name using its {@link EnumConst#getLabel()}
     * method. If one is not found - will throw an exception provided by the passed
     * {@code exceptionSupplier}.
     *
     * @param name              an name to search by
     * @param exceptionSupplier provides an exception that will be thrown in case
     *                          nothing is found for a given {@code name}
     * @param <X>               an exception type to be thrown in case nothing is found
     * @return {@link E} enum constant if found, otherwise throws an exception
     * provided by {@code exceptionSupplier}
     * @throws X when nothing is found for a given {@code name}
     */
    public <X extends Throwable> E byNameOrThrow(
            String name,
            Supplier<? extends X> exceptionSupplier) throws X {
        return byFilterOrThrow(elem -> elem.getLabel().equals(name), exceptionSupplier);
    }


    private E byFilter(Predicate<E> predicate) {
        return Arrays.stream(enumConstants)
                .filter(predicate)
                .findAny()
                .orElse(null);
    }

    private E byFilterOrDefault(
            Predicate<E> predicate,
            E defaultValue) {
        return Arrays.stream(enumConstants)
                .filter(predicate)
                .findAny()
                .orElse(defaultValue);
    }

    private <X extends Throwable> E byFilterOrThrow(
            Predicate<E> predicate,
            Supplier<? extends X> exceptionSupplier) throws X {
        return Arrays.stream(enumConstants)
                .filter(predicate)
                .findAny()
                .orElseThrow(exceptionSupplier);
    }
}
