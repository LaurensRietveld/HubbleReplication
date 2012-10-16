package com.data2semantics.replication.localDaemon;

import com.data2semantics.replication.localDaemon.queries.Query;
import com.data2semantics.replication.localDaemon.queries.Query2;

public class LocalDaemon {

	public LocalDaemon() {
	}


	public static void main(String[] args)  {
		Query query = new Query2();
		System.out.println(query.getSelectAllQuery());
	}
}
