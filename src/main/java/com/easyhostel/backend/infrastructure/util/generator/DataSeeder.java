package com.easyhostel.backend.infrastructure.util.generator;

import com.easyhostel.backend.domain.entity.*;
import com.easyhostel.backend.domain.entity.embedded.ManagerHouseId;
import com.easyhostel.backend.domain.enums.RentPaymentStatus;
import com.easyhostel.backend.domain.repository.interfaces.house.IHouseRepository;
import com.easyhostel.backend.domain.repository.interfaces.interior.IInteriorRepository;
import com.easyhostel.backend.domain.repository.interfaces.manager.IManagerRepository;
import com.easyhostel.backend.domain.repository.interfaces.managerhouse.IManagerHouseRepository;
import com.easyhostel.backend.domain.repository.interfaces.roomamenity.IRoomAmenityRepository;
import com.easyhostel.backend.domain.repository.interfaces.vehicle.IVehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Automatically check and create default data if not present
 *
 * @author Nyx
 */
@Component
@RequiredArgsConstructor
public class DataSeeder {

    private final IHouseRepository _houseRepository;

    private final IInteriorRepository _interiorRepository;
    private final IRoomAmenityRepository _roomAmenityRepository;
    private final IVehicleRepository _vehicleRepository;

    private final IManagerRepository _managerRepository;
    private final IManagerHouseRepository _managerHouseRepository;

    /**
     * Initialize method to seed data
     *
     * @author Nyx
     */
    public void init() {
        seedData();
    }

