SparqlReplication
=================

SparqlProxy: Generic tool. Chooses to execute query on remote triplestore or on local replica. Decision making might be extensive (e.g. on resource bounds), but for now just checks connectivity
ReplicationDaemon: Uses queries from Hubble, and rewrites as select/insert, so we can replicate only the needed data locally