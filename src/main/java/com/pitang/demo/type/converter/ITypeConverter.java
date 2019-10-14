package com.pitang.demo.type.converter;

import java.util.List;

public interface ITypeConverter<TypeClass, EntityClass> {
	
	TypeClass convertToType(EntityClass entityClass);

	EntityClass convertToEntity(TypeClass typeClass);
	
	List<TypeClass> convertToType(List<EntityClass> entityClasses);

	List<EntityClass> convertToEntity(List<TypeClass> typeClasses);
}
