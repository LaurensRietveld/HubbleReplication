package com.data2semantics.replication.localDaemon;

import java.io.IOException;
import org.openjena.atlas.logging.Log;
import com.data2semantics.replication.localDaemon.queries.*;

public class UpdateReplica {

	public UpdateReplica() {
	}

	

	public static void update() {
		String queryString = "";
		
		
		queryString = Query1.getInsertQuery();
		Helper.executeUpdateQuery(Helper.ENDPOINT_REPLICA_UPDATE, queryString);
		
		queryString = Query2.getInsertQuery();
		Helper.executeUpdateQuery(Helper.ENDPOINT_REPLICA_UPDATE, queryString);
		
		queryString = Query3.getInsertQuery();
		Helper.executeUpdateQuery(Helper.ENDPOINT_REPLICA_UPDATE, queryString);
		
		queryString = Query4.getInsertQuery();
		Helper.executeUpdateQuery(Helper.ENDPOINT_REPLICA_UPDATE, queryString);
		
		queryString = Query5a.getInsertQuery();
		Helper.executeUpdateQuery(Helper.ENDPOINT_REPLICA_UPDATE, queryString);
		queryString = Query5b.getInsertQuery();
		Helper.executeUpdateQuery(Helper.ENDPOINT_REPLICA_UPDATE, queryString);
		
		queryString = Query6.getInsertQuery();
		Helper.executeUpdateQuery(Helper.ENDPOINT_REPLICA_UPDATE, queryString);
		
		queryString = Query7.getInsertQuery();
		Helper.executeUpdateQuery(Helper.ENDPOINT_REPLICA_UPDATE, queryString);
		
		queryString = Query8.getInsertQuery();
		Helper.executeUpdateQuery(Helper.ENDPOINT_REPLICA_UPDATE, queryString);
	}



	public static void main(String[] args) throws IOException, InterruptedException {
		Log.setLog4j();
		UpdateReplica.update();
	}
}
