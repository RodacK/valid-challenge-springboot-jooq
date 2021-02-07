package com.valid.challenge.controller;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record4;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.valid.challenge.jooq.model.tables.User;
import com.valid.challenge.model.ProcessRequest;

public class UserRepository {
	
	@Autowired
	private DSLContext dsl;
	
	private User userTable = User.USER;
	
	
	public void saveUser(com.valid.challenge.jooq.model.tables.pojos.User user) {
		dsl.insertInto(userTable)
		.set(userTable.NAME,user.getName())
		.set(userTable.LAST_NAME,user.getLastName())
		.set(userTable.PROCESS,user.getProcess())
		.execute();
	}
	
	public void processUser(ProcessRequest request) {	
		dsl.batch(request.getToProcess()).execute();
	}
	
	public List<com.valid.challenge.jooq.model.tables.pojos.User> getUsers() {
		Result<Record4<Integer, String, String, Boolean>> result =  dsl
				.select(userTable.ID, userTable.NAME, userTable.LAST_NAME, userTable.PROCESS)
				.from(userTable).fetch();
		return result.into(com.valid.challenge.jooq.model.tables.pojos.User.class);
	}

}
