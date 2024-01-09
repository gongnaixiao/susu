package com.gongnaixiao.susu.multiDataSource.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.jdbc.core.convert.*;
import org.springframework.data.jdbc.core.dialect.JdbcArrayColumns;
import org.springframework.data.jdbc.core.dialect.JdbcDialect;
import org.springframework.data.jdbc.core.mapping.JdbcMappingContext;
import org.springframework.data.jdbc.core.mapping.JdbcSimpleTypes;
import org.springframework.data.jdbc.repository.QueryMappingConfiguration;
import org.springframework.data.jdbc.repository.config.DialectResolver;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jdbc.repository.support.JdbcRepositoryFactory;
import org.springframework.data.mapping.callback.EntityCallbacks;
import org.springframework.data.mapping.model.SimpleTypeHolder;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.core.support.TransactionalRepositoryFactoryBeanSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableJdbcRepositories(basePackages = "com.gongnaixiao.susu.multiDataSource.repository.mysql",
        repositoryFactoryBeanClass = MysqlDataSourceConfig.MysqlRepositoryFactoryBean.class
)
public class MysqlDataSourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.mysql")
    public DataSourceProperties mysqlDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource mysqlDataSource(@Qualifier("mysqlDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    static class MysqlRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable>
            extends TransactionalRepositoryFactoryBeanSupport<T, S, ID>
            implements ApplicationEventPublisherAware {
        private ApplicationEventPublisher publisher;
        private BeanFactory beanFactory;
        private RelationalMappingContext mappingContext;
        private JdbcConverter converter;
        private DataAccessStrategy dataAccessStrategy;
        private QueryMappingConfiguration queryMappingConfiguration = QueryMappingConfiguration.EMPTY;
        private NamedParameterJdbcOperations operations;
        private EntityCallbacks entityCallbacks;
        private Dialect dialect;
        private RelationResolver relationResolver;

        protected MysqlRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
            super(repositoryInterface);
        }

        @Override
        protected RepositoryFactorySupport doCreateRepositoryFactory() {
            JdbcRepositoryFactory jdbcRepositoryFactory = new JdbcRepositoryFactory(dataAccessStrategy, mappingContext, converter, dialect, publisher, operations);
            jdbcRepositoryFactory.setQueryMappingConfiguration(queryMappingConfiguration);
            jdbcRepositoryFactory.setEntityCallbacks(entityCallbacks);
            jdbcRepositoryFactory.setBeanFactory(beanFactory);

            return jdbcRepositoryFactory;
        }

        @Override
        public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {

            super.setApplicationEventPublisher(publisher);

            this.publisher = publisher;
        }

        @Override
        public void setBeanFactory(BeanFactory beanFactory) {

            super.setBeanFactory(beanFactory);

            this.beanFactory = beanFactory;
        }


        @Override
        public void afterPropertiesSet() {
            this.dialect = DialectResolver.getDialect(operations.getJdbcOperations());

            SimpleTypeHolder simpleTypeHolder = dialect.simpleTypes().isEmpty() ? JdbcSimpleTypes.HOLDER : new SimpleTypeHolder(dialect.simpleTypes(), JdbcSimpleTypes.HOLDER);

            JdbcCustomConversions jdbcCustomConversions = new JdbcCustomConversions(CustomConversions.StoreConversions.of(simpleTypeHolder, storeConverters(dialect)), userConverters());
            this.mappingContext = new JdbcMappingContext(NamingStrategy.INSTANCE);

            JdbcArrayColumns arrayColumns = dialect instanceof JdbcDialect ? ((JdbcDialect) dialect).getArraySupport() : JdbcArrayColumns.DefaultSupport.INSTANCE;
            DefaultJdbcTypeFactory jdbcTypeFactory = new DefaultJdbcTypeFactory(operations.getJdbcOperations(), arrayColumns);

            this.converter = new BasicJdbcConverter(mappingContext, relationResolver, jdbcCustomConversions, jdbcTypeFactory, dialect.getIdentifierProcessing());

            this.dataAccessStrategy = new DefaultDataAccessStrategy(new SqlGeneratorSource(mappingContext, converter, dialect), mappingContext,
                    converter, operations, new SqlParametersFactory(mappingContext, converter, dialect),
                    new InsertStrategyFactory(operations, new BatchJdbcOperations(operations.getJdbcOperations()), dialect));
            if (this.queryMappingConfiguration == null) {
                this.queryMappingConfiguration = QueryMappingConfiguration.EMPTY;
            }

            if (beanFactory != null) {
                entityCallbacks = EntityCallbacks.create(beanFactory);
            }

            super.afterPropertiesSet();
        }

        @Autowired
        private void setOperations(@Qualifier("mysqlDataSource") DataSource dataSource) {
            Assert.notNull(dataSource, "DataSource must not be null!");
            this.operations = new NamedParameterJdbcTemplate(dataSource);
        }

        @Autowired
        private void setRelationResolver(@Lazy RelationResolver relationResolver) {
            this.relationResolver = relationResolver;
        }

        private List<Object> storeConverters(Dialect dialect) {
            List<Object> converters = new ArrayList<>();
            converters.addAll(dialect.getConverters());
            converters.addAll(JdbcCustomConversions.storeConverters());
            return converters;
        }

        protected List<?> userConverters() {
            return Collections.emptyList();
        }
    }
}
