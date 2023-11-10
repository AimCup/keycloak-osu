package xyz.aimcup.keycloakosu;

import org.keycloak.broker.provider.AbstractIdentityProviderFactory;
import org.keycloak.broker.social.SocialIdentityProviderFactory;
import org.keycloak.models.IdentityProviderModel;
import org.keycloak.models.KeycloakSession;

public class OsuIdentityProviderFactory extends AbstractIdentityProviderFactory<OsuIdentityProvider>
        implements SocialIdentityProviderFactory<OsuIdentityProvider> {

    public static final String PROVIDER_ID = "osu";

    @Override
    public String getName() {
        return "Osu! OAuth2";
    }

    @Override
    public OsuIdentityProvider create(KeycloakSession keycloakSession, IdentityProviderModel identityProviderModel) {
        return new OsuIdentityProvider(keycloakSession, new OsuIdentityProviderConfig(identityProviderModel));
    }

    @Override
    public IdentityProviderModel createConfig() {
        return new OsuIdentityProviderConfig();
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }
}
