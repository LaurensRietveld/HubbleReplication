package com.data2semantics.replication.localDaemon.queries;


public interface Query {

	public String getInsertQuery();
	
	public String getSelectAllQuery();
	
	public String getSelectExampleQuery();

	public String getConstructQuery();

}
