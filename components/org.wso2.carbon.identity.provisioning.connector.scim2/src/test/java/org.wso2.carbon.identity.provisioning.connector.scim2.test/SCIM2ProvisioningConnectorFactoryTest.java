/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wso2.carbon.identity.provisioning.connector.scim2.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wso2.carbon.identity.application.common.model.Property;
import org.wso2.carbon.identity.provisioning.connector.scim2.SCIM2ProvisioningConnectorFactory;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.MockitoAnnotations.initMocks;

public class SCIM2ProvisioningConnectorFactoryTest {

    private SCIM2ProvisioningConnectorFactory scim2ProvisioningConnectorFactory;

    @BeforeMethod
    public void setUp() throws Exception {

        scim2ProvisioningConnectorFactory = new SCIM2ProvisioningConnectorFactory();
        initMocks(this);
    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetConnectorType() {

        Assert.assertEquals(scim2ProvisioningConnectorFactory.getConnectorType(),
                scim2ProvisioningConnectorFactory.SCIM2);
    }

    @Test
    public void testGetConfigurationProperties() {

        List<Property> configProperties = new ArrayList<Property>();
        Property username = new Property();
        configProperties.add(username);
        Property userPassword = new Property();
        configProperties.add(userPassword);
        Property userEndpoint = new Property();
        configProperties.add(userEndpoint);
        Property groupEndpoint = new Property();
        configProperties.add(groupEndpoint);
        Property userStoreDomain = new Property();
        configProperties.add(userStoreDomain);
        Property passwordProvisioning = new Property();
        configProperties.add(passwordProvisioning);
        Property defaultPassword = new Property();
        configProperties.add(defaultPassword);
        Assert.assertEquals(configProperties.size(), scim2ProvisioningConnectorFactory.getConfigurationProperties().
                size());
    }

//    @Test
//    public void testBuildConnector() throws IdentityProvisioningException {
//        List<Property> configProperties = new ArrayList<Property>();
//        Property username = new Property();
//        configProperties.add(username);
//        Property userPassword = new Property();
//        configProperties.add(userPassword);
//        Property userEndpoint = new Property();
//        configProperties.add(userEndpoint);
//        Property groupEndpoint = new Property();
//        configProperties.add(groupEndpoint);
//        Property userStoreDomain = new Property();
//        configProperties.add(userStoreDomain);
//        Property passwordProvisioning = new Property();
//        configProperties.add(passwordProvisioning);
//        Property defaultPassword = new Property();
//        configProperties.add(defaultPassword);
//
//        Property[] proProperties = configProperties.toArray(new Property[configProperties.size()]);
//
//        AbstractOutboundProvisioningConnector scimProvisioningConnector = new SCIM2ProvisioningConnector();
//        scimProvisioningConnector.init(proProperties);
//
//        Assert.assertEquals(scimProvisioningConnector, scim2ProvisioningConnectorFactory.getConnector("test", proProperties,"-1234"));
//    }
}
