package com.javabyexamples.java.tools.lombok.annotations.data.staticconstructor;

public class EmployeeDelomboked {

    private String name;
    private int salary;

    private EmployeeDelomboked() {
    }

    public static EmployeeDelomboked of() {
        return new EmployeeDelomboked();
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

    public boolean equals(final Object o) {
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

    protected boolean canEqual(final Object other) {
        return other instanceof EmployeeDelomboked;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        result = result * PRIME + this.getSalary();
        return result;
    }

    public String toString() {
        return "EmployeeDelomboked(name=" + this.getName() + ", salary=" + this.getSalary() + ")";
    }
}

