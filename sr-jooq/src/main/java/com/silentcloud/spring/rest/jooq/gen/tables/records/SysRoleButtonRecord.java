/*
 * This file is generated by jOOQ.
 */
package com.silentcloud.spring.rest.jooq.gen.tables.records;


import com.silentcloud.spring.rest.jooq.gen.tables.SysRoleButton;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SysRoleButtonRecord extends TableRecordImpl<SysRoleButtonRecord> implements Record2<Long, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>SYS_ROLE_BUTTON.ROLE_ID</code>.
     */
    public void setRoleId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>SYS_ROLE_BUTTON.ROLE_ID</code>.
     */
    public Long getRoleId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>SYS_ROLE_BUTTON.BUTTON_ID</code>.
     */
    public void setButtonId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>SYS_ROLE_BUTTON.BUTTON_ID</code>.
     */
    public Long getButtonId() {
        return (Long) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Long, Long> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return SysRoleButton.SYS_ROLE_BUTTON.ROLE_ID;
    }

    @Override
    public Field<Long> field2() {
        return SysRoleButton.SYS_ROLE_BUTTON.BUTTON_ID;
    }

    @Override
    public Long component1() {
        return getRoleId();
    }

    @Override
    public Long component2() {
        return getButtonId();
    }

    @Override
    public Long value1() {
        return getRoleId();
    }

    @Override
    public Long value2() {
        return getButtonId();
    }

    @Override
    public SysRoleButtonRecord value1(Long value) {
        setRoleId(value);
        return this;
    }

    @Override
    public SysRoleButtonRecord value2(Long value) {
        setButtonId(value);
        return this;
    }

    @Override
    public SysRoleButtonRecord values(Long value1, Long value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SysRoleButtonRecord
     */
    public SysRoleButtonRecord() {
        super(SysRoleButton.SYS_ROLE_BUTTON);
    }

    /**
     * Create a detached, initialised SysRoleButtonRecord
     */
    public SysRoleButtonRecord(Long roleId, Long buttonId) {
        super(SysRoleButton.SYS_ROLE_BUTTON);

        setRoleId(roleId);
        setButtonId(buttonId);
    }
}
