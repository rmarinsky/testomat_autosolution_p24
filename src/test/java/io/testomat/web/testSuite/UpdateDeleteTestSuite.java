package io.testomat.web.testSuite;

import io.testomat.web.common.data.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

public class UpdateDeleteTestSuite {

    //just for example how to struct
    @ParameterizedTest
    @MethodSource("ololo")
    @DisplayName("popopo sdafpo sdpfo spdfo sdpfo  [{index}] {argumentsWithNames}")
    void popopoSdafpoSdpfoSpdfoSdpfo(UserDto targetUserDto) {
        System.out.println(targetUserDto);
    }

    private static List<UserDto> ololo() {
        return List.of(new UserDto("sdf"," asdf", true));
    }
}
