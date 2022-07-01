package com.vazgen.investment.util;

import java.util.Optional;
import java.util.UUID;

public interface RequestIdHolder {

    Optional<UUID> get();

    default UUID mustGet(){
        return get().orElseThrow(RuntimeException::new);
    }

//	default boolean isEmpty(){
//		return get().isEmpty();
//	}

    default boolean isPresent(){
        return get().isPresent();
    }

    void save(UUID id);
}
