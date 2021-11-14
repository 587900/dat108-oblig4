DROP SCHEMA IF EXISTS fest CASCADE;
CREATE SCHEMA fest;
SET search_path = fest;

CREATE TABLE users
(
   firstname VARCHAR (20),
   lastname VARCHAR (20),
   cell CHAR (8),
   hash CHAR (64),
   salt CHAR (32),
   sex CHAR (1),
   PRIMARY KEY (cell)
);

-- Password same as phone number in these examples.
INSERT INTO users VALUES
	('Are', 'Clementin', '66666666', 'F9B0504C2A614851EF5E78CC650310CEF423EDBCF137A576872B976CDFE3980E', 'E0B6E9024A3C472D29061DC355777C50', 'f'),
	('Chris P', 'Bacon', '77777777', 'B1B7E1FE53651FD3815DCC68C36D5B85FA1534615D3ADE16A4B64A72A2C363D4', '561E2DBDAA31749B7E67D6A643BEF8A1', 'm'),
	('Janice', 'Keihanaikukauaka', '88888888', '7CF6EF4697D1E6EFFD1503DB57BFDFED7E9AF682608ABB2C1071DA471D99F294', 'BBF2C5A0BD4094DC6376D4CE5645EA70', 'f'),
	('Kjetil', 'Dverge', '11111111', '43975690F9959A17490E1614C9ACFF65A9E4ECE170C7216B4737E934E91DD1DD', '4B837791E12C84E711CB947312709D63', 'm'),
	('Lima', 'Lima', '33333333', '456A3437147722220BBC2EA512996FA94C646F3F9891D4BD11DE598419AE1663', 'AA5711381A30CF6B5E51F54136B69CE5', 'f'),
	('Llars Erik', 'Birkefjell', '22222222', '963D2E4CA155F39B8D8134F91A27E59B11CFA3792352AD87EB56A788D70A7736', '8D9576AD123544587A6390BF0547EBF3', 'f'),
	('Sjølvaste', 'Kristoffer', '44444444', '4DC5A6CBE669A3043A7E746D77C028857BD8E5F470DCC1B63F0C45259DFB18E0', '483A6D3F1F3C661BD9EF781369E65CA1', 'm'),
	('Sjølvaste', 'Leidvin', '99999999', 'B6C14463913B0FC3294266FEDDAAA83625A81898D9DB7BF3691E3475729BCA81', '173A70B1254A3B87D8C499614160DBD6', 'm'),
	('Stein', 'Abeltun', '55555555', '0E46C5F1D85E3772DF87FBE91DBF0774202BFB20A39D2F8DB4B0433A8B3EFE16', 'E69B62A47654672D3341576653BECE51', 'm');
