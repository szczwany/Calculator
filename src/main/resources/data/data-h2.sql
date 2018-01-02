INSERT INTO projects (name) VALUES
('Simple calculations'), ('Population density /km2'), ('100 PLN in foreign currencies');

INSERT INTO calculations (description, expression, project_id) VALUES
('First calculation','2+2', 1), ('Second calculation', '12+434*21/3.3', 1), ('Third calculation', '2.2+2.89*2.98', 1),
('Poland','38422346/312679', 2), ('Germany', '82349400/357375.62', 2), ('Japan', '126451398/377972', 2),
('China','1379302771/9596960', 2), ('GBP', '100/4.7', 3), ('EUR', '100/4.16', 3), ('USD', '100/3.45', 3);