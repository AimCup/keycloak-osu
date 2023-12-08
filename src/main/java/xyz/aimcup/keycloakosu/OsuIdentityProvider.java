package xyz.aimcup.keycloakosu;

import com.fasterxml.jackson.databind.JsonNode;
import org.keycloak.broker.oidc.AbstractOAuth2IdentityProvider;
import org.keycloak.broker.provider.BrokeredIdentityContext;
import org.keycloak.broker.provider.IdentityBrokerException;
import org.keycloak.broker.provider.util.SimpleHttp;
import org.keycloak.broker.social.SocialIdentityProvider;
import org.keycloak.events.EventBuilder;
import org.keycloak.models.KeycloakSession;

import java.io.IOException;
import java.util.logging.Logger;

public class OsuIdentityProvider extends AbstractOAuth2IdentityProvider<OsuIdentityProviderConfig> implements SocialIdentityProvider<OsuIdentityProviderConfig> {
    public static final Logger log = Logger.getLogger(String.valueOf(OsuIdentityProvider.class));

    public static final String AUTHORIZATION_URI = "https://osu.ppy.sh/oauth/authorize";
    public static final String TOKEN_URL = "https://osu.ppy.sh/oauth/token";
    public static final String USER_INFO_URI = "https://osu.ppy.sh/api/v2/me";
    public static final String DEFAULT_SCOPE = "identify public";
    public OsuIdentityProvider(KeycloakSession session, OsuIdentityProviderConfig config) {
        super(session, config);
        config.setAuthorizationUrl(AUTHORIZATION_URI);
        config.setTokenUrl(TOKEN_URL);
        config.setUserInfoUrl(USER_INFO_URI);
    }

    @Override
    protected BrokeredIdentityContext doGetFederatedIdentity(String accessToken) {
        log.log(java.util.logging.Level.INFO, "==========doGetFederatedIdentity");
        JsonNode node;
        try {
            node = SimpleHttp.doGet(USER_INFO_URI, session).header("Authorization", "Bearer " + accessToken).asJson();
        } catch (IOException e) {
            throw new IdentityBrokerException("Could not obtain user profile from osu!", e);
        }
        return extractIdentityFromProfile(null, node);
    }

    @Override
    protected String getDefaultScopes() {
        return DEFAULT_SCOPE;
    }

    @Override
    protected BrokeredIdentityContext extractIdentityFromProfile(EventBuilder event, JsonNode node) {
        log.log(java.util.logging.Level.INFO, "==========extractIdentityFromProfile");
        BrokeredIdentityContext user = new BrokeredIdentityContext(getJsonProperty(node, "id"));

        user.setId(getJsonProperty(node, "id"));
        user.setUsername(getJsonProperty(node, "id"));

        OsuUserAttributeMapper.storeUserProfileForMapper(user, node, getConfig().getAlias());
        log.log(java.util.logging.Level.INFO, "==========extractIdentityFromProfile: " + user.getUsername());
        return user;
    }

    @Override
    protected boolean supportsExternalExchange() {
        return true;
    }
}
