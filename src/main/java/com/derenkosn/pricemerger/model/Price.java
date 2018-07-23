package com.derenkosn.pricemerger.model;

import java.util.Date;
import java.util.Objects;

public class Price {

    private long id;
    private String productCode;
    private int number;
    private int department;
    private Date begin;
    private Date end;
    private long value;

    public Price(String productCode, int number, int department, Date begin, Date end, long value) {
        this.productCode = productCode;
        this.number = number;
        this.department = department;
        this.begin = begin;
        this.end = end;
        this.value = value;
    }

    public boolean isSame(Price otherPrice) {
        return productCode.equals(otherPrice.productCode) &&
                (department == otherPrice.department) &&
                (number == otherPrice.number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return number == price.number &&
                department == price.department &&
                value == price.value &&
                Objects.equals(productCode, price.productCode) &&
                Objects.equals(begin, price.begin) &&
                Objects.equals(end, price.end);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productCode, number, department, begin, end, value);
    }

    @Override
    public String toString() {
        return "Price{" +
                "productCode='" + productCode + '\'' +
                ", number=" + number +
                ", department=" + department +
                ", begin=" + begin +
                ", end=" + end +
                ", value=" + value +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getProductCode() {
        return productCode;
    }

    public int getNumber() {
        return number;
    }

    public int getDepartment() {
        return department;
    }

    public Date getBegin() {
        return begin;
    }

    public Date getEnd() {
        return end;
    }

    public long getValue() {
        return value;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
