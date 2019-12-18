package com.lx.core.module.web.dto;


import java.io.Serializable;

public abstract class BaseDTO<T> implements Serializable {
    public abstract <T> T convert(Serializable id);

    public BaseDTO() {
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BaseDTO)) {
            return false;
        } else {
            BaseDTO<?> other = (BaseDTO)o;
            return other.canEqual(this);
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BaseDTO;
    }

    public int hashCode() {
        int result = 1;
        return result;
    }

    public String toString() {
        return "BaseDTO()";
    }
}
