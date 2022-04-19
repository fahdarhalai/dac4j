/*
 * Copyright 2021-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.dac4j.spring.data.jpa.core.factory;

import org.springframework.lang.Nullable;

import javax.persistence.ParameterMode;
import java.util.Objects;

/**
 * This class represents a Stored Procedure Parameter and an instance of the annotation
 * {@link javax.persistence.StoredProcedureParameter}.
 *
 * @author Fahd Arhalai
 */
class AccessControlProcedureParameter {

	private final String name;
	private final ParameterMode mode;
	private final Class<?> type;

	AccessControlProcedureParameter(@Nullable String name, ParameterMode mode, Class<?> type) {

		this.name = name;
		this.mode = mode;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public ParameterMode getMode() {
		return mode;
	}

	public Class<?> getType() {
		return type;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}

		if (!(o instanceof AccessControlProcedureParameter)) {
			return false;
		}

		AccessControlProcedureParameter that = (AccessControlProcedureParameter) o;
		return Objects.equals(name, that.name) && mode == that.mode && Objects.equals(type, that.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, mode, type);
	}

	@Override
	public String toString() {
		return "ProcedureParameter{" + "name='" + name + '\'' + ", mode=" + mode + ", type=" + type + '}';
	}
}
