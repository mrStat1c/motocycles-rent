SET sql_safe_updates = 0;

DELETE FROM customer;

INSERT INTO customer (name, driver_lic, phone)
VALUES
('Martin JJJ', '99 11 111111', '+79061111111'),
('Mary A', '99 22 222222', '+79062222222'),
('Soler from Astora', '99 33 333333', '+79063333333');

DELETE FROM motocycle;

INSERT INTO motocycle (model, vin, `release`, weight, power, type)
VALUES
('Bajaj Dominar 400', 'FM344LL544I', '2020', 194, 40, 'Naked'),
('Yamaha MT-03', 'AG4KK09GS0', '2020', 165, 42, 'Naked'),
('Honda Rebel 1100', 'JDFA345PGD1', '2022', 204, 75, NULL);

-- todo сделать побольше разнообразных данных, в т.ч. добавить уже арендованные мотоциклы