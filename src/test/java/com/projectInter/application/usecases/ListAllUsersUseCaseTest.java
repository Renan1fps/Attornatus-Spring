package com.projectInter.application.usecases;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class ListAllUsersUseCaseTest {

    @InjectMocks
    private ListAllUsersUseCase listAllUsersUseCase;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        PageImpl<User> userPage = new PageImpl<>(List.of(UserMock.createdValidUser()));
        BDDMockito.when(userRepository.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(userPage);
    }

    @Test
    @DisplayName("Should return list of user when is provided")
    void whenUserIsProvided() {
        Page<User> response = listAllUsersUseCase.execute(PageRequest.of(0, 1));
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.toList().get(0).getId()).isEqualTo(UserMock.id);
        Assertions.assertThat(response.toList())
                .isNotEmpty()
                .hasSize(1);
    }
}