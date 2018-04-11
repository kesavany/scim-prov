/*
* Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
* WSO2 Inc. licenses this file to you under the Apache License,
* Version 2.0 (the "License"); you may not use this file except
* in compliance with the License.
* you may obtain a copy of the License at
*
*   http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/
package org.wso2.carbon.identity.provisioning.connector.scim2.test;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.powermock.reflect.Whitebox;
import org.testng.IObjectFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;
import org.wso2.carbon.identity.application.common.model.ClaimMapping;
import org.wso2.carbon.identity.application.common.model.Property;
import org.wso2.carbon.identity.provisioning.ProvisioningEntity;
import org.wso2.carbon.identity.provisioning.ProvisioningEntityType;
import org.wso2.carbon.identity.provisioning.ProvisioningOperation;
import org.wso2.carbon.identity.provisioning.connector.scim2.SCIM2ProvisioningConnector;
import org.wso2.carbon.identity.provisioning.connector.scim2.SCIM2ProvisioningConnectorConstants;
import org.wso2.charon3.core.utils.codeutils.PatchOperation;
import org.wso2.scim2.client.ProvisioningClient;
import org.wso2.scim2.client.SCIMProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@PrepareForTest({PatchOperation.class, SCIM2ProvisioningConnector.class, SCIMProvider.class})
public class SCIM2ProvisioningConnectorTest extends PowerMockTestCase {

    @ObjectFactory
    public IObjectFactory getObjectFactory() {

        return new org.powermock.modules.testng.PowerMockObjectFactory();
    }

    //    @Mock
//    org.wso2.scim2.client.SCIMProvider scimProvider;
//
//    @InjectMocks
    private SCIM2ProvisioningConnector sCIM2ProvisioningConnector;
    private ProvisioningClient provisioningClient;
    private SCIMProvider scimProvider;

    @BeforeMethod
    public void setUp() throws Exception {

        //initMocks(this);
        sCIM2ProvisioningConnector = new SCIM2ProvisioningConnector();
        scimProvider = Mockito.mock(SCIMProvider.class);
        provisioningClient = Mockito.mock(ProvisioningClient.class);
    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetConnectorType() throws Exception {

        Whitebox.invokeMethod(sCIM2ProvisioningConnector, "getUserStoreDomainName");
    }

    @Test
    public void testCreateUser() throws Exception {
        PowerMockito.when(scimProvider.getProperty(SCIM2ProvisioningConnectorConstants.SCIM_ENABLE_PASSWORD_PROVISIONING)).thenReturn("true");

        sCIM2ProvisioningConnector.init(new Property[0]);

        PowerMockito.whenNew(ProvisioningClient.class).withArguments(Mockito.anyObject(), Mockito.anyObject(),
                Mockito.anyObject()).thenReturn(provisioningClient);
        PowerMockito.whenNew(SCIMProvider.class).withNoArguments().thenReturn(scimProvider);
        Map<ClaimMapping, List<String>> attributes = new HashMap<ClaimMapping, List<String>>();
        List<String> value = new ArrayList<String>();
        value.add("testUser");
        attributes.put(ClaimMapping.build("org:wso2:carbon:identity:provisioning:claim:username",null,null,false),
                value);
        attributes.put(ClaimMapping.build("org:wso2:carbon:identity:provisioning:claim:password",null,null,false),
                value);
        attributes.put(ClaimMapping.build("org:wso2:carbon:identity:provisioning:claim:group",null,null,false),
                null);

        ProvisioningEntity userEntity = new ProvisioningEntity(ProvisioningEntityType.USER, ProvisioningOperation.POST,attributes);

        Whitebox.invokeMethod(sCIM2ProvisioningConnector, "createUser",userEntity);
    }
}
