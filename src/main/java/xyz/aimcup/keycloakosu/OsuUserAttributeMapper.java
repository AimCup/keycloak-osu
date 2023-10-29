package xyz.aimcup.keycloakosu;

import org.keycloak.broker.oidc.mappers.AbstractJsonUserAttributeMapper;

public class OsuUserAttributeMapper extends AbstractJsonUserAttributeMapper {
    private static final String[] cp = new String[] { OsuIdentityProviderFactory.PROVIDER_ID };

    @Override
    public String[] getCompatibleProviders() {
        return cp;
    }

    @Override
    public String getId() {
        return "osu-user-attribute-mapper";
    }
}
