package com.example.mbtiboard.Util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class UserUtil {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public User getUserInfo(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        //토큰이 null 이 아니고 유효할 경우 수정 가능
        if (token != null && jwtUtil.validateToken(token)) {
            // 토큰에서 사용자 정보 가져오기
            claims = jwtUtil.getUserInfoFromToken(token);

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            return userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        } else {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }
    }