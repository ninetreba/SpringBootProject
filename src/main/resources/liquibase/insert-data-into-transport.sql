insert into Transport (id_Transport, transport_Type, number, transport_Condition, current_Parking, status, transport_Status, longitude, latitude)
values (42, 'BICYCLE', 'ВЕЛ-42', 'EXCELLENT', 42, 'WORKING', 'FREE', 35.873418, 56.853929);
insert into Transport (id_Transport, transport_Type, number, transport_Condition, current_Parking, status, transport_Status, longitude, latitude)
values (43, 'BICYCLE', 'ВЕЛ-43', 'EXCELLENT', 42, 'WORKING', 'FREE', 35.859929, 56.845104);
insert into Bicycle (Transport_id_transport)
values (42);
insert into Bicycle (Transport_id_transport)
values (43);