package com.attornatus.application.usecases;

import com.attornatus.application.exception.BadRequestException;
import com.attornatus.domain.entities.User;
import com.attornatus.domain.repositories.UserRepository;
import com.attornatus.mocks.UserMock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
class GetUserByIdTest {

    @InjectMocks
    private GetUserById getUserById;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        BDDMockito.when(userRepository.findById(ArgumentMatchers.any(UUID.class)))
                .thenReturn(Optional.of(UserMock.createdValidUser()));
    }

    @Test
    @DisplayName("Should throw error when user is not provided")
    void whenUserIsNotProvided() {
        BDDMockito.when(userRepository.findById(ArgumentMatchers.any(UUID.class)))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() ->
                        getUserById.execute(UserMock.id.toString())
                );
    }

    @Test
    @DisplayName("Should return user when is provided")
    void whenUserIsProvided() {
        User user = getUserById.execute(UserMock.id.toString());
        Assertions.assertThat(user.getId()).isNotNull();
        Assertions.assertThat(user.getId()).isEqualTo(UserMock.id);
        Assertions.assertThat(user).isNotNull().isEqualTo(UserMock.createdValidUser());
    }

}