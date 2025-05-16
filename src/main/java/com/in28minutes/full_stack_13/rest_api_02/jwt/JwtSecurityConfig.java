package com.in28minutes.full_stack_13.rest_api_02.jwt;

@Configuration
@EnableWebSecurity
public class JwtSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // https://github.com/spring-projects/spring-security/issues/1231
        // https://docs.spring.io/spring-boot/docs/current/reference/html/data.html#data.sql.h2-web-console.spring-security
        return httpSecurity
                .authorizeHttpRequests(auth -> auth
                	.requestMatchers("/authenticate").permitAll()
                	.requestMatchers(PathRequest.toH2Console()).permitAll() // h2-console is a servlet and NOT recommended for a production
                    .requestMatchers(HttpMethod.OPTIONS,"/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.
                	sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt) // Deprecated in SB 3.1.x
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults())) // Starting from SB 3.1.x using Lambda DSL
                .httpBasic(
                        Customizer.withDefaults())
//                .headers(header -> { // Deprecated in SB 3.1.x
//                    header.frameOptions().sameOrigin();
//                })
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) // Starting from SB 3.1.x using Lambda DSL
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("in28minutes")
                                .password("{noop}dummy")
                                .authorities("read")
                                .roles("USER")
                                .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        JWKSet jwkSet = new JWKSet(rsaKey());

        return (((jwkSelector, securityContext) 
                        -> jwkSelector.select(jwkSet)));
    }

    @Bean
    JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    JwtDecoder jwtDecoder() throws JOSEException {
        return NimbusJwtDecoder
                .withPublicKey(rsaKey().toRSAPublicKey())
                .build();
    }
    
    @Bean
    public RSAKey rsaKey() {
        KeyPair keyPair = keyPair();
        
        return new RSAKey
                .Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey((RSAPrivateKey) keyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    @Bean
    public KeyPair keyPair() {
        try {
            var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Unable to generate an RSA Key Pair", e);
        }
    }
    
}