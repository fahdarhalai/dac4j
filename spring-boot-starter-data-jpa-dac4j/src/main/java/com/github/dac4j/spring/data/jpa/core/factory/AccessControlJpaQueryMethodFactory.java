/*
 * Copyright 2019-2022 the original author or authors.
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
import org.springframework.data.jpa.repository.query.JpaQueryMethod;
import org.springframework.data.jpa.repository.query.JpaQueryMethodFactory;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.util.Assert;

import java.lang.reflect.Method;

/**
 * A factory for creating {@link JpaQueryMethod} instances.
 *
 * @author Fahd Arhalai
 */
public class AccessControlJpaQueryMethodFactory implements JpaQueryMethodFactory {

	private final QueryExtractor extractor;

	public AccessControlJpaQueryMethodFactory(QueryExtractor extractor) {

		Assert.notNull(extractor, "QueryExtractor must not be null");

		this.extractor = extractor;
	}

	@Override
	public JpaQueryMethod build(Method method, RepositoryMetadata metadata, ProjectionFactory factory) {
		return new AccessControlJpaQueryMethod(method, metadata, factory, extractor);
	}
}
