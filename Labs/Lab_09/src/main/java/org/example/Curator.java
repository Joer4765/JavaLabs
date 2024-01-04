package org.example;

import lombok.Data;
import lombok.NonNull;

@Data
public class Curator {
    @NonNull
    private int curatorId;
    @NonNull
    private String curatorName;
    @NonNull
    private int groupId;
}
