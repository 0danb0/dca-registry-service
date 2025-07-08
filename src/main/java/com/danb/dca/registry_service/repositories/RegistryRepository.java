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
import com.danb.dca.registry_service.utils.Tools;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RegistryRepository {

    private final Tools tools;
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

    public boolean canLogin(String pk) throws RegistryException {
        DynamoDBQueryExpression<RegistryPO> query = new DynamoDBQueryExpression<RegistryPO>()
                .withHashKeyValues(
                        RegistryPO.builder().pk(pk).build()
                );

        List<RegistryPO> results = dynamoDBMapper.query(RegistryPO.class, query);
        checkRegistryResultsIsEmpty(results);

        RegistryPO registryPO = results.get(0);
        checkRegistryResultIsActive(registryPO);

        return true;
    }

    public RegistryPO checkUserIsPresentAndRetrieve(String pk, String sk) throws RegistryException  {
        RegistryPO registryPO = dynamoDBMapper.load(RegistryPO.class, pk, sk);

        if(!registryPO.isEmpty()){
            throw new RegistryException(
                    ErrorMsg.DCA_RGT_SRV_05.getCode(),
                    ErrorMsg.DCA_RGT_SRV_05.getMessage(),
                    DomainMsg.REGISTRY_SERVICE_TECHNICAL.getName(),
                    ErrorMsg.DCA_RGT_SRV_05.getCode()
            );

        }else{
            return registryPO;
        }
    }

    public void checkUserIsPresent(String pk, String sk) throws RegistryException  {
        RegistryPO registryPO = dynamoDBMapper.load(RegistryPO.class, pk, sk);

        if(registryPO != null && !registryPO.isEmpty()){
            throw new RegistryException(
                    ErrorMsg.DCA_RGT_SRV_05.getCode(),
                    ErrorMsg.DCA_RGT_SRV_05.getMessage(),
                    DomainMsg.REGISTRY_SERVICE_TECHNICAL.getName(),
                    ErrorMsg.DCA_RGT_SRV_05.getCode()
            );

        }
    }

    public void updateLastAccessDate(String pk){
        DynamoDBQueryExpression<RegistryPO> query = new DynamoDBQueryExpression<RegistryPO>()
                .withHashKeyValues(
                        RegistryPO.builder().pk(pk).build()
                );

        List<RegistryPO> results = dynamoDBMapper.query(RegistryPO.class, query);

        RegistryPO registryPO = results.get(0);
        delete(registryPO);

        registryPO.setLastAccessDate(tools.getInstant());
        insert(registryPO);
    }

    public void insert(RegistryPO registryPO) {
        dynamoDBMapper.save(registryPO);
    }

    public void delete(RegistryPO registryPO) {
        dynamoDBMapper.delete(registryPO);
    }

    private static void checkRegistryResultIsActive(RegistryPO registryPO ) throws RegistryException {
        if(!Boolean.getBoolean(registryPO.getActive())){
            throw new RegistryException(
                    ErrorMsg.DCA_RGT_SRV_03.getCode(),
                    ErrorMsg.DCA_RGT_SRV_03.getMessage(),
                    DomainMsg.REGISTRY_SERVICE_TECHNICAL.getName(),
                    ErrorMsg.DCA_RGT_SRV_03.getCode()
            );
        }
    }

    private static void checkRegistryResultsIsEmpty(List<RegistryPO> results) throws RegistryException {
        if(results.isEmpty()) {
            throw new RegistryException(
                    ErrorMsg.DCA_RGT_SRV_02.getCode(),
                    ErrorMsg.DCA_RGT_SRV_02.getMessage(),
                    DomainMsg.REGISTRY_SERVICE_TECHNICAL.getName(),
                    ErrorMsg.DCA_RGT_SRV_02.getCode()
            );
        }

    }

}
