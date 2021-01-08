/*
 * This file is generated by jOOQ.
 */
package com.silentcloud.spring.rest.jooq.gen.tables.records;


import com.silentcloud.spring.rest.jooq.gen.tables.LibPublisher;
import com.silentcloud.springrest.model.enums.CountryCode;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record9;
import org.jooq.Row9;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class LibPublisherRecord extends TableRecordImpl<LibPublisherRecord> implements Record9<Long, LocalDateTime, LocalDateTime, String, CountryCode, String, String, Long, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>LIB_PUBLISHER.ID</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>LIB_PUBLISHER.ID</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>LIB_PUBLISHER.CREATED_TIME</code>.
     */
    public void setCreatedTime(LocalDateTime value) {
        set(1, value);
    }

    /**
     * Getter for <code>LIB_PUBLISHER.CREATED_TIME</code>.
     */
    public LocalDateTime getCreatedTime() {
        return (LocalDateTime) get(1);
    }

    /**
     * Setter for <code>LIB_PUBLISHER.LAST_MODIFIED_TIME</code>.
     */
    public void setLastModifiedTime(LocalDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>LIB_PUBLISHER.LAST_MODIFIED_TIME</code>.
     */
    public LocalDateTime getLastModifiedTime() {
        return (LocalDateTime) get(2);
    }

    /**
     * Setter for <code>LIB_PUBLISHER.ADDRESS</code>.
     */
    public void setAddress(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>LIB_PUBLISHER.ADDRESS</code>.
     */
    public String getAddress() {
        return (String) get(3);
    }

    /**
     * Setter for <code>LIB_PUBLISHER.COUNTRY_CODE</code>.
     */
    public void setCountryCode(CountryCode value) {
        set(4, value);
    }

    /**
     * Getter for <code>LIB_PUBLISHER.COUNTRY_CODE</code>.
     */
    public CountryCode getCountryCode() {
        return (CountryCode) get(4);
    }

    /**
     * Setter for <code>LIB_PUBLISHER.NAME</code>.
     */
    public void setName(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>LIB_PUBLISHER.NAME</code>.
     */
    public String getName() {
        return (String) get(5);
    }

    /**
     * Setter for <code>LIB_PUBLISHER.ZIP_CODE</code>.
     */
    public void setZipCode(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>LIB_PUBLISHER.ZIP_CODE</code>.
     */
    public String getZipCode() {
        return (String) get(6);
    }

    /**
     * Setter for <code>LIB_PUBLISHER.CREATED_BY_ID</code>.
     */
    public void setCreatedById(Long value) {
        set(7, value);
    }

    /**
     * Getter for <code>LIB_PUBLISHER.CREATED_BY_ID</code>.
     */
    public Long getCreatedById() {
        return (Long) get(7);
    }

    /**
     * Setter for <code>LIB_PUBLISHER.LAST_MODIFIED_BY_ID</code>.
     */
    public void setLastModifiedById(Long value) {
        set(8, value);
    }

    /**
     * Getter for <code>LIB_PUBLISHER.LAST_MODIFIED_BY_ID</code>.
     */
    public Long getLastModifiedById() {
        return (Long) get(8);
    }

    // -------------------------------------------------------------------------
    // Record9 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row9<Long, LocalDateTime, LocalDateTime, String, CountryCode, String, String, Long, Long> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    @Override
    public Row9<Long, LocalDateTime, LocalDateTime, String, CountryCode, String, String, Long, Long> valuesRow() {
        return (Row9) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return LibPublisher.LIB_PUBLISHER.ID;
    }

    @Override
    public Field<LocalDateTime> field2() {
        return LibPublisher.LIB_PUBLISHER.CREATED_TIME;
    }

    @Override
    public Field<LocalDateTime> field3() {
        return LibPublisher.LIB_PUBLISHER.LAST_MODIFIED_TIME;
    }

    @Override
    public Field<String> field4() {
        return LibPublisher.LIB_PUBLISHER.ADDRESS;
    }

    @Override
    public Field<CountryCode> field5() {
        return LibPublisher.LIB_PUBLISHER.COUNTRY_CODE;
    }

    @Override
    public Field<String> field6() {
        return LibPublisher.LIB_PUBLISHER.NAME;
    }

    @Override
    public Field<String> field7() {
        return LibPublisher.LIB_PUBLISHER.ZIP_CODE;
    }

    @Override
    public Field<Long> field8() {
        return LibPublisher.LIB_PUBLISHER.CREATED_BY_ID;
    }

    @Override
    public Field<Long> field9() {
        return LibPublisher.LIB_PUBLISHER.LAST_MODIFIED_BY_ID;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public LocalDateTime component2() {
        return getCreatedTime();
    }

    @Override
    public LocalDateTime component3() {
        return getLastModifiedTime();
    }

    @Override
    public String component4() {
        return getAddress();
    }

    @Override
    public CountryCode component5() {
        return getCountryCode();
    }

    @Override
    public String component6() {
        return getName();
    }

    @Override
    public String component7() {
        return getZipCode();
    }

    @Override
    public Long component8() {
        return getCreatedById();
    }

    @Override
    public Long component9() {
        return getLastModifiedById();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public LocalDateTime value2() {
        return getCreatedTime();
    }

    @Override
    public LocalDateTime value3() {
        return getLastModifiedTime();
    }

    @Override
    public String value4() {
        return getAddress();
    }

    @Override
    public CountryCode value5() {
        return getCountryCode();
    }

    @Override
    public String value6() {
        return getName();
    }

    @Override
    public String value7() {
        return getZipCode();
    }

    @Override
    public Long value8() {
        return getCreatedById();
    }

    @Override
    public Long value9() {
        return getLastModifiedById();
    }

    @Override
    public LibPublisherRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public LibPublisherRecord value2(LocalDateTime value) {
        setCreatedTime(value);
        return this;
    }

    @Override
    public LibPublisherRecord value3(LocalDateTime value) {
        setLastModifiedTime(value);
        return this;
    }

    @Override
    public LibPublisherRecord value4(String value) {
        setAddress(value);
        return this;
    }

    @Override
    public LibPublisherRecord value5(CountryCode value) {
        setCountryCode(value);
        return this;
    }

    @Override
    public LibPublisherRecord value6(String value) {
        setName(value);
        return this;
    }

    @Override
    public LibPublisherRecord value7(String value) {
        setZipCode(value);
        return this;
    }

    @Override
    public LibPublisherRecord value8(Long value) {
        setCreatedById(value);
        return this;
    }

    @Override
    public LibPublisherRecord value9(Long value) {
        setLastModifiedById(value);
        return this;
    }

    @Override
    public LibPublisherRecord values(Long value1, LocalDateTime value2, LocalDateTime value3, String value4, CountryCode value5, String value6, String value7, Long value8, Long value9) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached LibPublisherRecord
     */
    public LibPublisherRecord() {
        super(LibPublisher.LIB_PUBLISHER);
    }

    /**
     * Create a detached, initialised LibPublisherRecord
     */
    public LibPublisherRecord(Long id, LocalDateTime createdTime, LocalDateTime lastModifiedTime, String address, CountryCode countryCode, String name, String zipCode, Long createdById, Long lastModifiedById) {
        super(LibPublisher.LIB_PUBLISHER);

        setId(id);
        setCreatedTime(createdTime);
        setLastModifiedTime(lastModifiedTime);
        setAddress(address);
        setCountryCode(countryCode);
        setName(name);
        setZipCode(zipCode);
        setCreatedById(createdById);
        setLastModifiedById(lastModifiedById);
    }
}
