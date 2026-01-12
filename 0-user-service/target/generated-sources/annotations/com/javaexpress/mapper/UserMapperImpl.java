package com.javaexpress.mapper;

import com.javaexpress.dto.CredentialDto;
import com.javaexpress.dto.UserDto;
import com.javaexpress.models.Credential;
import com.javaexpress.models.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-09T18:25:44+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 25.0.1 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setEmailAddress( user.getEmail() );
        userDto.setContact( user.getPhone() );
        userDto.setCredential( credentialToCredentialDto( user.getCredential() ) );
        userDto.setFirstName( user.getFirstName() );
        userDto.setLastName( user.getLastName() );
        userDto.setUserId( user.getUserId() );

        return userDto;
    }

    @Override
    public User toUserEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( userDto.getEmailAddress() );
        user.setPhone( userDto.getContact() );
        user.setFirstName( userDto.getFirstName() );
        user.setLastName( userDto.getLastName() );
        user.setCredential( credentialDtoToCredential( userDto.getCredential() ) );

        return user;
    }

    @Override
    public void updateUserFromDto(UserDto dto, User user) {
        if ( dto == null ) {
            return;
        }

        user.setEmail( dto.getEmailAddress() );
        user.setPhone( dto.getContact() );
        user.setFirstName( dto.getFirstName() );
        user.setLastName( dto.getLastName() );
        if ( dto.getCredential() != null ) {
            if ( user.getCredential() == null ) {
                user.setCredential( new Credential() );
            }
            credentialDtoToCredential1( dto.getCredential(), user.getCredential() );
        }
        else {
            user.setCredential( null );
        }
    }

    protected CredentialDto credentialToCredentialDto(Credential credential) {
        if ( credential == null ) {
            return null;
        }

        CredentialDto credentialDto = new CredentialDto();

        credentialDto.setPassword( credential.getPassword() );
        credentialDto.setRoleBasedAuthority( credential.getRoleBasedAuthority() );
        credentialDto.setUsername( credential.getUsername() );

        return credentialDto;
    }

    protected Credential credentialDtoToCredential(CredentialDto credentialDto) {
        if ( credentialDto == null ) {
            return null;
        }

        Credential credential = new Credential();

        credential.setUsername( credentialDto.getUsername() );
        credential.setPassword( credentialDto.getPassword() );
        credential.setRoleBasedAuthority( credentialDto.getRoleBasedAuthority() );

        return credential;
    }

    protected void credentialDtoToCredential1(CredentialDto credentialDto, Credential mappingTarget) {
        if ( credentialDto == null ) {
            return;
        }

        mappingTarget.setUsername( credentialDto.getUsername() );
        mappingTarget.setPassword( credentialDto.getPassword() );
        mappingTarget.setRoleBasedAuthority( credentialDto.getRoleBasedAuthority() );
    }
}
