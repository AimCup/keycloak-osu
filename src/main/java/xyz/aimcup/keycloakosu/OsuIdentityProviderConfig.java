package xyz.aimcup.keycloakosu;

import org.keycloak.broker.oidc.OAuth2IdentityProviderConfig;
import org.keycloak.models.IdentityProviderModel;

public class OsuIdentityProviderConfig extends OAuth2IdentityProviderConfig {

    public OsuIdentityProviderConfig(IdentityProviderModel identityProviderModel) {
        super(identityProviderModel);
    }

    public OsuIdentityProviderConfig() {
    }

    @Override
    public String getDefaultScope() {
        return "identify";
    }

    @Override
    public boolean isBasicAuthentication() {
        return false;
    }
}
