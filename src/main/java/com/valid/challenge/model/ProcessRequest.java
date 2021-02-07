package com.valid.challenge.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.jooq.Query;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.valid.challenge.model.convert.ListIntegerToListQuery;

public class ProcessRequest {

	@NotNull
	@NotEmpty
	@JsonDeserialize(using = ListIntegerToListQuery.class)
	private List<Query> toProcess;

	public List<Query> getToProcess() {
		return toProcess;
	}

	public void setToProcess(List<Query> toProcess) {
		this.toProcess = toProcess;
	}

}
