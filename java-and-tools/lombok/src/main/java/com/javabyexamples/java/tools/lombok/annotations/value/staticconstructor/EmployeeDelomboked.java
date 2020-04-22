package com.javabyexamples.java.tools.lombok.annotations.value.staticconstructor;

public final class EmployeeDelomboked {

    private final String name;
    private final int salary;

    private EmployeeDelomboked(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public static EmployeeDelomboked of(String name, int salary) {
        return new EmployeeDelomboked(name, salary);
    }

    public String getName() {
        return this.name;
    }

    public int getSalary() {
        return this.salary;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof EmployeeDelomboked)) {
            return false;
        }
        final EmployeeDelomboked other = (EmployeeDelomboked) o;
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

    public String toString() {
        return "Employee(name=" + this.getName() + ", salary=" + this.getSalary() + ")";
    }
}
