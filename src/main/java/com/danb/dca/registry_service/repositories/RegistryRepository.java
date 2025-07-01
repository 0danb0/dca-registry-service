package com.danb.dca.registry_service.repositories;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.danb.dca.registry_service.enums.DomainMsg;
import com.danb.dca.registry_service.enums.ErrorMsg;
import com.danb.dca.registry_service.exceptions.RegistryException;
import com.danb.dca.registry_service.models.po.RegistryPO;
import com.danb.dca.registry_service.properties.DynamoDBProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public boolean isRegistryDtoPresentByPk(String pk) throws RegistryException {
        DynamoDBQueryExpression<RegistryPO> query = new DynamoDBQueryExpression<RegistryPO>()
                .withHashKeyValues(
                        RegistryPO.builder().pk(pk).build()
                );

        List<RegistryPO> results = dynamoDBMapper.query(RegistryPO.class, query);

        checkRegistryResultsEmptyAndActive(results);

        return true;
    }

    public void insert(RegistryPO invoicePO) {
        dynamoDBMapper.save(invoicePO);
    }

    public void delete(RegistryPO invoicePO) {
        dynamoDBMapper.delete(invoicePO);
    }

    private static void checkRegistryResultsEmptyAndActive(List<RegistryPO> results) throws RegistryException {
        if(results.isEmpty()) {
            throw new RegistryException(
                    ErrorMsg.DCA_RGT_SRV_02.getCode(),
                    ErrorMsg.DCA_RGT_SRV_02.getMessage(),
                    DomainMsg.REGISTRY_SERVICE_TECHNICAL.getName(),
                    ErrorMsg.DCA_RGT_SRV_02.getCode()
            );
        }

        if(!Boolean.getBoolean(results.get(0).getActive())){
            throw new RegistryException(
                    ErrorMsg.DCA_RGT_SRV_03.getCode(),
                    ErrorMsg.DCA_RGT_SRV_03.getMessage(),
                    DomainMsg.REGISTRY_SERVICE_TECHNICAL.getName(),
                    ErrorMsg.DCA_RGT_SRV_03.getCode()
            );
        }
    }

}