    /**
     * Seed House, Room, Contract, Vehicle, Interior, RoomAmenity data
     *
     * @author Nyx
     */
    private void seedData() {
        // Check if there's no House, which means no Room, no Contract, no ManagerHouse
        // Check if there's no Interior, RoomAmenity, Vehicle
        // Creating ContractInterior, ContractRoomAmenity, ContractVehicle would be very hard, skip for now

        if (_interiorRepository.count() < 1) {
            var interiors = List.of(
                    Interior.builder().interiorName("Closet").createdBy("auto-gen").modifiedBy("auto-gen").build(),
                    Interior.builder().interiorName("Air Conditioner").createdBy("auto-gen").modifiedBy("auto-gen").build(),
                    Interior.builder().interiorName("Fridge").createdBy("auto-gen").modifiedBy("auto-gen").build(),
                    Interior.builder().interiorName("Mirror").createdBy("auto-gen").modifiedBy("auto-gen").build(),
                    Interior.builder().interiorName("Cupboard").createdBy("auto-gen").modifiedBy("auto-gen").build());

            _interiorRepository.saveAll(interiors);
        }

        if (_roomAmenityRepository.count() < 1) {
            var roomAmenities = List.of(
                    RoomAmenity.builder().roomAmenityType("General").roomAmenityName("Cleaning").createdBy("auto-gen").modifiedBy("auto-gen").build(),
                    RoomAmenity.builder().roomAmenityType("Network").roomAmenityName("Wifi").createdBy("auto-gen").modifiedBy("auto-gen").build(),
                    RoomAmenity.builder().roomAmenityType("AC").roomAmenityName("Air Conditioner").createdBy("auto-gen").modifiedBy("auto-gen").build(),
                    RoomAmenity.builder().roomAmenityType("Fridge").roomAmenityName("Fridge").createdBy("auto-gen").modifiedBy("auto-gen").build(),
                    RoomAmenity.builder().roomAmenityType("Washing").roomAmenityName("Washing Machine").createdBy("auto-gen").modifiedBy("auto-gen").build());


            _roomAmenityRepository.saveAll(roomAmenities);
        }

        if (_vehicleRepository.count() < 1) {
            var vehicles = List.of(
                    Vehicle.builder().vehicleType("Motorbike").vehicleModel("Air Blade").vehicleColor("Red").vehicleLicensePlate("59A.11111").createdBy("auto-gen").modifiedBy("auto-gen").build(),
                    Vehicle.builder().vehicleType("Motorbike").vehicleModel("Winner").vehicleColor("Blue").vehicleLicensePlate("59A.22222").createdBy("auto-gen").modifiedBy("auto-gen").build(),
                    Vehicle.builder().vehicleType("Motorbike").vehicleModel("Wave").vehicleColor("Black").vehicleLicensePlate("59A.33333").createdBy("auto-gen").modifiedBy("auto-gen").build(),
                    Vehicle.builder().vehicleType("Motorbike").vehicleModel("Lead").vehicleColor("Green").vehicleLicensePlate("59A.44444").createdBy("auto-gen").modifiedBy("auto-gen").build(),
                    Vehicle.builder().vehicleType("Bike").vehicleModel(null).vehicleColor("Black and white").vehicleLicensePlate(null).createdBy("auto-gen").modifiedBy("auto-gen").build()
            );

            _vehicleRepository.saveAll(vehicles);
        }

        if (_houseRepository.count() < 1) {
            // Create House first
            var ACHouse = House.builder().houseName("9583AC").roomQuantity(10).occupancy(0.9f).createdBy("auto-gen").modifiedBy("auto-gen").build();
            var LTTHouse = House.builder().houseName("269LTT").roomQuantity(5).occupancy(0.7f).createdBy("auto-gen").modifiedBy("auto-gen").build();
            var B3House = House.builder().houseName("2912B3").roomQuantity(3).occupancy(0.8f).createdBy("auto-gen").modifiedBy("auto-gen").build();

            // Create Rooms referencing to House
            var roomsOfACHouse = Set.of(
                    createRoomWithHouseAndContracts("001", RentPaymentStatus.PAID, ACHouse),
                    createRoomWithHouseAndContracts("002", RentPaymentStatus.IN_PROGRESS, ACHouse),
                    createRoomWithHouseAndContracts("003", RentPaymentStatus.PAID, ACHouse),
                    createRoomWithHouseAndContracts("004", RentPaymentStatus.IN_PROGRESS, ACHouse),
                    createRoomWithHouseAndContracts("005", RentPaymentStatus.PAID, ACHouse),
                    createRoomWithHouseAndContracts("006", RentPaymentStatus.IN_PROGRESS, ACHouse),
                    createRoomWithHouseAndContracts("007", RentPaymentStatus.PAID, ACHouse),
                    createRoomWithHouseAndContracts("008", RentPaymentStatus.IN_PROGRESS, ACHouse),
                    createRoomWithHouseAndContracts("009", RentPaymentStatus.PAID, ACHouse),
                    createRoomWithHouseAndContracts("010", RentPaymentStatus.IN_PROGRESS, ACHouse)
            );

            var roomsOfLTTHouse = Set.of(
                    createRoomWithHouseAndContracts("01", RentPaymentStatus.PAID, LTTHouse),
                    createRoomWithHouseAndContracts("02", RentPaymentStatus.IN_PROGRESS, LTTHouse),
                    createRoomWithHouseAndContracts("03", RentPaymentStatus.PAID, LTTHouse),
                    createRoomWithHouseAndContracts("04", RentPaymentStatus.IN_PROGRESS, LTTHouse),
                    createRoomWithHouseAndContracts("05", RentPaymentStatus.PAID, LTTHouse)
            );

            var roomsOfB3House = Set.of(
                    createRoomWithHouseAndContracts("A1", RentPaymentStatus.PAID, B3House),
                    createRoomWithHouseAndContracts("A2", RentPaymentStatus.IN_PROGRESS, B3House),
                    createRoomWithHouseAndContracts("A3", RentPaymentStatus.PAID, B3House)
            );

            // Set Rooms for House
            ACHouse.setRooms(roomsOfACHouse);
            LTTHouse.setRooms(roomsOfLTTHouse);
            B3House.setRooms(roomsOfB3House);

            // If run save method separately, it will fail, why is that?
            _houseRepository.saveAll(List.of(ACHouse, LTTHouse, B3House));

            var sysadmin = _managerRepository.findManagerByUsername("sysadmin");
            var admin = _managerRepository.findManagerByUsername("admin");
            var user = _managerRepository.findManagerByUsername("user");

            // Create ManagerHouse
            if (sysadmin.isPresent() && admin.isPresent() && user.isPresent()) {
                var managerHouses = List.of(
                        // Create ManagerHouse for admin and ACHouse
                        ManagerHouse.builder()
                                .managerHouseId(ManagerHouseId.builder().managerId(admin.get().getManagerId()).houseId(ACHouse.getHouseId()).build())
                                .manager(admin.get())
                                .house(ACHouse)
                                .createdBy("auto-gen").modifiedBy("auto-gen").build(),
                        // Create ManagerHouse for admin and LTTHouse
                        ManagerHouse.builder()
                                .managerHouseId(ManagerHouseId.builder().managerId(admin.get().getManagerId()).houseId(LTTHouse.getHouseId()).build())
                                .manager(admin.get())
                                .house(LTTHouse)
                                .createdBy("auto-gen").modifiedBy("auto-gen").build(),
                        // Create ManagerHouse for user and B3House
                        ManagerHouse.builder()
                                .managerHouseId(ManagerHouseId.builder().managerId(user.get().getManagerId()).houseId(B3House.getHouseId()).build())
                                .manager(user.get())
                                .house(B3House)
                                .createdBy("auto-gen").modifiedBy("auto-gen").build()
                );

                _managerHouseRepository.saveAll(managerHouses);
            }
        }
    }

    /**
     * Create Room with reference to House
     *
     * @param roomNumber Room's number
     * @param status Room's rent payment status
     * @param house House
     * @return Room
     * @author Nyx
     */
    private Room createRoomWithHouseAndContracts(String roomNumber, RentPaymentStatus status, House house) {
        Room room = Room.builder()
                .roomNumber(roomNumber)
                .rentPaymentStatus(status)
                .house(house)
                .createdBy("auto-gen").modifiedBy("auto-gen").build();

        Set<Contract> contracts = createRandomContracts(room);
        room.setContracts(contracts);

        return room;
    }

    /**
     * Create random contracts with reference to Room
     *
     * @param room Room
     * @return Contracts
     * @author Nyx
     */
    private Set<Contract> createRandomContracts(Room room) {
        return Set.of(
                Contract.builder().peopleQuantity((int) (Math.random() * 5) + 1).rentPrice((float) Math.random() * 100)
                        .signingDate(LocalDate.now()).expirationDate(LocalDate.now().plusMonths(1))
                        .room(room).createdBy("auto-gen").modifiedBy("auto-gen").build(),
                Contract.builder().peopleQuantity((int) (Math.random() * 5) + 1).rentPrice((float) Math.random() * 100)
                        .signingDate(LocalDate.now()).expirationDate(LocalDate.now().plusMonths(2))
                        .room(room).createdBy("auto-gen").modifiedBy("auto-gen").build());
    }

}
