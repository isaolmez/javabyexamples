package com.javabyexamples.java.tools.lombok.annotations.equalsandhashcode.include.fieldlevel;

public class EmployeeDelomboked {

    private String name;
    private int age;
    private int salary;

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
        final Object this$name = this.name;
        final Object other$name = other.name;
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        if (this.age != other.age) {
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
        final Object $name = this.name;
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        result = result * PRIME + this.age;
        return result;
    }
}
