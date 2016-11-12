package com.wso2.carbon.custom.user.store.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.CarbonConstants;
import org.wso2.carbon.user.api.Properties;
import org.wso2.carbon.user.api.Property;
import org.wso2.carbon.user.core.UserCoreConstants;
import org.wso2.carbon.user.core.UserRealm;
import org.wso2.carbon.user.core.UserStoreException;
import org.wso2.carbon.user.core.claim.ClaimManager;
import org.wso2.carbon.user.core.jdbc.JDBCRealmConstants;
import org.wso2.carbon.user.core.jdbc.JDBCUserStoreManager;
import org.wso2.carbon.user.core.profile.ProfileConfigurationManager;
import org.wso2.carbon.user.core.util.DatabaseUtil;

public class CustomUserStoreManager extends JDBCUserStoreManager {
    private static Log log = LogFactory.getLog(CustomUserStoreManager.class);

    public CustomUserStoreManager() {
    }

    public CustomUserStoreManager(org.wso2.carbon.user.api.RealmConfiguration realmConfig,
                               Map<String, Object> properties,
                               ClaimManager claimManager,
                               ProfileConfigurationManager profileManager,
                               UserRealm realm, Integer tenantId)
            throws UserStoreException {
        super(realmConfig, properties, claimManager, profileManager, realm, tenantId, false);
    }

    @Override
    public boolean doAuthenticate(String userName, Object credential) throws UserStoreException {

        String password = (String) credential;
        if("admin".equals(userName) && "admin".equals(password)) {
            return true;
        }

        return false;
    }

    @Override
    public org.wso2.carbon.user.api.Properties getDefaultUserStoreProperties() {
        Properties properties = new Properties();
        properties.setMandatoryProperties(CustomUserStoreManagerConstants.MANDATORY_PROPERTIES.toArray
                (new Property[CustomUserStoreManagerConstants.MANDATORY_PROPERTIES.size()]));
        properties.setOptionalProperties(CustomUserStoreManagerConstants.OPTIONAL_PROPERTIES.toArray
                (new Property[CustomUserStoreManagerConstants.OPTIONAL_PROPERTIES.size()]));
        properties.setAdvancedProperties(CustomUserStoreManagerConstants.ADVANCED_PROPERTIES.toArray
                (new Property[CustomUserStoreManagerConstants.ADVANCED_PROPERTIES.size()]));
        return properties;
    }
}
