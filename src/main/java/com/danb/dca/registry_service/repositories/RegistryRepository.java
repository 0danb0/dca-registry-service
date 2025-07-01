package com.danb.dca.registry_service.repositories;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.danb.dca.registry_service.models.po.RegistryPO;
import com.danb.dca.registry_service.properties.DynamoDBProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RegistryRepository {

    private final AmazonDynamoDB client;
    private final DynamoDBProperties dynamoDBProperties;
    private DynamoDBMapper dynamoDBMapper;

    @PostConstruct
    public void init() {
        DynamoDBMapperConfig mapperConfig = new DynamoDBMapperConfig.Builder()
                .withTableNameOverride(
                        DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement(dynamoDBProperties.getTable())
                ).build();

        this.dynamoDBMapper = new DynamoDBMapper(client, mapperConfig);
        log.info("DynamoDBMapper initialized for table: {}", dynamoDBProperties.getTable());
    }

    public void insert(RegistryPO invoicePO) {
        dynamoDBMapper.save(invoicePO);
    }

    public void delete(RegistryPO invoicePO) {
        dynamoDBMapper.delete(invoicePO);
    }
}
