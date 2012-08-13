package com.data2semantics.replication.localDaemon;

import java.io.IOException;
import org.openjena.atlas.logging.Log;
import com.data2semantics.replication.localDaemon.queries.*;

public class UpdateReplica {

	public UpdateReplica() {
	}

	

	public static void update() {
		String queryString = "";
		Query query;
		query = new Query1();
		queryString = query.getInsertQuery();
		Helper.executeUpdateQuery(Helper.ENDPOINT_REPLICA_UPDATE, queryString);
		
		query = new Query2();
		queryString = query.getInsertQuery();
		Helper.executeUpdateQuery(Helper.ENDPOINT_REPLICA_UPDATE, queryString);
		
		query = new Query3();
		queryString = query.getInsertQuery();
		Helper.executeUpdateQuery(Helper.ENDPOINT_REPLICA_UPDATE, queryString);
		
		query = new Query4();
		queryString = query.getInsertQuery();
		Helper.executeUpdateQuery(Helper.ENDPOINT_REPLICA_UPDATE, queryString);
		
		query = new Query5a();
		queryString = query.getInsertQuery();
		Helper.executeUpdateQuery(Helper.ENDPOINT_REPLICA_UPDATE, queryString);
		query = new Query5b();
		queryString = query.getInsertQuery();
		Helper.executeUpdateQuery(Helper.ENDPOINT_REPLICA_UPDATE, queryString);
		
		query = new Query6();
		queryString = query.getInsertQuery();
		Helper.executeUpdateQuery(Helper.ENDPOINT_REPLICA_UPDATE, queryString);
		
		query = new Query7();
		queryString = query.getInsertQuery();
		Helper.executeUpdateQuery(Helper.ENDPOINT_REPLICA_UPDATE, queryString);
		
		query = new Query8();
		queryString = query.getInsertQuery();
		Helper.executeUpdateQuery(Helper.ENDPOINT_REPLICA_UPDATE, queryString);
	}



	public static void main(String[] args) throws IOException, InterruptedException {
		Log.setLog4j();
		UpdateReplica.update();
	}
}
