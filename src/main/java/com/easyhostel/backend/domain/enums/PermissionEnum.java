package com.easyhostel.backend.domain.enums;

public enum PermissionEnum {

    CREATE_HOUSE, READ_HOUSE, UPDATE_HOUSE, DELETE_HOUSE,
    CREATE_ROOM, READ_ROOM, UPDATE_ROOM, DELETE_ROOM,
    MANAGE_CONTRACT, MANAGE_CONTRACT_INTERIOR, MANAGE_CONTRACT_ROOM_AMENITY, MANAGE_CONTRACT_VEHICLE,
    MANAGE_INTERIOR, MANAGE_ROOM_AMENITY, MANAGE_VEHICLE, MANAGE_RENT_PAYMENT, MANAGE_REPAIR_ROOM_LOG;

    public String getName() {
        return this.name();
    }

    public String getDescription() {
        return this.name().toLowerCase();
    }

}
