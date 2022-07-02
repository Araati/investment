package com.vazgen.investment.security.permission;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum Authority implements GrantedAuthority {

    ROLE_ADMIN(1);

    private static final Map<Integer, Authority> AUTHORITY_MAP;

    static {
        AUTHORITY_MAP = Arrays.stream(Authority.values())
                .collect(Collectors.toMap(Authority::getId, Function.identity()));
    }

    private final int id;

    Authority(final int id){
        this.id = id;
    }

    @NonNull
    public static Authority fromValue(final String id){
        return fromValue(Integer.parseInt(id));
    }

    public static Authority fromValue(final long id){
        return fromValue((int) id);
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Authority fromValue(final int id){
        if(!AUTHORITY_MAP.containsKey(id)){
            throw new RuntimeException("com.vazgen.investment.security.Authority not found");
        }
        return AUTHORITY_MAP.get(id);
    }

    @JsonValue
    public int toValue(){
        return id;
    }

    @Override
    public String getAuthority() {
        return this.name();
    }

    // TODO: 28.06.2022 Выпилить ненужное

}
