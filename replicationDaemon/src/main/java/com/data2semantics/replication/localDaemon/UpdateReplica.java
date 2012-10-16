package com.data2semantics.replication.localDaemon;

import java.io.IOException;

import org.openjena.atlas.logging.Log;
import com.data2semantics.replication.localDaemon.queries.*;

public class UpdateReplica {


	public static void update(Query query) {
		String queryString = query.getInsertQuery();
		Helper.executeUpdateQuery(Helper.ENDPOINT_REPLICA_UPDATE, queryString);
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		Log.setLog4j();
		UpdateReplica.update(new Query1());
		UpdateReplica.update(new Query2());
		UpdateReplica.update(new Query3());
		UpdateReplica.update(new Query4());
		UpdateReplica.update(new Query5a());
		UpdateReplica.update(new Query5b());
		UpdateReplica.update(new Query6());
		UpdateReplica.update(new Query7());
		UpdateReplica.update(new Query8());
	}
}
