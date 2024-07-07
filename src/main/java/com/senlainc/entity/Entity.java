package com.senlainc.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Entity {
    protected long id;

    public abstract void parseFieldsFromStringValue(String string);
}
