insert into Functions (name, is_active, short_description, signature) values('!cmdlist',1, 'Cette commande permet de lister les commandes actives', 'cmdlist');
insert into functions (name, is_active, short_description, signature) values('!event',1,'Crée un nouvel événement au format [!event (Description de l événement) YYYY MM DD HH mm]','!event (Description de l événement) YYYY MM DD HH mm');
insert into functions (name, is_active, short_description, signature) values('!eventlist',1,'Permet de lister les événements futurs','!eventlist');
insert into functions (name, is_active, short_description, signature) values('!man',1,'Affiche une description de la commande au format [!man !cmd_recherchée]','!man !cmd_recherchée');
insert into functions (name, is_active, short_description, signature) values('!mod',1,'Permet de transformer un chatter en modérateur','!mod chatter_name');
insert into functions (name, is_active, short_description, signature) values('!participate',1,'Permet de participer à un événementau format [!participate id_événement]','!participate event_id');
insert into functions (name, is_active, short_description, signature) values('!unmod',1,'Permet de retirer les droits de modérateur à un chatter','!unmod chatter_name');

insert into users (name, is_moderator, messages_sent, last_modified) values('Albert',0,10,'2017-9-20 08:10:57');
insert into users (name, is_moderator, messages_sent, last_modified) values('Bertrand',0,3,'2012-7-4 04:15:54');
insert into users (name, is_moderator, messages_sent, last_modified) values('Christelle',0,211,'2017-9-20 08:11:15');
insert into users (name, is_moderator, messages_sent, last_modified) values('Deborah',0,351,'1776-7-3 04:15:54');
insert into users (name, is_moderator, messages_sent, last_modified) values('Eugène',0,900,'1776-7-4 01:15:54');
insert into users (name, is_moderator, messages_sent, last_modified) values('Francine',0,777,'1776-7-4 14:15:54');
insert into users (name, is_moderator, messages_sent, last_modified) values('Gérard',0,12,'1776-7-4 04:16:54');
insert into users (name, is_moderator, messages_sent, last_modified) values('Hector',0,3,'1776-7-4 04:25:54');
insert into users (name, is_moderator, messages_sent, last_modified) values('Inès',0,3,'2014-7-4 04:15:54');
insert into users (name, is_moderator, messages_sent, last_modified) values('Janine',0,25,'1976-7-4 04:15:54');
insert into users (name, is_moderator, messages_sent, last_modified) values('Koralie',0,61,'776-7-4 04:15:54');
insert into users (name, is_moderator, messages_sent, last_modified) values('Lilou',0,7985,'176-7-4 04:15:54');
insert into users (name, is_moderator, messages_sent, last_modified) values('Mario',0,362,'1770-7-4 04:15:54');
insert into users (name, is_moderator, messages_sent, last_modified) values('Noé',0,12,'16-7-4 04:15:54');
insert into users (name, is_moderator, messages_sent, last_modified) values('Olive',0,35,'1776-1-4 04:15:54');
insert into users (name, is_moderator, messages_sent, last_modified) values('Phillipe',0,7899,'1776-7-4 04:15:54');
insert into users (name, is_moderator, messages_sent, last_modified) values('Quentin',0,14,'1776-7-4 14:15:54');
insert into users (name, is_moderator, messages_sent, last_modified) values('Réginald',0,75,'1776-7-4 04:15:54');
insert into users (name, is_moderator, messages_sent, last_modified) values('Sophie',0,35,'1776-7-4 21:15:54');
insert into users (name, is_moderator, messages_sent, last_modified) values('Thérence',0,65,'1776-7-4 23:15:54');
insert into users (name, is_moderator, messages_sent, last_modified) values('Ursulla',0,1,'1776-7-4 04:24:54');
insert into users (name, is_moderator, messages_sent, last_modified) values('Véronique',0,1,'1779-7-4 04:15:54');
insert into users (name, is_moderator, messages_sent, last_modified) values('Wong',0,1,'1442-7-4 04:15:54');
insert into users (name, is_moderator, messages_sent, last_modified) values('Xavier',0,741,'2017-9-20 08:10:45');
insert into users (name, is_moderator, messages_sent, last_modified) values('Yohan',0,142,'1776-12-4 04:15:54');
insert into users (name, is_moderator, messages_sent, last_modified) values('Zébulon',0,42,'1776-7-12 04:15:54');

insert into messages (id, date, message, user_name) values(1,'2017-9-20 08:10:00','Bonjour','Albert');
insert into messages (id, date, message, user_name) values(2,'2017-9-20 08:10:02','Ca va?','Albert');
insert into messages (id, date, message, user_name) values(3,'2017-9-20 08:10:05','Bonjour, oui et toi?','Christelle');
insert into messages (id, date, message, user_name) values(4,'2017-9-20 08:10:15','Tranquille Emile','Albert');
insert into messages (id, date, message, user_name) values(5,'2017-9-20 08:10:25','Hahaha','Christelle');
insert into messages (id, date, message, user_name) values(7,'2017-9-20 08:10:45','Oh ouais !','Xavier');
insert into messages (id, date, message, user_name) values(8,'2017-9-20 08:10:57','J y retourne !','Albert');
insert into messages (id, date, message, user_name) values(9,'2017-9-20 08:11:15','A toute !','Christelle');

insert into events(id, date, description)
