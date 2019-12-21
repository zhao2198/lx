
package com.lx.common.entity;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

@Data
public abstract class AbstractEntity implements Serializable {




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
