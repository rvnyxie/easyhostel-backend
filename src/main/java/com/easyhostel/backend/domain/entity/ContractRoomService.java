package com.easyhostel.backend.domain.entity;

import com.easyhostel.backend.domain.entity.base.BaseEntity;
import com.easyhostel.backend.domain.entity.embedded.ContractRoomServiceId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "contract_has_room_service",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"contract_id", "room_service_id"})

        })
public class ContractRoomService extends BaseEntity {

    @EmbeddedId
    private ContractRoomServiceId contractRoomServiceId = new ContractRoomServiceId();

    private float roomServicePrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    @MapsId("contractId")
    private Contract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_service_id")
    @MapsId("roomServiceId")
    private RoomService roomService;

}
