package com.valid.challenge.model.convert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.valid.challenge.jooq.model.tables.User;

public class ListIntegerToListQuery extends JsonDeserializer<List<Query>>{
	
	@Autowired
	private DSLContext dsl;
	
	private User userTable = User.USER;

	public ListIntegerToListQuery() {}

	@SuppressWarnings("unchecked")
	@Override
	public List<Query> deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		List<Query> queryList = new ArrayList<>();
		p.readValueAs(List.class).forEach(i -> {
			Query aux = dsl.update(userTable)
					.set(userTable.PROCESS,true)
					.where(userTable.ID.equal((Integer)i));
			queryList.add(aux);
		});
		return queryList;
	}



}
