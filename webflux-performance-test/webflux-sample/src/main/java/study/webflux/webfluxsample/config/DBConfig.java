//package study.webflux.webfluxsample.config;
//
//import io.r2dbc.spi.ConnectionFactories;
//import io.r2dbc.spi.ConnectionFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.data.convert.CustomConversions;
//import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
//import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
//import org.springframework.data.r2dbc.dialect.DialectResolver;
//import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
//import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
//import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
//import org.springframework.r2dbc.core.DatabaseClient;
//
//import java.util.ArrayList;
//import java.util.List;
//
////@Configuration
////public class DBConfig {
////
//////    @Override
//////    public ConnectionFactory connectionFactory() {
//////        return ConnectionFactories.get("r2dbc:mysql://localhost:3306/school-reactive-db");
//////    }
////
//////    @Bean
//////    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
//////        var initializer = new ConnectionFactoryInitializer();
//////        initializer.setConnectionFactory(connectionFactory);
//////
//////        var databasePopulator = new CompositeDatabasePopulator();
//////        databasePopulator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("db/schema.sql")));
//////        initializer.setDatabasePopulator(databasePopulator);
//////
//////        return initializer;
//////    }
////}
//
//@Configuration
//public class DBConfig {
//
//    @Bean
//    public R2dbcCustomConversions r2dbcCustomConversions(DatabaseClient databaseClient) {
//        var dialect = DialectResolver.getDialect(databaseClient.getConnectionFactory());
//        var converters = new ArrayList<>(dialect.getConverters());
//        converters.addAll(R2dbcCustomConversions.STORE_CONVERTERS);
//
//        return new R2dbcCustomConversions(
//                CustomConversions.StoreConversions.of(dialect.getSimpleTypeHolder(), converters),
//                getCustomConverters());
//    }
//
//    private List<Object> getCustomConverters() {
//        return List.of();
//    }
//
//}