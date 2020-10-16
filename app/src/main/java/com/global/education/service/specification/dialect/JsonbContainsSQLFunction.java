package com.global.education.service.specification.dialect;

import java.util.List;

import org.hibernate.QueryException;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.engine.spi.Mapping;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.BooleanType;
import org.hibernate.type.Type;


/**
 * Custom function for PostgreSQL DB. Uses for check available of the key in top level.
 * Example: WHERE column @> '{"key": {}}'
 * If "key" is exist inside of the "column" - function will return TRUE.
 */
public class JsonbContainsSQLFunction implements SQLFunction {

	@Override
	public boolean hasArguments() {
		return false;
	}

	@Override
	public boolean hasParenthesesIfNoArguments() {
		return false;
	}

	@Override
	public Type getReturnType(Type type, Mapping mapping) throws QueryException {
		return new BooleanType();
	}

	@Override
	public String render(Type type, List list, SessionFactoryImplementor sessionFactoryImplementor)
			throws QueryException {
		Object field = list.get(0);
		Object value = list.get(1);

		return field + " @> '{\"" + value + "\": {}}'";
	}
}
