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

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wso2.carbon.identity.application.common.model.ClaimMapping;
import org.wso2.carbon.identity.provisioning.ProvisioningEntity;
import org.wso2.carbon.identity.provisioning.ProvisioningEntityType;
import org.wso2.carbon.identity.provisioning.ProvisioningOperation;
import org.wso2.carbon.identity.provisioning.connector.scim2.SCIM2ProvisioningConnector;
import org.wso2.carbon.identity.provisioning.connector.scim2.SCIM2ProvisioningConnectorConstants;
import org.wso2.charon3.core.utils.codeutils.PatchOperation;
import org.wso2.scim2.client.SCIMProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ PatchOperation.class, org.wso2.scim2.client.SCIMProvider.class, SCIM2ProvisioningConnector.class})
public class SCIM2ProvisioningConnectorTest {

//    @Mock
//    org.wso2.scim2.client.SCIMProvider scimProvider;

    @InjectMocks
    SCIM2ProvisioningConnector sCIM2ProvisioningConnector;

    @BeforeMethod
    public void setUp() throws Exception {

        //initMocks(this);
        sCIM2ProvisioningConnector = new SCIM2ProvisioningConnector();
    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetConnectorType() throws Exception {

//        Assert.assertEquals(sCIM2ProvisioningConnector.getUserStoreDomainName(),
//                sCIM2ProvisioningConnector.);
        Whitebox.invokeMethod(sCIM2ProvisioningConnector, "getUserStoreDomainName");
    }

    @Test
    public void testCreateUser() throws Exception {

        //SCIMProvider scimProvider = Mockito.mock(SCIMProvider.class);
        //mockStatic(SCIMProvider.class);
//        scimProvider.setProperty(SCIM2ProvisioningConnectorConstants.SCIM_ENABLE_PASSWORD_PROVISIONING,"true");
        //SCIMProvider scimProvider = PowerMockito.mock(SCIMProvider.class);
        //PowerMockito.whenNew(SCIMProvider.class).withNoArguments().thenReturn(scimProvider);
        //SCIMProvider scimProvider = new SCIMProvider();
        //scimProvider.setProperty(SCIM2ProvisioningConnectorConstants.SCIM_ENABLE_PASSWORD_PROVISIONING,"true");
        //PowerMockito.mockStatic(org.wso2.scim2.client.SCIMProvider.class);
//        mockStatic(PatchOperation.class);
//        PowerMockito.whenNew(PatchOperation.class).withNoArguments().thenReturn(patchOperation);

        //mockStatic(org.wso2.scim2.client.SCIMProvider.class);

        //PowerMockito.whenNew(org.wso2.scim2.client.SCIMProvider.class).withNoArguments().thenReturn(scimProvider);
        SCIMProvider scimProvider = Mockito.mock(SCIMProvider.class);
        //scimProvider.setProperty(SCIM2ProvisioningConnectorConstants.SCIM_ENABLE_PASSWORD_PROVISIONING,"true");
        PowerMockito.whenNew(SCIMProvider.class).withNoArguments().thenReturn(scimProvider);
        PowerMockito.when(scimProvider.getProperty(SCIM2ProvisioningConnectorConstants.SCIM_ENABLE_PASSWORD_PROVISIONING)).thenReturn("true");

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
