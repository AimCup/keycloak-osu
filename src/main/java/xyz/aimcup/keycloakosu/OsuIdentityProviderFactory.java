package xyz.aimcup.keycloakosu;

import org.keycloak.broker.provider.AbstractIdentityProviderFactory;
import org.keycloak.broker.social.SocialIdentityProviderFactory;
import org.keycloak.models.IdentityProviderModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;

import java.util.List;

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
    public List<ProviderConfigProperty> getConfigProperties() {
        return ProviderConfigurationBuilder.create()
                .property()
                .name("avatar_url")
                .label("Avatar URL")
                .helpText("Avatar URL")
                .type(ProviderConfigProperty.STRING_TYPE)
                .defaultValue("${avatar_url}")
                .add()
                .build();
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }
}
