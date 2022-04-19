/*
 * Copyright 2008-2022 the original author or authors.
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

import org.springframework.data.jpa.provider.QueryExtractor;
import org.springframework.data.jpa.repository.query.*;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryMethod;

import java.lang.reflect.Method;

/**
 * JPA specific extension of {@link QueryMethod}.
 *
 * @author Fahd Arhalai
 */
public class AccessControlJpaQueryMethod extends JpaQueryMethod {


	/**
	 * Creates a {@link JpaQueryMethod}.
	 *
	 * @param method    must not be {@literal null}
	 * @param metadata  must not be {@literal null}
	 * @param factory   must not be {@literal null}
	 * @param extractor must not be {@literal null}
	 */
	protected AccessControlJpaQueryMethod(Method method, RepositoryMetadata metadata, ProjectionFactory factory, QueryExtractor extractor) {
		super(method, metadata, factory, extractor);
	}
}
