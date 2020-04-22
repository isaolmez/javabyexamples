package com.javabyexamples.java.tools.lombok.annotations.equalsandhashcode.basic;

public class EmployeeDelomboked {

    private String name;
    private int salary;

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
        final Object this$name = this.name;
        final Object other$name = other.name;
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        if (this.salary != other.salary) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.name;
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        result = result * PRIME + this.salary;
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof EmployeeDelomboked;
    }
}
