package com.javabyexamples.java.tools.lombok.annotations.data;

public class EmployeeDelomboked {

    private String name;
    private int salary;

    public EmployeeDelomboked() {
    }

    public String getName() {
        return this.name;
    }

    public int getSalary() {
        return this.salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof EmployeeDelomboked)) {
            return false;
        }
        final EmployeeDelomboked other = (EmployeeDelomboked) o;
        if (!other.canEqual((Object) this)) {
            return false;
        }
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        if (this.getSalary() != other.getSalary()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        result = result * PRIME + this.getSalary();
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof EmployeeDelomboked;
    }

    public String toString() {
        return "EmployeeDelomboked(name=" + this.getName() + ", salary=" + this.getSalary() + ")";
    }
}
