package com.mercadolibre.challenge.test.utilities;

import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestExecutionListeners.MergeMode;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners(listeners = { CassandraUnitDependencyInjectionTestExecutionListener.class }, mergeMode = MergeMode.MERGE_WITH_DEFAULTS)
@EmbeddedCassandra(configuration = "embedded-cassandra.yaml", timeout = 60000)
@CassandraDataSet(value = "create-keyspace.cql")
public abstract class AbstractCassandraSpringBootTest {

	@Value("${spring.data.cassandra.keyspace-name}")
	private String keyspace;

	@AfterEach
	public void relase() {
		EmbeddedCassandraServerHelper.cleanDataEmbeddedCassandra(keyspace);
	}

}
