package com.mercadolibre.challenge;

import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestExecutionListeners.MergeMode;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

//@formatter:off
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners(listeners = {
  CassandraUnitDependencyInjectionTestExecutionListener.class,
  DependencyInjectionTestExecutionListener.class},
  mergeMode = MergeMode.MERGE_WITH_DEFAULTS)
@EmbeddedCassandra(configuration = "embedded-cassandra.yaml",timeout = 60000)
@CassandraDataSet(value =  "create-keyspace.cql")
//@formatter:on
public abstract class AbstractCassandraSpringBootTest {

}
