package com.accenture.russiaatc.irentservice10.SNAPSHOT.model;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class CallContext {
    private Long id;
    private String login;
    private Role role;
}
