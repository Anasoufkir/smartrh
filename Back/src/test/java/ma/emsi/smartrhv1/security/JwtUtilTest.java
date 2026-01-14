package ma.emsi.smartrhv1.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "jwtSecret",
                "test-secret-key-for-unit-tests-min-256-bits-xxxxxxxx");
        ReflectionTestUtils.setField(jwtUtil, "jwtExpiration", 3600000L);
    }

    @Test
    void generateAndValidate_roundtrip() {
        String token = jwtUtil.generateToken("alice", "USER");
        assertThat(jwtUtil.validateToken(token)).isTrue();
    }

    @Test
    void extractUsername_returnsCorrectValue() {
        String token = jwtUtil.generateToken("alice", "HR");
        assertThat(jwtUtil.extractUsername(token)).isEqualTo("alice");
    }

    @Test
    void extractRole_returnsCorrectValue() {
        String token = jwtUtil.generateToken("alice", "HR");
        assertThat(jwtUtil.extractRole(token)).isEqualTo("HR");
    }

    @Test
    void validateToken_invalidToken_returnsFalse() {
        assertThat(jwtUtil.validateToken("not.a.jwt")).isFalse();
    }

    @Test
    void validateToken_tamperedToken_returnsFalse() {
        String token = jwtUtil.generateToken("alice", "USER");
        assertThat(jwtUtil.validateToken(token + "tampered")).isFalse();
    }
}
