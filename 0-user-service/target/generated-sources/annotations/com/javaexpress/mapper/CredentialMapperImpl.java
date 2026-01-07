package com.javaexpress.mapper;

import com.javaexpress.dto.CredentialDto;
import com.javaexpress.models.Credential;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-07T22:20:10+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 25.0.1 (Eclipse Adoptium)"
)
@Component
public class CredentialMapperImpl implements CredentialMapper {

    @Override
    public CredentialDto toCredentialDto(Credential credential) {
        if ( credential == null ) {
            return null;
        }

        CredentialDto credentialDto = new CredentialDto();

        credentialDto.setUsername( credential.getUsername() );
        credentialDto.setPassword( credential.getPassword() );
        credentialDto.setRoleBasedAuthority( credential.getRoleBasedAuthority() );

        return credentialDto;
    }

    @Override
    public Credential toCredentialEntity(CredentialDto credentialDto) {
        if ( credentialDto == null ) {
            return null;
        }

        Credential credential = new Credential();

        credential.setUsername( credentialDto.getUsername() );
        credential.setPassword( credentialDto.getPassword() );
        credential.setRoleBasedAuthority( credentialDto.getRoleBasedAuthority() );

        return credential;
    }
}
