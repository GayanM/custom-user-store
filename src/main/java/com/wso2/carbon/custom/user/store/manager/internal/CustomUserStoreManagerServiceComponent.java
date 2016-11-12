package com.wso2.carbon.custom.user.store.manager.internal;

import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.user.api.UserStoreManager;
import org.wso2.carbon.user.core.service.RealmService;
import com.wso2.carbon.custom.user.store.manager.CustomUserStoreManager;

/**
 * @scr.component name="com.wso2.carbon.custom.user.store.manager.component" immediate="true"
 * @scr.reference name="realm.service"
 * interface="org.wso2.carbon.user.core.service.RealmService"cardinality="1..1"
 * policy="dynamic" bind="setRealmService" unbind="unsetRealmService"
 */
public class CustomUserStoreManagerServiceComponent {

    private static Log log = LogFactory.getLog(CustomUserStoreManagerServiceComponent.class);
    
    private static RealmService realmService;
    
    protected void activate(ComponentContext ctxt) {
    	Hashtable<String, String> props = new Hashtable<String, String>();       
        CustomUserStoreManager customUserStoreManager = new CustomUserStoreManager();
        ctxt.getBundleContext().registerService(UserStoreManager.class.getName(), customUserStoreManager, props);
        log.info("CustomUserStoreManager bundle activated successfully..");
    }

    protected void deactivate(ComponentContext ctxt) {
        if (log.isDebugEnabled()) {
            log.info("CustomUserStoreManager bundle is deactivated");
        }
    }
    
    protected void setRealmService(RealmService realmService) {
        log.debug("Setting the Realm Service");
        CustomUserStoreManagerServiceComponent.realmService = realmService;
    }

    protected void unsetRealmService(RealmService realmService) {
        log.debug("UnSetting the Realm Service");
        CustomUserStoreManagerServiceComponent.realmService = null;
    }

    public static RealmService getRealmService() {
        return realmService;
    }
}
