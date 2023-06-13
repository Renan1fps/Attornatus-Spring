package com.projectInter.application.usecases;

import com.projectInter.application.exception.BadRequestException;
import com.projectInter.application.protocols.Encrypt;
import com.projectInter.domain.entities.User;
import com.projectInter.domain.repositories.UserRepository;
import com.projectInter.mocks.UserMock;
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

@ExtendWith(SpringExtension.class)
class CreateUserUseCaseTest {

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Encrypt crypto;

    @BeforeEach
    void setUp() {
        BDDMockito.when(userRepository.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(UserMock.createdValidUser()));
        BDDMockito.when(userRepository.save(ArgumentMatchers.any(User.class)))
                .thenReturn(UserMock.createdValidUser());
    }

    @Test
    @DisplayName("Should throw error when user already exists")
    void whenUserAlreadyExists() {
        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() ->
                        createUserUseCase.execute(UserMock.createValidUser())
                );
    }

    @Test
    @DisplayName("Should return valid user saved")
    void whenSaveWithSuccess() {
        BDDMockito.when(userRepository.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        User user = createUserUseCase.execute(UserMock.createValidUser());
        Assertions.assertThat(user.getId()).isNotNull();
        Assertions.assertThat(user).isNotNull().isEqualTo(UserMock.createdValidUser());
    }
}